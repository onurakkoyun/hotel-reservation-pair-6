import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHandlerFn } from '@angular/common/http';
import { AuthService } from '../services/auth/auth.service';
import { inject } from '@angular/core';
import { Observable } from 'rxjs';

export function jwtInterceptor(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
  const authService = inject(AuthService);
  const token = authService.token;

  if (req.url.includes('auth') && !req.url.endsWith('logout')){
    // Don't add the Authorization header
    return next.handle(req);
  }

  if (token) {
    console.log('Adding token to request');
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }
  return next.handle(req);
};

export class JwtInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return jwtInterceptor(req, next);
  }
}