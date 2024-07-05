import {
  AfterViewInit,
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  EventEmitter,
  Inject,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CounterComponent } from '../counter/counter.component';
import { DatepickerComponent } from '../datepicker/datepicker.component';
import { HotelService } from '../../../services/hotel/hotel.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-box',
  templateUrl: './search-box.component.html',
  styleUrls: ['./search-box.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SearchBoxComponent implements AfterViewInit, OnInit {
  public searchForm: FormGroup; // FormGroup tanımlandı
  @ViewChild(DatepickerComponent) datepickerComponent!: DatepickerComponent;
  @ViewChild(CounterComponent) counterComponent!: CounterComponent;

  queryControl = this.fb.control('');

  constructor(
    private fb: FormBuilder,
    @Inject(HotelService) private hotelService: HotelService,
    private router: Router
  ) {
    this.searchForm = this.fb.group({
      query: this.queryControl,
      checkIn: [null, Validators.required],
      checkOut: [null, Validators.required],
      guestCount: [2],
    });
  }

  ngOnInit() { }

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

  get query() {
    return this.searchForm.get('query')?.value || '';
  }

  get checkIn() {
    return this.searchForm.get('checkIn')?.value || new Date();
  }

  get checkOut() {
    return (
      this.searchForm.get('checkOut')?.value ||
      new Date(new Date().setDate(new Date().getDate() + 1))
    );
  }

  get guestCount() {
    return this.searchForm.get('guestCount')?.value || 2;
  }

  clearQuery() {
    this.queryControl.setValue('');
    this.onSearch();
  }

  onSearch() {
    // Get the local time zone offset in minutes
    const timezoneOffset = new Date().getTimezoneOffset() * 60000; // Convert to milliseconds

    // Adjust the dates for the time zone offset before converting to ISO string
    const adjustedCheckIn = new Date(this.checkIn.getTime() - timezoneOffset);
    const adjustedCheckOut = new Date(this.checkOut.getTime() - timezoneOffset);

    const searchQuery = {
      query: this.query,
      checkIn: adjustedCheckIn.toISOString().slice(0, 10),
      checkOut: adjustedCheckOut.toISOString().slice(0, 10),
      guestCount: this.guestCount,
    };
    this.hotelService.searchEvent.emit({
      ...searchQuery,
    });
    // Navigating with query parameters
    this.router.navigate(['search'], { // Assuming '/search' is your search page route
      queryParams: { ...searchQuery }
    });
  }
}
