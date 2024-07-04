import { Routes } from '@angular/router';
import { LoginComponent } from './components/user/login/login.component';
import { RegisterComponent } from './components/user/register/register.component';
import { RegisterManagerComponent } from './components/user/registerManager/registerManager.component';
import { HomeComponent } from './shared/components/home/home.component';
import { LayoutComponent } from './shared/components/layout/layout.component';
import { PaymentComponent } from './components/payment/payment/payment.component';
import { HotelDetailComponent } from './components/hotel/hotel-detail/hotel-detail.component';
import { ReservationListComponent } from './components/reservation/reservation-list/reservation-list.component';
import { ReservationDetailComponent } from './components/reservation/reservation-detail/reservation-detail.component';
import { ReviewComponent } from './components/review/review.component';
import { DashboardComponent } from './components/reporting/dashboard/dashboard.component';

export const routes: Routes = [
    {
        path: '',
        component: LayoutComponent,
        children: [
            {
              path: '',
              component: HomeComponent,
            },
            {
                path: 'payments',
                component: PaymentComponent,
            },
            {
                path: 'hotels/:id',
                component: HotelDetailComponent,
            },
            {
                path: 'reservations',
                component: ReservationListComponent,
            },
            {
                path: 'reservations/:id',
                component: ReservationDetailComponent,
            },
            {
                path: 'reviews/:hotelId',
                component: ReviewComponent,
            },
            {
                path: 'reports/:hotelId',
                component: DashboardComponent,
                canActivate: [],
            },
            {
                path: 'management/:managerId',
                component: DashboardComponent,
            }


        ]
    },
    {
        path: 'login',
        component: LoginComponent,
    },
    {
        path: 'guests/register',
        component: RegisterComponent,
    },
    {
        path: 'managers/register',
        component: RegisterManagerComponent,
    },
];
