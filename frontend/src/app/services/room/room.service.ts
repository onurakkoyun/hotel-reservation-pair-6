import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environment/environment';

export interface GetRoomResponse {
  id: number;
  hotelId: number;
  roomTypeId: number;
  roomTypeName: string;
  quantity: number;
  dailyPrice: number;
  currency: string;
  capacity: number;
  squareMeterSize: number;
  roomImages: GetRoomImageResponse[];
  roomBeds: GetRoomBedResponse[];
  roomFeatures: GetRoomFeatureResponse[];
}

export interface GetRoomImageResponse {
  id: number;
  url: string;
}

export interface GetRoomBedResponse {
  id: number;
  quantity: number;
  bedName: string;
}

export interface GetRoomFeatureResponse {
  id: number;
  featureName: string;
}

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
      .set('checkInDate', checkInDate.slice(0, 10))
      .set('checkOutDate', checkOutDate.slice(0, 10));

    return this.http.get<GetRoomResponse[]>(`${this.apiUrl}/available-rooms`, {
      params,
    });
  }
}
