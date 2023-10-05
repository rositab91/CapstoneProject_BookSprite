import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {GooglebooksComponent} from "./googlebooks/googlebooks.component";
import {GooglebookDetailsComponent} from "./googlebook-details/googlebook-details.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {AuthGuard} from "./shared/AuthGuard";
import {MybooksComponent} from "./mybooks/mybooks.component";

const routes: Routes = [
  {
    path: '',
    component: RegisterComponent
  },
  {
    path: 'googlebooks',
    component: GooglebooksComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'googlebookdetails/:title',
    component: GooglebookDetailsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'mybooks',
    component: MybooksComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'register',
    component: RegisterComponent
  },{
    path: 'login',
    component: LoginComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
