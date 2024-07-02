import { CommonModule, isPlatformBrowser } from '@angular/common';
import { AfterViewInit, ChangeDetectionStrategy, Component, ElementRef, EventEmitter, Inject, Input, OnChanges, OnInit, Output, PLATFORM_ID, ViewChild } from '@angular/core';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { Datepicker } from 'flowbite-datepicker';
import type { DatepickerOptions, InstanceOptions } from 'flowbite-datepicker';

@Component({
    selector: 'app-datepickerold',
    templateUrl: './datepickerold.component.html',
    styleUrl: './datepickerold.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DatepickerComponentOld implements OnInit {
    @ViewChild('datepickerStart') datepickerStart: ElementRef | undefined;
    @ViewChild('datepickerEnd') datepickerEnd: ElementRef | undefined;

    @Output() startdateChange = new EventEmitter<Date>();
    @Output() enddateChange = new EventEmitter<Date>();

/*     public checkIn: Date | null = null;
    public checkOut: Date | null = null; */
    
/*     public checkIn = new FormControl<Date | null>(null);
    public checkOut = new FormControl<Date | null>(null); */


     public range = new FormGroup({
        start: new FormControl<Date | null>(null),
        end: new FormControl<Date | null>(null),
      });

    constructor( @Inject(PLATFORM_ID) private platformId: Object) {


/*         this.range = this.fb.group({
          checkIn: new FormControl<Date | null>(null),
          checkOut: new FormControl<Date | null>(null),
        });
 */
    }

    ngOnInit() {
          this.range.get('start')?.valueChanges.subscribe((newStartDate) => {
            if (newStartDate) {
              this.startdateChange.emit(newStartDate);
              console.log(newStartDate);
            }
          });
      

        this.range.get('end')?.valueChanges.subscribe((newEndDate) => {
            if (newEndDate) {
              this.enddateChange.emit(newEndDate);
              console.log(newEndDate);
            }
          });
    }

    async ngAfterViewInit() {
        if (isPlatformBrowser(this.platformId)) {
            const Datepicker = (await import('flowbite-datepicker')).Datepicker;

            const datepickerStartInstance = new Datepicker(this.datepickerStart?.nativeElement);
        
            const datepickerEndInstance = new Datepicker(this.datepickerEnd?.nativeElement);

        }
    }

/*     public assignStartDate(date: Date | null) {
        this.checkIn = date;
        this.startdateChange.emit(date!);
    }

    public assignEndDate(date: Date | null) {
        this.checkOut = date;
        this.enddateChange.emit(date!);
    } */
}
