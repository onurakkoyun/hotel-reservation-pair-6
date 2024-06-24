import { CommonModule, isPlatformBrowser } from '@angular/common';
import { AfterViewInit, ChangeDetectionStrategy, Component, ElementRef, Inject, OnInit, PLATFORM_ID, ViewChild } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
    selector: 'app-datepicker',
    templateUrl: './datepicker.component.html',
    styleUrl: './datepicker.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DatepickerComponent implements AfterViewInit {
    @ViewChild('datepickerStart') datepickerStart: ElementRef | undefined;
    @ViewChild('datepickerEnd') datepickerEnd: ElementRef | undefined;

    range = new FormGroup({
        start: new FormControl<Date | null>(null),
        end: new FormControl<Date | null>(null),
      });

    constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

    async ngAfterViewInit() {
        if (isPlatformBrowser(this.platformId)) {
            const Datepicker = (await import('flowbite-datepicker/Datepicker')).default;
            new Datepicker(this.datepickerStart?.nativeElement, {
                // Options
            });
            new Datepicker(this.datepickerEnd?.nativeElement, {
                // Options
            });
        }
    }

}
