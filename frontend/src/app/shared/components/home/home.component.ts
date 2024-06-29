import { CommonModule } from '@angular/common';
import {
  ChangeDetectionStrategy,
  Component,
  ChangeDetectorRef,
  OnInit,
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
  hotels: Hotel[] = [];

  ngOnInit(): void {
    this.hotelService.getAllHotels().subscribe((data: Hotel[]) => {
      this.hotels = data;
    });
  }

  constructor(
    private hotelService: HotelService
    //private change: ChangeDetectorRef
  ) {}

  onSearch(searchData: { query: string; guestCount: number }) {
    this.hotelService
      .searchHotels(searchData.query, searchData.guestCount)
      .subscribe((data: Hotel[]) => {
        this.hotels = data;
      });
  }
}
