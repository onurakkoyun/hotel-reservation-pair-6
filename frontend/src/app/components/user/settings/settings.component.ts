import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
    selector: 'app-settings',
    standalone: true,
    imports: [
        CommonModule,
    ],
    templateUrl: './settings.component.html',
    styleUrl: './settings.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SettingsComponent { }
