import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject, tap } from 'rxjs';
import { GuestDetails } from './models/GuestDetails';
import { ManagerDetails } from './models/ManagerDetails';
import { environment } from '../../../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiControllerUrl = `${environment.apiUrl}/api/users`;
  private _firstName = new BehaviorSubject<string>('');
  private _lastName = new BehaviorSubject<string>('');
  private _email = new BehaviorSubject<string>('');
  private _phoneNumber = new BehaviorSubject<string>('');
  private _companyName = new BehaviorSubject<string>('');

  constructor(private http: HttpClient) { }

  get firstName(): Observable<string> {
    return this._firstName.asObservable();
  }

  get lastName(): Observable<string> {
    return this._lastName.asObservable();
  }

  get email(): Observable<string> {
    return this._email.asObservable();
  }

  get phoneNumber(): Observable<string> {
    return this._phoneNumber.asObservable();
  }

  get companyName(): Observable<string> {
    return this._companyName.asObservable();
  }

  getGuestDetailsByEmail(access_token: string, email: string): Observable<GuestDetails> {
      const params = new HttpParams().set('email', email); // Use HttpParams to add query parameters
      return this.http.get<GuestDetails>(`${this.apiControllerUrl}/get-by-email`, {
        headers: {
          'Authorization': `Bearer ${access_token}`,
        },
        params: params // Attach the email as a query parameter
      }).pipe(
        tap((guestDetails) => {
          this._firstName.next(guestDetails.firstName);
          this._lastName.next(guestDetails.lastName);
        })
      );
    }

  getManagerDetailsByEmail(access_token: string, email: string): Observable<ManagerDetails> {
    const params = new HttpParams().set('email', email); // Use HttpParams to add query parameters
    return this.http.get<ManagerDetails>(`${this.apiControllerUrl}/get-by-email`, {
      headers: {
        // Assuming loginCredentials need to be sent as headers. Adjust as necessary.
        'Authorization': `Bearer ${access_token}`, // Example, adjust based on your auth method
      },
      params: params // Attach the email as a query parameter
    }).pipe(
      tap((managerDetails) => {
        this._firstName.next(managerDetails.firstName);
        this._lastName.next(managerDetails.lastName);
        this._email.next(managerDetails.email);
        this._companyName.next(managerDetails.companyName);
      })
    );
  }
}
