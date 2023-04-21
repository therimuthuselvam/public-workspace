import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { HardcodedAuthenticationService } from './hardcoded-authentication.service';

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService implements CanActivate {

  notLoggedInURLTyping = false //This property is created by me to give warning message while typing URL without login

  constructor(private hardcodedAuthenticationService: HardcodedAuthenticationService,
    private router: Router
  ) { }


  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.hardcodedAuthenticationService.isUserLoggedIn()) {
      return true
    }
    console.log('You have to login first')
    this.router.navigate(['login'])
    this.notLoggedInURLTyping = true //added by me
    return false
  }

}
