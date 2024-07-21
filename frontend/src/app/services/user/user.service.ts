import { HttpClient, HttpParams } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject, tap } from 'rxjs';
import { GuestDetails } from './models/GuestDetails';
import { ManagerDetails } from './models/ManagerDetails';
import { environment } from '../../../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiControllerUrl = `${environment.apiUrl}/api/users`;
  private _userDetails = new Subject<GuestDetails | ManagerDetails>();

  constructor(private http: HttpClient) { }

  get userDetails(): Observable<GuestDetails | ManagerDetails> {
    return this._userDetails.asObservable();
  }

  getGuestDetailsByEmail(email: string): Observable<GuestDetails | ManagerDetails> {
      const params = new HttpParams().set('email', email); // Use HttpParams to add query parameters
      return this.http.get<GuestDetails | ManagerDetails>(`${this.apiControllerUrl}/get-by-email`, {
        params: params // Attach the email as a query parameter
      }).pipe(
        tap((userDetails) => {
          this._userDetails.next(userDetails);
        })
      );
    }
}
