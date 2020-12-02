import { DepartmentsComponent } from './components/departments/departments.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CustomerComponent } from './components/customer/customer.component';
import { HomeComponent } from './components/home/home.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { PartsComponent } from './components/parts/parts.component';
import { PlbgComponent } from './components/plbg/plbg.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'home' },
{ path: 'home', component: HomeComponent },
{ path: 'jobs', component: PlbgComponent },
{ path: 'customers', component: CustomerComponent },
{ path: 'parts', component: PartsComponent },
{ path: 'departments/plumbing', component: DepartmentsComponent },
{ path: 'departments/heating', component: DepartmentsComponent },
{ path: 'departments/septic', component: DepartmentsComponent },
{ path: '**', component: NotFoundComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
