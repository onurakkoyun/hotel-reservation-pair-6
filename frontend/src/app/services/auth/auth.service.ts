import { EventEmitter, Inject, Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { DOCUMENT } from '@angular/common';
import { CoreAuthService } from '../../core/auth/services/core-auth.service';
import { LoggedUser } from '../../models/logged-user';
import { LoginCredentials } from '../../models/login-credentials';
import { environment } from '../../../environment/environment';
import { RegisteredUser } from '../../models/registered-user';
import { RegisterCredentials } from '../../models/register-credentials';
import { RegisteredManager } from '../../models/registered-manager';
import { RegisterManagerCredentials } from '../../models/register-manager-credentials';

@Injectable({
  providedIn: 'root',
})
export class AuthService extends CoreAuthService {
  private apiControllerUrl = `${environment.apiUrl}/api/auth`;
  loginSuccess = new EventEmitter<void>();

  constructor(private http: HttpClient, @Inject(DOCUMENT) document: Document) {
    super(document);
  }

  emitLoginSuccess(): void {
    this.loginSuccess.emit();
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

  logout(): Observable<void> {
    return this.http.post<void>(`${this.apiControllerUrl}/logout`, {}).pipe(
      tap(() => {
        super.logoutHandler();
      })
    );
  }

  register(registerCredentials: RegisterCredentials): Observable<RegisteredUser> {
    return this.http.post<RegisteredUser>(
      `${this.apiControllerUrl}/register-guest`,
      registerCredentials
    ).pipe(
      tap((registeredGuest) => {
        this.token = registeredGuest.access_token;
        this.refreshToken = registeredGuest.refresh_token; 
        this._registered.next();
        this._isRegistered.next(true);
       })
    );
  }

  registerManager(registerManagerCredentials: RegisterManagerCredentials): Observable<RegisteredManager> {
    return this.http.post<RegisteredManager>(
      `${this.apiControllerUrl}/register-manager`,
      registerManagerCredentials
    ).pipe(
      tap((registeredManager) => {
        this.token = registeredManager.access_token;
        this.refreshToken = registeredManager.refresh_token;
        this._registeredManager.next();
        this._isRegisteredManager.next(true);
       })
    );
  }

  //TODO: There is no refresh-token endpoint in the backend
  // Method to refresh the access token using the refresh_token
  refreshAccessToken(): Observable<any> {
    return this.http.post<any>(
      `${this.apiControllerUrl}/refresh-token`,
      { refresh_token: this.refreshToken }
    ).pipe(
      tap((tokens) => {
        this.token = tokens.access_token;
        this.refreshToken = tokens.refresh_token;
      })
    );
  }
}