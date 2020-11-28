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


  constructor(private jServ: JobService) { }

  ngOnInit(): void {
    this.loadJobs();
  }
loadJobs():void{
  this.jServ.index().subscribe(data=>{
    this.jobs = data;
  }, err=>{});
}
setJob(job){
  this.selected = job;
}

}
