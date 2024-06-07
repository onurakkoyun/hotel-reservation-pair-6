import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
    selector: 'app-pagination',
    templateUrl: './pagination.component.html',
    styleUrl: './pagination.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PaginationComponent { 
    @Input() pageIndex: number = 0;
    @Input() pageSize: number = 0;
    @Input() totalItems: number = 0;
    @Output() changePage = new EventEmitter<number>();

    onChangePage(requestedPageIndex: number) {
        if (requestedPageIndex === this.pageIndex) return;
    
        this.changePage.emit(requestedPageIndex);
    }

    get totalPages(): number {
        return Math.ceil(this.totalItems / this.pageSize);
    }

    get pages(): number[] {
        return new Array(this.totalPages);
    }

    get showPrevious(): boolean {
        return this.pageIndex > 0;
    }

    get showNext(): boolean {
        return this.pageIndex < this.totalPages - 1;
    }

}
