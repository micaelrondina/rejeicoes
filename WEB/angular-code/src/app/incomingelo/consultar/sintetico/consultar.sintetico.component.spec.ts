import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarSinteticoComponent } from './consultar.sintetico.component';

describe('ConsultarSinteticoComponent', () => {
  let component: ConsultarSinteticoComponent;
  let fixture: ComponentFixture<ConsultarSinteticoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsultarSinteticoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultarSinteticoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
