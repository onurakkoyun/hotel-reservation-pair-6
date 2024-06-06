import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
    selector: 'app-counter',
    templateUrl: './counter.component.html',
    styleUrl: './counter.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CounterComponent { }
