import { CommonModule } from '@angular/common';
import {
  ChangeDetectionStrategy,
  Component,
  ChangeDetectorRef,
  OnInit,
  Output,
  Inject,
} from '@angular/core';
import { Hotel } from '../../../services/hotel/hotel.service';
import { HotelService } from './../../../services/hotel/hotel.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  changeDetection: ChangeDetectionStrategy.Default,
})
export class HomeComponent implements OnInit {

  ngOnInit(): void {

  }

  constructor(@Inject(HotelService) private hotelService: HotelService
    //private change: ChangeDetectorRef
  ) {}

  onSearch(searchData: { location: string, checkIn: Date, checkOut: Date, guestCount: number }) {
    this.hotelService
      .setSearchQuery(searchData.location, searchData.checkIn, searchData.checkOut, searchData.guestCount);
    this.hotelService.searchHotels();
  }
}
