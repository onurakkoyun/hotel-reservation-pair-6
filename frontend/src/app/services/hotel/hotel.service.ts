import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable, map } from 'rxjs';
import { environment } from '../../../environment/environment';
import { cp } from 'fs';

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

  private location = new BehaviorSubject<string>('');
  private checkIn = new BehaviorSubject<Date>(new Date());
  private checkOut = new BehaviorSubject<Date>(new Date());
  private guestCount = new BehaviorSubject<number>(1);

/*   currentLocation = this.location.asObservable();
  currentCheckIn = this.checkIn.asObservable();
  currentCheckOut = this.checkOut.asObservable();
  currentGuestCount = this.guestCount.asObservable(); */

  constructor(private http: HttpClient) {}

  setSearchQuery(location: string, checkIn: Date, checkOut: Date, guestCount: number) {
    this.location.next(location);
    this.checkIn.next(checkIn);
    this.checkOut.next(checkOut);
    this.guestCount.next(guestCount);
  }

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

  getHotelById(id: string): Observable<Hotel> {
    return this.http.get<Hotel>(`${this.apiUrl}/get-hotel/${id}`).pipe(
      map((hotel: Hotel) => {
        hotel.lowestRoomPrice = hotel.rooms.length > 0 
          ? Math.min(...hotel.rooms.map((room) => room.dailyPrice))
          : undefined;
        hotel.currency = hotel.rooms.length > 0 ? hotel.rooms[0].currency : undefined;
        return hotel;
      })
    );
  }
  

  searchHotels(): Observable<Hotel[]> {

    //const checkInDate = this.checkIn.value instanceof Date ? this.checkIn.value : new Date(this.checkIn.value);
    //const checkOutDate = this.checkOut.value instanceof Date ? this.checkOut.value : new Date(this.checkOut.value);
    return this.http.get<Hotel[]>(`${this.apiUrl}/search`, {
      params: new HttpParams({
        fromObject: {
          query: this.location.value,
          guestCount: this.guestCount.value,
        },
      }),
    }).pipe(
      map((hotels: Hotel[]) =>
        hotels.map((hotel) => {
          hotel.lowestRoomPrice = hotel.rooms.length > 0 
            ? Math.min(...hotel.rooms.map((room) => room.dailyPrice))
            : undefined;
          hotel.currency = hotel.rooms.length > 0 ? hotel.rooms[0].currency : undefined;
          console.log("blabla", hotel);
          return hotel;
        })
      )
    );
  }
}
