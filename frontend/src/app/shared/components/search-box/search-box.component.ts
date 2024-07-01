import { CommonModule } from '@angular/common';
import { AfterViewInit, ChangeDetectionStrategy, Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CounterComponent } from '../counter/counter.component';
import { DatepickerComponent } from '../datepicker/datepicker.component';



@Component({
    selector: 'app-search-box',
    templateUrl: './search-box.component.html',
    styleUrl: './search-box.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SearchBoxComponent implements AfterViewInit, OnInit { 
  public searchForm: FormGroup; // FormGroup tanımlandı
  @ViewChild(DatepickerComponent) datepickerComponent!: DatepickerComponent;
  @ViewChild(CounterComponent) counterComponent!: CounterComponent;

  @Output() search = new EventEmitter<{ location: string, checkIn: Date, checkOut: Date, guestCount: number }>();


  constructor(private fb: FormBuilder) {
    this.searchForm = this.fb.group({
      location: ['', Validators.required],
      checkIn: [null, Validators.required],
      checkOut: [null, Validators.required],
      guestCount: [1, Validators.required]
    });
  }

  ngOnInit() {

  }

  ngAfterViewInit() {
    this.datepickerComponent.enddateChange.subscribe((value) => {
      this.searchForm.get('checkOut')?.setValue(value);
    });

    this.datepickerComponent.startdateChange.subscribe((value) => {
      this.searchForm.get('checkIn')?.setValue(value);
    });

    this.counterComponent.countChange.subscribe((value) => {
      this.searchForm.get('guestCount')?.setValue(value);
    });
  }

  get location() {
    return this.searchForm.get('location')?.value || '';
  }

  get checkIn() {
    return this.searchForm.get('checkIn')?.value || '06/01/2022';
  }

  get checkOut() {
    return this.searchForm.get('checkOut')?.value || '06/02/2022';
  }

  get guestCount() {
    return this.searchForm.get('guestCount')?.value || 1;
  }

  onSearch() {
    const location = this.location;
    const checkIn =  this.checkIn;
    const checkOut =  this.checkOut;
    const guestCount = this.guestCount;
    this.search.emit({ location, checkIn, checkOut, guestCount });
    console.log({ location, checkIn, checkOut, guestCount });
  }
}
