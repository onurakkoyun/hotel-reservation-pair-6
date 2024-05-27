import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

// Translation imports
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { HttpClient } from '@angular/common/http';

// Interceptor imports
import { JwtInterceptor } from './interceptors/jwt.interceptor';

// Modules
import { UserModule } from './components/user/user.module';
import { HotelModule } from './components/hotel/hotel.module';
import { ReservationModule } from './components/reservation/reservation.module';
import { PaymentModule } from './components/payment/payment.module';
import { ReportingModule } from './components/reporting/reporting.module';
import { ServicesModule } from './services/services.module';
import { GuardsModule } from './guards/guards.module';
import { InterceptorsModule } from './interceptors/interceptors.module';
import { ModelsModule } from './models/models.module';
import { UtilitiesModule } from './utilities/utilities.module';

// AoT requires an exported function for factories
export function HttpLoaderFactory(http: HttpClient): TranslateHttpLoader {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    //AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    UserModule,
    HotelModule,
    ReservationModule,
    PaymentModule,
    ReportingModule,
    ServicesModule,
    GuardsModule,
    InterceptorsModule,
    ModelsModule,
    UtilitiesModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }
  ],
  bootstrap:[]//[AppComponent]
})
export class AppModule { }
