import { Employee } from './../../models/employee';
import { EmployeeService } from './../../services/employee.service';
import { AddressService } from './../../services/address.service';
import { Address } from './../../models/address';
import { CustomerService } from './../../services/customer.service';
import { Customer } from './../../models/customer';
import { Component, OnInit } from '@angular/core';
import { Job } from 'src/app/models/job';
import { JobService } from 'src/app/services/job.service';

@Component({
  selector: 'app-plbg',
  templateUrl: './plbg.component.html',
  styleUrls: ['./plbg.component.css'],
})

export class PlbgComponent implements OnInit {
  jobs: Job[] = [];
  customers: Customer[] = [];
  addresses: Address [] = [];
  staff: Employee [] = [];
  errors: String[] = [];
  selected = null;
  jobPage = false;
  customerPage = false;
  partsPage = false;
  employeePage = false;
  plumbingPage = false;
  heatingPage = false;
  septicPage = false;
  updateJob = false;
  addJob = false;
  show = '';
  dropExpand: boolean = false;
  dropCount = 1;
  newCustomerAddJob = false;
  existingCustomerNewJob = false;
  newJobCustCount = 1;
  newJobExistingCustCount = 1;
  blankCustomer = new Customer();
  blankEmployee = new Employee();
  newJob = new Job();
  blankAddress = new Address();
  collapsed = 'collapsed';
  menuShow = '';
  menuExpand = false;
  menuCount = 1;
  newJobAddressCheck = false;
  editJob = null;
  editJobAddress = null;
  editJobCustomer = null;



  constructor(private jServ: JobService, private cServ: CustomerService, private aServ: AddressService, private eServ: EmployeeService) {}

  ngOnInit(): void {
    this.loadJobs();
    this.loadCustomers();
    this.loadAddresses();
    this.loadStaff();

  }
  reload(): void{
    this.loadJobs();
    this.loadCustomers();
    this.loadAddresses();
    this.loadStaff();

}

  loadJobs(): void {
    this.jServ.index().subscribe(
      (data) => {
        this.jobs = data;
        // console.log(this.jobs);
      },
      (err) => {}
    );

  }
  loadStaff(): void {
    this.eServ.index().subscribe(
      (data) => {
        this.staff = data;
        // console.log(this.jobs);
      },
      (err) => {}
    );

  }

  loadAddresses(): void {
    this.aServ.index().subscribe(
      (data) => {
        this.addresses = data;
        // console.log(this.addresses);
      },
      (err) => {}
    );

  }

  loadCustomers(): void {
    this.cServ.index().subscribe(
      (data) => {
        this.customers = data;
      },
      (err) => {}
    );
  }

setEditJob(j: Job){
  this.selected = j;
  this.editJob = j;
  // console.log(j.customer);

  this.editJobAddress = this.editJob.address;
  // console.log(this.editJobAddress.customer);

  this.editJobCustomer = this.editJob.customer;
}

  setNewJobExistingCustomerAddress(a: Address) {
    for(let i = 0; i < this.blankCustomer.addresses.length; i++){
    if(this.blankCustomer.id !== undefined && a.id !== this.blankCustomer.addresses[i].id){
      alert("This address does not belong to that Customer. Please choose a valid address");
      a = new Address();
      this.newJobAddressCheck = false;
    } else if (this.blankAddress.id !== undefined && a.id !== this.blankAddress.id) {
      let response = confirm(
        'You have already made a selection. Would you like to assign this Address to the current Job?'
        );
        if (response) {
          this.blankAddress= a;
        }
      }
      if (a == this.newJob.address) {
        this.resetEditAddress();
      } else {
        this.blankAddress = a;
      }
  }

}
  setJob(job) {
    this.selected = job;
  }

