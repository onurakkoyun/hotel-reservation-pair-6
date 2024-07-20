import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environment/environment';
import { GetRoomResponse } from './model/GetRoomResponse';



@Injectable({
  providedIn: 'root',
})
export class RoomService {
  private apiUrl = `${environment.apiUrl}/api/rooms`;

  constructor(private http: HttpClient) {}

  getAvailableRooms(
    hotelId: number,
    guestCount: number,
    checkInDate: string,
    checkOutDate: string
  ): Observable<GetRoomResponse[]> {
    let params = new HttpParams()
      .set('hotelId', hotelId.toString())
      .set('guestCount', guestCount.toString())
      .set('checkInDate', checkInDate)
      .set('checkOutDate', checkOutDate);

    return this.http.get<GetRoomResponse[]>(`${this.apiUrl}/available-rooms`, {
      params,
    });
  }

  getById(id: number): Observable<GetRoomResponse> {
    let params = new HttpParams().set('id', id.toString());
    return this.http.get<GetRoomResponse>(`${this.apiUrl}/get-by-id`, {
      params,
    });
  }
}
