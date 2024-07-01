import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
    selector: 'app-support',
    standalone: true,
    imports: [
        CommonModule,
    ],
    templateUrl: './support.component.html',
    styleUrl: './support.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SupportComponent { }
