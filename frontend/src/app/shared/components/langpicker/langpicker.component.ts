import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
    selector: 'app-langpicker',
    templateUrl: './langpicker.component.html',
    styleUrl: './langpicker.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LangpickerComponent { }
