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
import {HotelService } from '../../../services/hotel/hotel.service';
import { NavigationEnd, Router } from '@angular/router';
import { Hotel } from '../../../services/hotel/model/Hotel';

@Component({
  selector: 'app-hotel-list',
  templateUrl: './hotel-list.component.html',
  styleUrl: './hotel-list.component.scss',
})
export class HotelListComponent implements OnInit, AfterViewInit {
  hotels: Hotel[] = [];
  isSearchClicked = false;
  searchQuery: {
    query: string;
    checkIn: string;
    checkOut: string;
    guestCount: number;
  } = {
    query: '',
    checkIn: new Date().toISOString().slice(0, 10),
    checkOut: new Date(new Date().setDate(new Date().getDate() + 1))
      .toISOString()
      .slice(0, 10),
    guestCount: 2,
  };

  constructor(
    @Inject(HotelService) private hotelService: HotelService,
    private change: ChangeDetectorRef,
    private router: Router
  ) {}

  ngOnInit(): void {
    //this.loadHotels();
  }

  ngAfterViewInit(): void {
    //this.loadHotels();
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
      this.isSearchClicked = true;
      this.hotelService
        .searchHotels(
          searchQuery.query,
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
      // Navigating with query parameters
      this.router.navigate(['search'], {
        // Assuming '/search' is your search page route
        queryParams: { ...this.searchQuery },
      });

      this.hotelService
        .searchHotels(
          this.searchQuery.query,
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
