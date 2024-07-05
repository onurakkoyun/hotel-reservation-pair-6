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


  getLatLong(address: string): Observable<Location>  {

    address = address.split(' ')[0];
    if (address) {
      const url = `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(address)}`;
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
      throw new Error('No address provided');
    }
  }
}
