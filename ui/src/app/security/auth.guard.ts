import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthGuardService implements CanActivate {

  constructor(private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (localStorage.getItem('user_dtls')) {
      console.log('User is not logged in');
      return true;
    }

    this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
    // this.router.navigate(['/login']);
    return false;
  }
}
