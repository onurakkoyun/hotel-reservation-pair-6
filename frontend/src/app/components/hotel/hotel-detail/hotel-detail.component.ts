import { isPlatformBrowser } from '@angular/common';
import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  Inject,
  Input,
  NgZone,
  OnInit,
  PLATFORM_ID,
} from '@angular/core';
import { HotelService } from '../../../services/hotel/hotel.service';
import { ActivatedRoute } from '@angular/router';
import {

  RoomService,
} from '../../../services/room/room.service';
import { LocationService } from '../../../services/location/location.service';
import { Hotel } from '../../../services/hotel/model/Hotel';
import { GetRoomResponse } from '../../../services/room/model/GetRoomResponse';

@Component({
  selector: 'app-hotel-detail',
  templateUrl: './hotel-detail.component.html',
  styleUrl: './hotel-detail.component.css',
})
export class HotelDetailComponent implements OnInit, AfterViewInit {
  private map: any;
  hotel: Hotel = {} as Hotel;
  availableRooms: GetRoomResponse[] = [];
  guestCount = +this.route.snapshot.queryParamMap.get('guestCount')!;
  checkInDate = this.route.snapshot.queryParamMap.get('checkIn')!;
  checkOutDate = this.route.snapshot.queryParamMap.get('checkOut')!;
  hotelId = this.route.snapshot.paramMap.get('id')!;
  days = this.daysBetweenDates(this.checkInDate, this.checkOutDate);

  constructor(
    private hotelService: HotelService,
    private route: ActivatedRoute,
    @Inject(PLATFORM_ID) private platformId: Object,
    @Inject(LocationService) private locationService: LocationService,
    private change: ChangeDetectorRef,
    private roomService: RoomService,
    private ngZone: NgZone
  ) {}

  ngOnInit(): void {}

  ngAfterViewInit(): void {
    this.hotelService.getHotelById(+this.hotelId).subscribe((hotel) => {
      this.hotel = hotel;
      if (isPlatformBrowser(this.platformId)) {
        this.ngZone.runOutsideAngular(() => {
          import('leaflet').then((L) => {
            this.initMap(L);
          });
        });
      }
      this.change.detectChanges();
    });

    this.roomService
      .getAvailableRooms(
        +this.hotelId,
        this.guestCount,
        this.checkInDate,
        this.checkOutDate
      )
      .subscribe((rooms) => {
        this.availableRooms = rooms;
        this.change.detectChanges();
      });
  }

  private initMap(L: any): void {
    const defaultIcon = L.icon({
      iconUrl: 'assets/images/marker-icon.png',
      shadowUrl: 'assets/images/marker-shadow.png',
      iconSize: [15, 21], // size of the icon
      shadowSize: [41, 41], // size of the shadow
      iconAnchor: [12, 41], // point of the icon which will correspond to marker's location
      shadowAnchor: [14, 41], // the same for the shadow
      popupAnchor: [2, -34], // point from which the popup should open relative to the iconAnchor
    });

    this.locationService.getLatLong(this.hotel.postalCode).subscribe({
      next: (location) => {
        const coor = [parseFloat(location.lat), parseFloat(location.lon)];
        this.map = L.map('map').setView(coor, 13);
        //
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
          maxZoom: 19,
        }).addTo(this.map);
        //
        L.marker(coor, { icon: defaultIcon })
          .addTo(this.map)
          .bindPopup(`${this.hotel.hotelName}`)
          .openPopup();
      },
      error: (error) => {
        console.error('Error fetching location', error);
      },
    });

    // Invalidate map size after a delay
    /*     setTimeout(() => {
      this.map.invalidateSize();
    }, 0); */
  }

  daysBetweenDates(checkInDate: string, checkOutDate: string): number {
    const checkIn = new Date(checkInDate);
    const checkOut = new Date(checkOutDate);
    const diffTime = Math.abs(checkOut.getTime() - checkIn.getTime());
    const days = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
    return days;
  }

  getRatingText(ratingAverage: number): string {
    if (ratingAverage > 9) return 'Exceptional';
    else if (ratingAverage > 8) return 'Wonderful';
    else if (ratingAverage > 7) return 'Good';
    else if (ratingAverage > 6) return 'Normal';
    else if (ratingAverage > 4) return 'Bad';
    return 'Not rated';
  }

  getRatingClass(ratingAverage: number): string {
    if (ratingAverage > 9) return 'bg-green-700';
    else if (ratingAverage > 8) return 'bg-green-600';
    else if (ratingAverage > 7) return 'bg-yellow-600';
    else if (ratingAverage > 6) return 'bg-orange-600';
    else if (ratingAverage > 4) return 'bg-red-600';
    return 'bg-gray-600';
  }
}
