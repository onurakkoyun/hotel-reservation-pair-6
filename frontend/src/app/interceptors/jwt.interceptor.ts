import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { AuthService } from '../services/auth/auth.service';
import { inject } from '@angular/core';
import { Observable } from 'rxjs';

export function jwtInterceptor(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
  const authService = inject(AuthService);
  
  if (req.url.includes('auth') && !req.url.endsWith('logout')){
    // Don't add the Authorization header
    return next.handle(req);
  }

  const newReq = req.clone({
    headers: req.headers.set('Authorization', `Bearer ${authService.token}`),
  });
  return next.handle(newReq);
};

export class JwtInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return jwtInterceptor(req, next);
  }
}