import { HttpClientModule } from '@angular/common/http';
import { JobService } from './services/job.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { HomeComponent } from './components/home/home.component';
import { CustomerComponent } from './components/customer/customer.component';
import { PartsComponent } from './components/parts/parts.component';
import { EmployeeComponent } from './components/employee/employee.component';
import { DepartmentsComponent } from './components/departments/departments.component';
import { PlbgComponent } from './components/plbg/plbg.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { JobAssignedPipe } from './pipes/job-assigned.pipe';

@NgModule({
  declarations: [
    AppComponent,
    PlbgComponent,
    NavBarComponent,
    HomeComponent,
    CustomerComponent,
    PartsComponent,
    EmployeeComponent,
    DepartmentsComponent,
    NotFoundComponent,
    JobAssignedPipe,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [JobService],
  bootstrap: [AppComponent]
})
export class AppModule { }
