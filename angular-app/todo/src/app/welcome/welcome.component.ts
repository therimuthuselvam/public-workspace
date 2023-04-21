import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppComponent } from '../app.component';
import { HelloWorldBean, WelcomeDataService } from '../service/data/welcome-data.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  welcomeMessage = 'Hi Guys Iam welcome Component content';
  name = ''
  welcomeMessageFromService: string = ''
  errorMessageFromService: string = ''

  constructor(private route: ActivatedRoute,
    private service: WelcomeDataService
  ) { }

  ngOnInit(): void {
    //console.log(this.welcomeMessage)
    this.name = this.route.snapshot.params['name']
  }

  getWelcomeMessage() {
    //console.log(this.service.executeHelloWorldBeanService())
    this.service.executeHelloWorldBeanService().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    )
  }

  getWelcomeMessageWithParameter() {
    //console.log(this.service.executeHelloWorldBeanService())
    this.service.executeHelloWorldServiceWithPathVariable(this.name).subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    )
  }

  handleSuccessfulResponse(response: any) {
    this.errorMessageFromService = ''
    this.welcomeMessageFromService = response.message
  }

  handleErrorResponse(error: any) {
    this.welcomeMessageFromService = ''
    this.errorMessageFromService = error.error.message
  }

}
