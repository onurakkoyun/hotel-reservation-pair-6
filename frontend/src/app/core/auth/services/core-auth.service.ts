export interface CoreAuth { }
import { Inject, Injectable } from '@angular/core';
import { AccessTokenPayload } from '../models/access-token-payload';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { DOCUMENT } from '@angular/common';

@Injectable({
  providedIn: 'root',
})
export class CoreAuthService {
  // Subject: TS tarafında bir Observable'dır. Bu nedenle Subject sınıfı, Observable sınıfının bir alt sınıfıdır. Subject'e subscribe olan dinleyiciler sonraki çağrıları -next, error, complete- yakalabilirler ve eğer bir değer de geçiliyorsa next çağrısından sonra bu değeri alabilirler.
  protected readonly _logged = new Subject<void>();

  public get logged(): Observable<void> {
    return this._logged.asObservable();
  }

  protected readonly _loggedOut = new Subject<void>();
  
  public get loggedOut(): Observable<void> {
    return this._loggedOut.asObservable();
  }

  // BehaviorSubject: Subject sınıfının bir alt sınıfıdır. BehaviorSubject, bir başlangıç değeri alır ve bu değeri subscribe olan dinleyicilere hemen iletir. Daha sonra yeni değerler geldiğinde bu değerleri dinleyicilere iletir.
  protected readonly _isLogged = new BehaviorSubject<boolean>(
    this.isAuthenticated
  );
  public get isLogged(): Observable<boolean> {
    return this._isLogged.asObservable();
  }

  protected readonly _registered = new Subject<void>();
  public get registered(): Observable<void> {
    return this._registered.asObservable();
  }

  protected readonly _isRegistered = new BehaviorSubject<boolean>(
    this.isAuthenticated
  );

  public get isRegistered(): Observable<boolean> {
    return this._isRegistered.asObservable();
  }

  protected readonly _registeredManager = new Subject<void>();
  public get registeredManager(): Observable<void> {
    return this._registeredManager.asObservable();
  }

  protected readonly _isRegisteredManager = new BehaviorSubject<boolean>(
    this.isAuthenticated
  );

  public get isRegisteredManager(): Observable<boolean> {
    return this._isRegisteredManager.asObservable();
  }

  constructor(@Inject(DOCUMENT) protected document: Document) {}

  protected get localStorage(): Storage | undefined {
    return this.document.defaultView?.localStorage;
  }

  public get tokenPayload(): AccessTokenPayload | null {
    if (!this.token) return null;

    const encodedPayload = this.token.split('.')[1];
    const decodedPayload = atob(encodedPayload);
    const payload = JSON.parse(decodedPayload) as AccessTokenPayload;

    return payload;
  }

  isTokenExpired(): boolean {
    // Implement logic to check if the current token is expired
    const nowUnixTimeInMilliseconds = Date.now();
    const nowUnitTimeInSeconds = Math.floor(nowUnixTimeInMilliseconds / 1000);
    if (nowUnitTimeInSeconds > this.tokenPayload!.exp) {
      return true;
    }
    return false;
  }

  public get isAuthenticated(): boolean {
    if (!this.token) return false;

    if (this.isTokenExpired()) {
      this.logoutHandler();
      return false;
    }

    return true;
  }

  // Role-based authorization was taken out of the project
  public isAuthorized(roles: String[]): boolean {
    if (!this.isAuthenticated) return false;

    const tokenRole = this.tokenPayload?.role;

    if (tokenRole && roles.includes(tokenRole)) {
      return true;
    }

    return false;
  }

  public logoutHandler(): void {
    this.localStorage?.removeItem('access_token');
    this.localStorage?.removeItem('refresh_token');
    this._loggedOut.next();
    this._isLogged.next(false);
  }

  public get token(): string | null {
    return this.localStorage?.getItem('access_token') ?? null;
  }

  protected set token(token: string) {
    this.localStorage?.setItem('access_token', token);
  }

  public get refreshToken(): string | null {
    return this.localStorage?.getItem('refresh_token') ?? null;
  }

  protected set refreshToken(token: string) {
    this.localStorage?.setItem('refresh_token', token);
  }

}