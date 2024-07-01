import { AfterViewInit, ChangeDetectionStrategy, ChangeDetectorRef, Component, Inject, Input, OnChanges, OnInit, PLATFORM_ID } from '@angular/core';
import { Hotel, HotelService } from '../../../services/hotel/hotel.service';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-hotel-list',
  templateUrl: './hotel-list.component.html',
  styleUrl: './hotel-list.component.scss',
})
export class HotelListComponent implements OnInit {

  hotels: Hotel[] = [];

  constructor(@Inject(HotelService) private hotelService: HotelService, private router: Router,
    private change: ChangeDetectorRef ) {
    
   }


/*   ngOnChanges(): void {
    this.searchHotels();
  }
 */
  ngOnInit(): void {
  }
  
  ngAfterViewInit(): void {
    this.loadHotels();
  }

  loadHotels(): void {
    this.hotelService.hotels.subscribe((hotels) => {
      this.hotels = hotels;
      this.change.detectChanges();
    })
  }
}
