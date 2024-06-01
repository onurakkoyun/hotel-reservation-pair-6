import { HttpInterceptorFn } from '@angular/common/http';
import { AuthService } from '../services/auth/auth.service';
import { inject } from '@angular/core';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);

  // Eğer request'i değiştirmek istiyorsak yeni bir request (referans) oluşturmalıyız.
  const newReq = req.clone({
    headers: req.headers.set('Authorization', `Bearer ${authService.token}`),
  });
  return next(newReq);
};

export class JwtInterceptor {
  intercept = jwtInterceptor;
}