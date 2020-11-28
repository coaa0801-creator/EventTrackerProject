import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlbgComponent } from './plbg.component';

describe('PlbgComponent', () => {
  let component: PlbgComponent;
  let fixture: ComponentFixture<PlbgComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlbgComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlbgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
