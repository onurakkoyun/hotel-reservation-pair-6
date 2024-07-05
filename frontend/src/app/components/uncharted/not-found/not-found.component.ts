import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
    selector: 'app-not-found',
    standalone: true,
    imports: [
        CommonModule,
    ],
    templateUrl: './not-found.component.html',
    styleUrl: './not-found.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NotFoundComponent { }
