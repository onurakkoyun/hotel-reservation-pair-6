import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';


export interface Location {
  place_id: number;
  licence: string;
  osm_type: string;
  osm_id: number;
  lat: string;
  lon: string;
  class: string;
  type: string;
  place_rank: number;
  importance: number;
  addresstype: string;
  name: string;
  display_name: string;
  boundingbox: string[];
}

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(private http: HttpClient) { }


  getLatLong(postalCode: string): Observable<Location>  {

    if (postalCode) {
      const url = `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(postalCode)}`;
      return this.http.get<Location[]>(url).pipe(
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
