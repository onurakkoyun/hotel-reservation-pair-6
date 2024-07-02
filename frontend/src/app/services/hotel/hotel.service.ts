import { EventEmitter, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable, filter, map } from 'rxjs';
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

  searchEvent = new EventEmitter<{ location: string, checkIn: Date, checkOut: Date, guestCount: number }>();
  filterEvent = new EventEmitter<{ star1: boolean, star2: boolean, star3: boolean, star4: boolean, star5: boolean, priceFrom: number, priceTo: number}>();
  resetEvent = new EventEmitter<{ star1: boolean, star2: boolean, star3: boolean, star4: boolean, star5: boolean, priceFrom: number, priceTo: number}>();


  constructor(private http: HttpClient) {}

  getAllHotels(): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(`${this.apiUrl}/get-all`).pipe(
      map((hotels: Hotel[]) => {
        hotels.forEach((hotel) => {
          hotel.lowestRoomPrice = hotel.rooms.length > 0 
            ? Math.min(...hotel.rooms.map((room) => room.dailyPrice))
            : undefined;
          hotel.currency = hotel.rooms.length > 0 ? hotel.rooms[0].currency : undefined;
        });
        return hotels; // Ensure the modified hotels array is returned
      })
    );
  }

/*   getHotelById(id: number): Observable<Hotel> {
    return this.hotels.pipe(
      filter((hotels) => hotels.map((hotel) => hotel.id).includes(id)),
      map((hotels) => hotels.find((hotel) => hotel.id === id) as Hotel)
    );
  } */
  

  searchHotels(location: string, checkIn: Date, checkOut: Date, guestCount: number): Observable<Hotel[]>  {
    return this.http.get<Hotel[]>(`${this.apiUrl}/search`, {
      params: new HttpParams({
        fromObject: {
          query: location,
          startDate: checkIn.toISOString().slice(0, 10),
          endDate:  checkOut.toISOString().slice(0, 10),
          guestCount: guestCount.toString(),
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
    );;
  }

  filterHotels(hotels: Hotel[], star1: boolean, star2: boolean, star3: boolean, star4: boolean, star5: boolean, priceFrom: number, priceTo: number): Hotel[] {
    return hotels.filter(hotel => {
        const matchesStarRating = this.matchesStarRating(hotel, star1, star2, star3, star4, star5);
        const matchesPriceRange = this.matchesPriceRange(hotel, priceFrom, priceTo);
        return matchesStarRating && matchesPriceRange;
    });
  }

  private matchesStarRating(hotel: Hotel, star1: boolean, star2: boolean, star3: boolean, star4: boolean, star5: boolean): boolean {
    const starCount = hotel.starCount;
    return (
      (star1 && starCount === 1) ||
      (star2 && starCount === 2) ||
      (star3 && starCount === 3) ||
      (star4 && starCount === 4) ||
      (star5 && starCount === 5) ||
      (!star1 && !star2 && !star3 && !star4 && !star5) // No star rating selected
    );
  }

  private matchesPriceRange(hotel: Hotel, priceFrom: number, priceTo: number): boolean {
    const price = hotel.lowestRoomPrice || 0;
    return (
      (priceFrom ? price >= priceFrom : true) &&
      (priceTo ? price <= priceTo : true)
    );
  }
}
