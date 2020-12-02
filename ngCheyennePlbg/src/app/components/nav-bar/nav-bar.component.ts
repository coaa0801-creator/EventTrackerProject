import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  collapsed = 'collapsed';
  menuShow = '';
  menuExpand = false;
  menuCount = 1;
  constructor() { }
  dropExpand: boolean = false;
  dropCount = 1;
  show = '';


  ngOnInit(): void {
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




}
