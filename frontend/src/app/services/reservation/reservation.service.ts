import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environment/environment';
import { HttpClient } from '@angular/common/http';

export interface CreateReservationRequest {
  roomId: number;
  guestId: number;
  cardNumber: string;
  cardHolderName: string;
  expirationMonth: string;
  expirationYear: string;
  cvc: string;
  currency: string;
  checkInDate: string;
  checkOutDate: string;
}

@Injectable({
  providedIn: 'root',
})
export class ReservationService {
  private apiUrl = `${environment.apiUrl}/api/reservations`;
  constructor(private http: HttpClient) {}

  createReservation(
    reservationRequest: CreateReservationRequest
  ): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/create`, reservationRequest);
  }
}
