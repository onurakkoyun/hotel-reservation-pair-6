import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { HotelLocation } from './model/HotelLocation';


@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(private http: HttpClient) { }


  getLatLong(postalCode: string): Observable<HotelLocation>  {

    if (postalCode) {
      const url = `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(postalCode)}`;
      return this.http.get<HotelLocation[]>(url).pipe(
        map(data => {
          if (data.length > 0) {
            return data[0];
          } else {
            throw new Error('No location found');
          }
        })
      );
    } else {
      throw new Error('No postalCode provided');
    }
  }
}
