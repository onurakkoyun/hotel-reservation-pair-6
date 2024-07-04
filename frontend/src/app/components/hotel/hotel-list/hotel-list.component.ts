import {
  AfterViewInit,
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  Inject,
  Input,
  OnChanges,
  OnInit,
  PLATFORM_ID,
} from '@angular/core';
import { Hotel, HotelService } from '../../../services/hotel/hotel.service';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-hotel-list',
  templateUrl: './hotel-list.component.html',
  styleUrl: './hotel-list.component.scss',
})
export class HotelListComponent implements OnInit, AfterViewInit {
  hotels: Hotel[] = [];
  searchQuery: {
    location: string;
    checkIn: Date;
    checkOut: Date;
    guestCount: number;
  } = {
    location: '',
    checkIn: new Date(),
    checkOut: new Date(),
    guestCount: 1,
  };

  constructor(
    @Inject(HotelService) private hotelService: HotelService,
    private change: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadHotels();
  }

  ngAfterViewInit(): void {
    this.loadHotels();
    this.searchHotels();
    this.applyFilters();
  }

  loadHotels(): void {
    this.hotelService.getAllHotels().subscribe((hotels) => {
      this.hotels = hotels;
      this.change.detectChanges();
    });
  }

  searchHotels(): void {
    this.hotelService.searchEvent.subscribe((searchQuery) => {
      this.searchQuery = searchQuery;
      this.hotelService
        .searchHotels(
          searchQuery.location,
          searchQuery.checkIn,
          searchQuery.checkOut,
          searchQuery.guestCount
        )
        .subscribe((hotels) => {
          this.hotels = hotels;
          this.change.detectChanges();
        });
    });
  }

  applyFilters(): void {
    this.hotelService.filterEvent.subscribe((filterQuery) => {
      this.hotelService
        .searchHotels(
          this.searchQuery.location,
          this.searchQuery.checkIn,
          this.searchQuery.checkOut,
          this.searchQuery.guestCount
        )
        .subscribe((hotels) => {
          this.hotels = this.hotelService.filterHotels(
            hotels,
            filterQuery.star1,
            filterQuery.star2,
            filterQuery.star3,
            filterQuery.star4,
            filterQuery.star5,
            filterQuery.priceFrom,
            filterQuery.priceTo
          );
          this.change.detectChanges();
        });
    });
  }
}
