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
  newCustomer = new Customer();
  newJob = new Job();
  newAddress = new Address();
  collapsed = 'collapsed';
  menuShow = '';
  menuExpand = false;
  menuCount = 1;

  constructor(private jServ: JobService, private cServ: CustomerService, private aServ: AddressService) {}

  ngOnInit(): void {
    this.loadJobs();
    this.loadCustomers();
    this.loadAddresses();

  }

  loadJobs(): void {
    this.jServ.index().subscribe(
      (data) => {
        this.jobs = data;
      },
      (err) => {}
    );
  }
  loadAddresses(): void {
    this.aServ.index().subscribe(
      (data) => {
        this.addresses = data;
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
  setNewJobExistingCustomerAddress(a: Address) {
    if (this.newAddress.id !== undefined && a.id !== this.newAddress.id) {
      let response = confirm(
        'You have already made a selection. Would you like to assign this Address to the current Job?'
      );
      if (response) {
        this.newAddress= a;
      }
    }
    if (a == this.newJob.address) {
      this.newAddress = new Address();
    } else {
      this.newAddress = a;
    }
    // console.log(this.newAddress);

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
      this.newCustomer = new Customer();
    } else {
      this.existingCustomerNewJob = true;
    }

    this.newJobExistingCustCount = this.newJobExistingCustCount + 1;
  }

  setNewJobExistingCustomer(c: Customer) {

    if (this.newCustomer.id !== undefined && c.id !== this.newCustomer.id) {
      let response = confirm(
        'You have already made a selection. Would you like to assign this customer to the current Job?'
      );
      if (response) {
        this.newCustomer = c;
      }
    } else if (c == this.newCustomer) {
      this.newCustomer = new Customer();
    } else {
      this.newCustomer = c;
    }
    // console.log(this.newCustomer);

  }

  createNewAddress() {

    if (this.newJob.name == null) {
      this.errors.push('Job Name field can not be empty');
    }
    if (this.newCustomer.firstName == null) {
      this.errors.push('First Name field can not be empty');
    }
    if (this.newCustomer.lastName == null) {
      this.errors.push('Last Name field can not be empty');
    }
    if (this.newAddress.address == null) {
      this.errors.push('Street Address field can not be empty');
    }
    if (this.newAddress.city == null) {
      this.errors.push('City field can not be empty');
    }
    if (this.newAddress.state == null) {
      this.errors.push('State field can not be empty');
    }

    if (this.errors.length == 0) {
      if (this.newAddress.id == undefined){
        // console.log(this.newAddress);

        this.aServ.create(this.newAddress).subscribe(
          (data) => {
            this.loadAddresses();
          },
          (err) => {}
          );
        }
        this.createNewCustomer();
      }}

      createNewCustomer(){
        this.loadAddresses();
        let index = (this.addresses.length - 1);
        if (this.addresses[index].address === this.newAddress.address){
          const newAddress = this.addresses[index];
            this.newCustomer.addresses.push(newAddress);
          this.newJob.address = newAddress;
      }


      if ((this.newCustomer.id == undefined)) {
        this.cServ.create(this.newCustomer).subscribe(
          (data) => {
            this.loadCustomers();

          },
          (err) => {}
        );
      }
      this.createNewJob();
    }


    createNewJob(){
      this.loadCustomers();
      if (this.customers[this.customers.length - 1].firstName === this.newCustomer.firstName && this.customers[this.customers.length - 1].lastName === this.newCustomer.lastName ) {
        this.newJob.customer = this.customers[this.customers.length - 1];
      }
      console.log(this.newJob.customer);

console.log(this.newJob.address);

      this.jServ.create(this.newJob).subscribe(
        (data) => {
          this.loadJobs();
          this.newAddress = new Address();
          this.newCustomer = new Customer();
          this.newJob = new Job();
          this.addJob = false;
          this.existingCustomerNewJob = false;
          this.newCustomerAddJob = false;
          // location.reload();
        },
        (err) => {}
        );

      }





deleteJob(id: number){
let response = false;
response = confirm("Are you sure you want to delete the " + this.jobs[id - 1].name + " job?");
if (response){
  this.jServ.destroy(id).subscribe(
    (data) => {
      this.loadJobs();
      location.reload();
    },
    (err) => {}
    );
}
}










}
