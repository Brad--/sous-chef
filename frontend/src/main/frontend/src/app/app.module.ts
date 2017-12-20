import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { HelloService } from '../shared/hello.service';


@NgModule({
  declarations: [
    AppComponent
  ],
  providers: [
  	HelloService
  ],
  imports: [
    BrowserModule,
    HttpModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
