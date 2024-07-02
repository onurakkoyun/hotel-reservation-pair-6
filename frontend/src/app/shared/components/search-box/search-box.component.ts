import { AfterViewInit, ChangeDetectionStrategy, Component, EventEmitter, Inject, OnInit, Output, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CounterComponent } from '../counter/counter.component';
import { DatepickerComponent } from '../datepicker/datepicker.component';
import { HotelService } from '../../../services/hotel/hotel.service';



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

  value = 'Clear me';

  constructor(private fb: FormBuilder, @Inject(HotelService) private hotelService: HotelService){
    this.searchForm = this.fb.group({
      location: [''],
      checkIn: [null, Validators.required],
      checkOut: [null, Validators.required],
      guestCount: [1]
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
    return this.searchForm.get('checkIn')?.value || new Date();
  }

  get checkOut() {
    return this.searchForm.get('checkOut')?.value || new Date(new Date().setDate(new Date().getDate() + 1));
  }

  get guestCount() {
    return this.searchForm.get('guestCount')?.value || 2;
  }

  onSearch() {
    const location = this.location;
    const checkIn =  this.checkIn;
    const checkOut =  this.checkOut;
    const guestCount = this.guestCount;
    this.hotelService.searchEvent.emit({ location, checkIn, checkOut, guestCount });
    //console.log({ location, checkIn, checkOut, guestCount });
  }
}
