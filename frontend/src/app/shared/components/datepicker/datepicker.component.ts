import { CommonModule, isPlatformBrowser } from '@angular/common';
import { AfterViewInit, ChangeDetectionStrategy, Component, ElementRef, Inject, OnInit, PLATFORM_ID, ViewChild } from '@angular/core';

@Component({
    selector: 'app-datepicker',
    templateUrl: './datepicker.component.html',
    styleUrl: './datepicker.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DatepickerComponent implements AfterViewInit {
    @ViewChild('datepickerStart') datepickerStart: ElementRef | undefined;
    @ViewChild('datepickerEnd') datepickerEnd: ElementRef | undefined;

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
