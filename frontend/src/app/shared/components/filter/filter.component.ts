import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
    selector: 'app-filter',
    templateUrl: './filter.component.html',
    styleUrl: './filter.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class FilterComponent { }
