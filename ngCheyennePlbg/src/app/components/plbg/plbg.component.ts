import { Component, OnInit } from '@angular/core';
import { Job } from 'src/app/models/job';
import { JobService } from 'src/app/services/job.service';

@Component({
  selector: 'app-plbg',
  templateUrl: './plbg.component.html',
  styleUrls: ['./plbg.component.css']
})
export class PlbgComponent implements OnInit {
jobs: Job [] = [];
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


  constructor(private jServ: JobService) { }

  ngOnInit(): void {
    this.loadJobs();
  }
  
loadJobs(): void {
  this.jServ.index().subscribe(data=>{
    this.jobs = data;
  }, err=>{});
}

setJob(job){
  this.selected = job;
}

dropdownExpand(){

  if (this.dropCount % 2 == 0){
    this.show = '';
    this.dropExpand = false;
  }else{
    this.show = 'show';
    this.dropExpand = true;
  }
  this.dropCount++;
}

}
