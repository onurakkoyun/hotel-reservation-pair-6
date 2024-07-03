import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {provideNativeDateAdapter} from '@angular/material/core';
import { ChangeDetectionStrategy, Component, EventEmitter, OnChanges, OnInit, Output } from '@angular/core';

@Component({
    selector: 'app-datepicker',
    templateUrl: './datepicker.component.html',
    styleUrl: './datepicker.component.css',
    providers: [provideNativeDateAdapter()],
    changeDetection: ChangeDetectionStrategy.OnPush,

})
export class DatepickerComponent implements OnInit {
  public readonly range = new FormGroup({
    start: new FormControl<Date | null>(null, Validators.required),
    end: new FormControl<Date | null>(null, Validators.required),
  });

  @Output() startdateChange = new EventEmitter<Date>();
  @Output() enddateChange = new EventEmitter<Date>();

  today = new Date();

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
 }
