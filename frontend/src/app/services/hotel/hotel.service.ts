import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { environment } from '../../../environment/environment';

export interface Hotel {
  id: number;
  managerId: number;
  hotelName: string;
  isBreakfastAvailable: boolean;
  IsBreakfastIncludedInPrice: boolean;
  breakfastPricePerPerson: number;
  starCount: number;
  ratingAverage: number;
  firstAddressLine: string;
  secondAddressLine: string;
  city: string;
  postalCode: string;
  province: string;
  country: string;
  hotelFeatures: GetHotelFeatureResponse[];
  hotelImages: GetHotelImageResponse[];
  reviews: GetReviewResponse[];
  rooms: GetRoomResponse[];
  lowestRoomPrice?: number;
  currency?: string;
}

export interface GetHotelFeatureResponse {
  id: number;
  hotelFeatureName: string;
}

export interface GetReviewResponse {
  id: number;
  comment: string;
  creationDate: Date;
  rating: number;
  guestId: number;
  hotelId: number;
}

export interface GetRoomResponse {
  id: number;
  quantity: number;
  dailyPrice: number;
  currency: string;
  capacity: number;
  squareMeterSize: number;
  hotelId: number;
  roomTypeId: number;
  roomTypeName: string;
  roomBeds: GetRoomBedResponse[];
  roomFeatures: GetRoomFeatureResponse[];
}

export interface GetRoomBedResponse{
  id: number;
  quantity: number;
  bedName: string;
}

export interface GetRoomFeatureResponse{
  id: number;
  featureName: string;
}

export interface GetHotelImageResponse {
  id: number;
  url: string;
  hotelId: number;
}

@Injectable({
  providedIn: 'root'
})
export class HotelService {

  private apiUrl = `${environment.apiUrl}/api/hotels`;

  constructor(private http: HttpClient) {}

  getAllHotels(): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(`${this.apiUrl}/get-all`).pipe(
      map((hotels: Hotel[]) =>
        hotels.map((hotel) => {
          hotel.lowestRoomPrice = hotel.rooms.length > 0 
            ? Math.min(...hotel.rooms.map((room) => room.dailyPrice))
            : undefined;
          hotel.currency = hotel.rooms.length > 0 ? hotel.rooms[0].currency : undefined;
          return hotel;
        })
      )
    );
  }
  
  searchHotels(query: string, guestCount: number): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(`${this.apiUrl}/search`, {
      params: {
        query: query,
        guestCount: guestCount.toString()
      }
    }).pipe(
      map((hotels: Hotel[]) =>
        hotels.map((hotel) => {
          hotel.lowestRoomPrice = hotel.rooms.length > 0 
            ? Math.min(...hotel.rooms.map((room) => room.dailyPrice))
            : undefined;
          hotel.currency = hotel.rooms.length > 0 ? hotel.rooms[0].currency : undefined;
          console.log(hotel);
          return hotel;
        })
      )
    );
  }
}
