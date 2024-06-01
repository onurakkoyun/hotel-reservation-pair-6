import { Routes } from '@angular/router';
import { LoginComponent } from './components/user/login/login.component';
import { RegisterComponent } from './components/user/register/register.component';
import { RegisterManagerComponent } from './components/user/registerManager/registerManager.component';

export const routes: Routes = [
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
