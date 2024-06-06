import { Routes } from '@angular/router';
import { LoginComponent } from './components/user/login/login.component';
import { RegisterComponent } from './components/user/register/register.component';
import { RegisterManagerComponent } from './components/user/registerManager/registerManager.component';
import { HomeComponent } from './shared/components/home/home.component';
import { LayoutComponent } from './shared/components/layout/layout.component';

export const routes: Routes = [
    {
        path: '',
        component: LayoutComponent,
        children: [
            {
              path: '',
              component: HomeComponent,
            },
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
