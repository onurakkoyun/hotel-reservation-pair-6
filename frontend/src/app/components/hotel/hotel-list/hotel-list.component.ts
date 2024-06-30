import { AfterViewInit, Component, Inject, Input, OnChanges, OnInit, PLATFORM_ID } from '@angular/core';
import { Hotel, HotelService } from '../../../services/hotel/hotel.service';

@Component({
  selector: 'app-hotel-list',
  templateUrl: './hotel-list.component.html',
  styleUrl: './hotel-list.component.scss'
})
export class HotelListComponent implements OnInit, AfterViewInit, OnChanges {

  hotels: Hotel[] = [];

  constructor(@Inject(HotelService) private hotelService: HotelService) { }


  ngOnChanges(): void {
    this.searchHotels();
  }

  ngOnInit(): void {

    this.hotelService.getAllHotels().subscribe((data: Hotel[]) => {
      this.hotels = data;
    });
  }
  
  ngAfterViewInit(): void {

/*     this.hotelService.getAllHotels().subscribe((data: Hotel[]) => {
      this.hotels = data;
    }); */
  }

  private searchHotels(): void {
    this.hotelService.searchHotels().subscribe((data: Hotel[]) => {
      this.hotels = data;
    });
  }


}
