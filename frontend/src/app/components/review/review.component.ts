import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
    selector: 'app-review',
    templateUrl: './review.component.html',
    styleUrl: './review.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ReviewComponent { }
