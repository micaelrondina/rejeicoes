import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BatimentoComponent } from './batimento.component';

describe('BatimentoComponent', () => {
  let component: BatimentoComponent;
  let fixture: ComponentFixture<BatimentoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BatimentoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BatimentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
