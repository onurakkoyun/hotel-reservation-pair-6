import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, GuardResult, MaybeAsync, Router, RouterStateSnapshot } from '@angular/router';
import { UserService } from '../services/user/user.service';
import { inject, Injectable } from '@angular/core';
import { first, map } from 'rxjs';
import { AUTH_ERROR_REDIRECT_URL_TOKEN } from '../core/auth/providers/auth.provider';
import { AuthService } from '../services/auth/auth.service';


export const roleGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  const authErrorRedirectUrl = inject(AUTH_ERROR_REDIRECT_URL_TOKEN);

  if (!authService.isAuthenticated) {
    router.navigateByUrl(authErrorRedirectUrl);
    return false;
  }

  const roles = route.data['roles'] as Array<string>;
  if (roles && !authService.isAuthorized(roles)) {
    router.navigateByUrl(authErrorRedirectUrl);
    // If user role is not allowed for this route, redirect to unauthorized page
    //router.navigate(['/unauthorized']);
    return false;
  }
  /* return userService.role.pipe(
    map(userRole => {
      if (!userRole) {
        // If no user is logged in, redirect to login page
        router.navigate(['/login']);
        return false;
      }

      const roles = route.data['roles'] as Array<string>;
      if (roles && roles.indexOf(userRole) === -1) {
        // If user role is not allowed for this route, redirect to unauthorized page
        router.navigate(['/unauthorized']);
        return false;
      }

      // If user is logged in and has the right role, allow access
      return true;
    })
  ); */
  return true
};

