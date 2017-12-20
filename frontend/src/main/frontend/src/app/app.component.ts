import { Component } from '@angular/core';
import { OnInit } from '@angular/core';

import { HelloService } from '../shared/hello.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Sous Chef';

  constructor(
  	private helloService: HelloService
  	) {}

  sayHello() {
  	this.helloService.getHello()
  		.subscribe(data => console.log(data['response']));
  }

  ngOnInit() {
  	this.sayHello();
  }
}
