import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ConsultarArqRetPaywareComponent } from './retorno.arq.payware.component';

describe('ConsultarArqRetPaywareComponent', () => {
  let component: ConsultarArqRetPaywareComponent;
  let fixture: ComponentFixture<ConsultarArqRetPaywareComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsultarArqRetPaywareComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultarArqRetPaywareComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
