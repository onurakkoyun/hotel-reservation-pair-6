import { isPlatformBrowser } from '@angular/common';
import { AfterViewInit, Component, Inject, PLATFORM_ID } from '@angular/core';

@Component({
  selector: 'app-hotel-detail',
  templateUrl: './hotel-detail.component.html',
  styleUrl: './hotel-detail.component.scss'
})
export class HotelDetailComponent implements AfterViewInit {
  private map: any;


  constructor(@Inject(PLATFORM_ID) private platformId: Object) { }

  ngAfterViewInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      import('leaflet').then((L) => {
        this.initMap(L);
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
      popupAnchor: [1, -34] // point from which the popup should open relative to the iconAnchor
    });

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 19,
    }).addTo(this.map);

    // Invalidate map size after a delay
    setTimeout(() => {
      this.map.invalidateSize();
    }, 0);

    L.marker([51.5, -0.09], { icon: defaultIcon }).addTo(this.map)
      .bindPopup('A pretty CSS3 popup.<br> Easily customizable.')
      .openPopup();
  }
}
