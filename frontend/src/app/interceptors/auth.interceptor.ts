import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, switchMap } from 'rxjs/operators';
import { AuthService } from '../services/auth/auth.service';

//  Interceptor for handling expired access tokens

/* @Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!this.authService.isTokenExpired()) {
      return next.handle(req);
    }

    return this.authService.refreshAccessToken().pipe(
      switchMap((token: string) => {
        const authReq = req.clone({ headers: req.headers.set('Authorization', `Bearer ${token}`) });
        return next.handle(authReq);
      }),
      catchError((error) => {
        // Handle refresh token expiration (e.g., logout or redirect to login)
        return throwError(() => new Error(error));
      })
    );
  }
} */