import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable, filter, map } from 'rxjs';
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

  private _hotels: BehaviorSubject<Hotel[]> = new BehaviorSubject<Hotel[]>([]);
  private _searchedhotels: BehaviorSubject<Hotel[]> = new BehaviorSubject<Hotel[]>([]);

/*   currentLocation = this.location.asObservable();
  currentCheckIn = this.checkIn.asObservable();
  currentCheckOut = this.checkOut.asObservable();
  currentGuestCount = this.guestCount.asObservable(); */

  constructor(private http: HttpClient) {}

  updateHotels(hotels: Hotel[]): void {
    this._hotels.next(hotels);
  }

  get hotels(): Observable<Hotel[]> {
    return this._hotels.asObservable();
  }

  updateSearchedHotels(hotels: Hotel[]): void {
    this._searchedhotels.next(hotels);
  }

  get searchedHotels(): Observable<Hotel[]> {
    return this._searchedhotels.asObservable();
  }

  setSearchQuery(location: string, checkIn: Date, checkOut: Date, guestCount: number) {
    this.location.next(location);
    this.checkIn.next(checkIn);
    this.checkOut.next(checkOut);
    this.guestCount.next(guestCount);
  }

  getAllHotels(): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(`${this.apiUrl}/get-all`).pipe(
      map((hotels: Hotel[]) => {
        hotels.forEach((hotel) => {
          hotel.lowestRoomPrice = hotel.rooms.length > 0 
            ? Math.min(...hotel.rooms.map((room) => room.dailyPrice))
            : undefined;
          hotel.currency = hotel.rooms.length > 0 ? hotel.rooms[0].currency : undefined;
          console.log("blabla");
        });
        return hotels; // Ensure the modified hotels array is returned
      })
    );
  }

  getHotelById(id: number): Observable<Hotel> {
    return this.hotels.pipe(
      filter((hotels) => hotels.map((hotel) => hotel.id).includes(id)),
      map((hotels) => hotels.find((hotel) => hotel.id === id) as Hotel)
    );
  }
  

  searchHotels(): Observable<Hotel[]>  {
    return this.http.get<Hotel[]>(`${this.apiUrl}/search`, {
      params: new HttpParams({
        fromObject: {
          query: this.location.value,
          guestCount: this.guestCount.value,
        },
      }),
    }).pipe(
      map((hotels: Hotel[]) => hotels.map((hotel) => {
        hotel.lowestRoomPrice = hotel.rooms.length > 0
          ? Math.min(...hotel.rooms.map(room => room.dailyPrice))
          : undefined;
        hotel.currency = hotel.rooms.length > 0 ? hotel.rooms[0].currency : undefined;
        return hotel;
      })
      )
    );
  }
}
