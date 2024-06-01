import { Inject, Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { DOCUMENT } from '@angular/common';
import { CoreAuthService } from '../../core/auth/services/core-auth.service';
import { LoggedUser } from '../../models/logged-user';
import { LoginCredentials } from '../../models/login-credentials';
import { environment } from '../../../environment/environment';
import { RegisteredUser } from '../../models/registered-user';
import { RegisterCredentials } from '../../models/register-credentials';

@Injectable({
  providedIn: 'root',
})
export class AuthService extends CoreAuthService {
  private apiControllerUrl = `${environment.apiUrl}/api/auth`;

  constructor(private http: HttpClient, @Inject(DOCUMENT) document: Document) {
    super(document);
  }

  login(loginCredentials: LoginCredentials): Observable<LoggedUser> {
    return this.http
      .post<LoggedUser>(`${this.apiControllerUrl}/login`, loginCredentials)
      .pipe(
        tap((loggedUser) => {
          this.token = loggedUser.access_token;
          this._logged.next();
          this._isLogged.next(true);
        })
      );
  }

  register(registerCredentials: RegisterCredentials): Observable<RegisteredUser> {
    return this.http.post<RegisteredUser>(
      `${this.apiControllerUrl}/register`,
      registerCredentials
    ).pipe(
      tap((registeredGuest) => {
        this.token = registeredGuest.access_token;
        this._registered.next();
        this._isRegistered.next(true);
       })
    );
  }
}