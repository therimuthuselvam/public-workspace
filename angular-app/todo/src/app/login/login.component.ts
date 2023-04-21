import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BasicAuthenticationService } from '../service/basic-authentication.service';
import { HardcodedAuthenticationService } from '../service/hardcoded-authentication.service';
import { RouteGuardService } from '../service/route-guard.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = 'in28minutes'
  password = ''
  errorMessage = 'Invalid Credentials'
  invalidLogin = false
  notLoggedInMessage = 'You have to login First' //added by me

  constructor(private router: Router,
    private hardcodedAuthenticationService: HardcodedAuthenticationService,
    public routerGurardService: RouteGuardService,
    private basicAuthenticationService: BasicAuthenticationService,
  ) { }

  ngOnInit(): void {
    if (sessionStorage.getItem('authenticaterUser') !== null) { // added by me
      //console.log('you have to logout first')//
      this.router.navigate(['/todos'])// added by me
    }
  }

  handleLogin() {
    if (this.hardcodedAuthenticationService.authenticate(this.username, this.password)) {
      this.invalidLogin = false
      //Redirect to welcome page
      this.router.navigate(['welcome', this.username])
    }
    else {
      this.invalidLogin = true
    }
  }

  handleBasicAuthLogin() {
    this.basicAuthenticationService.executeAuthenticationService(this.username, this.password).subscribe(
      data =>{
        console.log(data)
        this.invalidLogin = false
        //Redirect to welcome page
        this.router.navigate(['welcome', this.username])
      },

      error =>{
        console.log(error)
        this.invalidLogin = true
      }
    )
  }

  handleJWTAuthLogin() {
    this.basicAuthenticationService.executeJWTAuthenticationService(this.username, this.password).subscribe(
      data =>{
        console.log(data)
        this.invalidLogin = false
        //Redirect to welcome page
        this.router.navigate(['welcome', this.username])
      },

      error =>{
        console.log(error)
        this.invalidLogin = true
      }
    )
  }

}
