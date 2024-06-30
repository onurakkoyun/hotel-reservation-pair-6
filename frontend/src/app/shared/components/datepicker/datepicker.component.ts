import { CommonModule, isPlatformBrowser } from '@angular/common';
import { AfterViewInit, ChangeDetectionStrategy, Component, ElementRef, EventEmitter, Inject, Input, OnInit, Output, PLATFORM_ID, ViewChild } from '@angular/core';
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

    @Output() dateChange = new EventEmitter<Date>();
    
    public checkIn = new FormControl<Date | null>(null);
    public checkOut = new FormControl<Date | null>(null);

/*     range = new FormGroup({
        start: new FormControl<Date | null>(null),
        end: new FormControl<Date | null>(null),
      }); */

    constructor(@Inject(PLATFORM_ID) private platformId: Object) {
        this.checkIn.valueChanges.subscribe((value) => {
            if (value !== null) {
                this.dateChange.emit(value);
            }
        });

        this.checkOut.valueChanges.subscribe((value) => {
            if (value !== null) {
                this.dateChange.emit(value);
            }
        });
    }

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
