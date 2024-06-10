import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
    selector: 'app-review',
    standalone: true,
    imports: [
        CommonModule,
    ],
    templateUrl: './review.component.html',
    styleUrl: './review.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ReviewComponent { }
