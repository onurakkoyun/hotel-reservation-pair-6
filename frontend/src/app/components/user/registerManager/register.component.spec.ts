import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterManagerComponent } from './registerManager.component';

describe('RegisterComponent', () => {
  let component: RegisterManagerComponent;
  let fixture: ComponentFixture<RegisterManagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterManagerComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegisterManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
