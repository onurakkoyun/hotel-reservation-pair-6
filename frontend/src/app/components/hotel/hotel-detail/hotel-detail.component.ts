import { isPlatformBrowser } from '@angular/common';
import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  Inject,
  Input,
  OnInit,
  PLATFORM_ID,
} from '@angular/core';
import { Hotel, HotelService } from '../../../services/hotel/hotel.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-hotel-detail',
  templateUrl: './hotel-detail.component.html',
  styleUrl: './hotel-detail.component.scss',
})
export class HotelDetailComponent implements OnInit, AfterViewInit {
  private map: any;
  hotel: Hotel = {} as Hotel;

  constructor(
    private hotelService: HotelService,
    private route: ActivatedRoute,
    @Inject(PLATFORM_ID) private platformId: Object,
    private change: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
  }
  
  ngAfterViewInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      import('leaflet').then((L) => {
        this.initMap(L);
      });
    }
    const hotelId = this.route.snapshot.paramMap.get('id');
    if (hotelId) {
      this.hotelService.getHotelById(+hotelId).subscribe((hotel) => {
        this.hotel = hotel;
        this.change.detectChanges();
      });
    }
  }

  //TODO: Replace the latitude and longitude with your hotel's latitude and longitude from backend
  /*   this.hotelService.getHotelDetails(hotelId).subscribe((hotel) => {
    import('leaflet').then((L) => {
        this.initMap(L, hotel.latitude, hotel.longitude);
    });
  });

  private initMap(L: any, latitude: number, longitude: number): void {
    this.map = L.map('map').setView([latitude, longitude], 13);
    // rest of the code...
  } */

  private initMap(L: any): void {
    this.map = L.map('map').setView([51.505, -0.09], 13); // replace with your hotel's latitude and longitude

    const defaultIcon = L.icon({
      iconUrl: 'assets/images/marker-icon.png',
      shadowUrl: 'assets/images/marker-shadow.png',
      iconSize: [25, 41], // size of the icon
      shadowSize: [41, 41], // size of the shadow
      iconAnchor: [12, 41], // point of the icon which will correspond to marker's location
      shadowAnchor: [14, 41], // the same for the shadow
      popupAnchor: [1, -34], // point from which the popup should open relative to the iconAnchor
    });

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 19,
    }).addTo(this.map);

    // Invalidate map size after a delay
    /*     setTimeout(() => {
      this.map.invalidateSize();
    }, 0); */

    L.marker([51.5, -0.09], { icon: defaultIcon })
      .addTo(this.map)
      .bindPopup('A pretty CSS3 popup.<br> Easily customizable.')
      .openPopup();
  }
}
