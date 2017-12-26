import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { UserApiService } from './user-api.service';
import { LoginService } from './login.service';
import { LoginComponent } from './login/login.component';
import { AppRoutingModule } from './/app-routing.module';
import { RegistrationComponent } from './registration/registration.component';
import { LandingComponent } from './landing/landing.component';
import { NavComponent } from './nav/nav.component';
import { HttpClientModule } from '@angular/common/http';
import { ProfileService } from './profile.service';
import { AuthGuard } from './auth.guard';
import { AsyncLocalStorageModule } from 'angular-async-local-storage';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    LandingComponent,
    NavComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    AsyncLocalStorageModule
  ],
  providers: [UserApiService, LoginService, ProfileService, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