  menuHamExpand() {
    if (this.dropCount % 2 == 0) {
      this.collapsed = 'collapsed';
      this.menuShow = '';
      this.menuExpand = false;
    } else {
      this.collapsed = '';
      this.menuShow = 'show';
      this.menuExpand = true;
    }
    this.dropCount++;
  }
  dropdownExpand() {
    if (this.dropCount % 2 == 0) {
      this.show = '';
      this.dropExpand = false;
    } else {
      this.show = 'show';
      this.dropExpand = true;
    }
    this.dropCount++;
  }

setEditCustomer(c: Customer){
  this.blankCustomer = c;
}
resetEditCustomer(){
  this.blankCustomer = new Customer();
}
resetEditAddress(){
  this.blankAddress = new Address();
}
setEditAddress(a: Address){
  this.blankAddress = a;
}
resetBlankEmployee(){
  this.blankEmployee = new Employee();
}
  setNewCustomer() {
    if (this.newJobCustCount % 2 == 0) {
      this.newCustomerAddJob = false;
    } else {
      this.newCustomerAddJob = true;
    }
    this.newJobCustCount = this.newJobCustCount + 1;
  }
  setExistingCustomer() {
    if (this.newJobExistingCustCount % 2 == 0) {
      this.existingCustomerNewJob = false;
      this.resetEditCustomer();
    } else {
      this.existingCustomerNewJob = true;
    }

    this.newJobExistingCustCount = this.newJobExistingCustCount + 1;
  }
  checkNewCustomer(){
    return this.newCustomerAddJob;
  }
  checkExistingCustomer(){
    return this.existingCustomerNewJob;
  }

  setNewJobExistingCustomer(c: Customer) {

    if (this.blankCustomer.id !== undefined && c.id !== this.blankCustomer.id) {
      let response = confirm(
        'You have already made a selection. Would you like to assign this customer to the current Job?'
      );
      if (response) {
        this.blankCustomer = c;
      }
    } else if (c == this.blankCustomer) {
      this.resetEditCustomer();
    } else {
      this.blankCustomer = c;
    }
    // console.log(this.newCustomer);

  }
  updateJobEdit(){
    this.errors = [];
    if (this.editJob.name == null) {
      this.errors.push('Job Name field can not be empty');
    }
    if (this.editJob.customer.firstName == null) {
      this.errors.push('First Name field can not be empty');
    }
    if (this.editJob.customer.lastName == null) {
      this.errors.push('Last Name field can not be empty');
    }
    if (this.editJob.address.address == null) {
      this.errors.push('Street Address field can not be empty');
    }
    if (this.editJob.address.city == null) {
      this.errors.push('City field can not be empty');
    }
    if (this.editJob.address.state == null) {
      this.errors.push('State field can not be empty');
    }
    if (this.errors.length == 0) {





      this.jServ.update(this.editJob, this.editJobAddress, this.editJobCustomer).subscribe(
        (data) => {
          this.reload();
          this.selected = null;
          this.editJob = null;
          this.updateJob = false;

        },
        (err) => {}
        );





    }



  }

    createNewJob(){
      this.errors = [];
      if (this.newJob.name == null) {
        this.errors.push('Job Name field can not be empty');
      }
      if (this.blankCustomer.firstName == null) {
        this.errors.push('First Name field can not be empty');
      }
      if (this.blankCustomer.lastName == null) {
        this.errors.push('Last Name field can not be empty');
      }
      if (this.blankAddress.address == null) {
        this.errors.push('Street Address field can not be empty');
      }
      if (this.blankAddress.city == null) {
        this.errors.push('City field can not be empty');
      }
      if (this.blankAddress.state == null) {
        this.errors.push('State field can not be empty');
      }

      if (this.errors.length == 0) {

        this.newJob.address = this.blankAddress;
        this.newJob.customer = this.blankCustomer;


      this.jServ.create(this.newJob).subscribe(
        (data) => {
          this.reload();
          this.resetEditAddress();
          this.resetEditCustomer();
          this.newJob = new Job();
          this.addJob = false;
          this.existingCustomerNewJob = false;
          this.newCustomerAddJob = false;

        },
        (err) => {}
        );

      }
    }




    deleteJob(id: number){
      let response = false;
      response = confirm("Are you sure you want to delete the " + this.jobs[this.jobs.length - 1].name + " job?");
      if (response){
        this.jServ.destroy(id).subscribe(
          (data) => {
            this.resetEditAddress();
            this.resetEditCustomer();
            this.newJob = new Job();
            this.addJob = false;
            this.existingCustomerNewJob = false;
            this.newCustomerAddJob = false;
            this.selected = null;
           this.reload();
    },
    (err) => {}
    );
}
}










}
