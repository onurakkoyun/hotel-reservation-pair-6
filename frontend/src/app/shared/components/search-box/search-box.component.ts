import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
    selector: 'app-search-box',
    templateUrl: './search-box.component.html',
    styleUrl: './search-box.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SearchBoxComponent { }
