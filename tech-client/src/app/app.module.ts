import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import { PaymentListComponent } from './payment-list/payment-list.component';
import { DealListComponent } from './deal-list/deal-list.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { EnterEventComponent } from './enter-event/enter-event.component';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    PaymentListComponent,
    DealListComponent,
    EnterEventComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
