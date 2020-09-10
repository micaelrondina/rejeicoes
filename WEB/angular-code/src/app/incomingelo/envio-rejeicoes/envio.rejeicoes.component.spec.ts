import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnvioRejeicoesComponent } from './envio.rejeicoes.component';

describe('EnvioRejeicoesComponent', () => {
  let component: EnvioRejeicoesComponent;
  let fixture: ComponentFixture<EnvioRejeicoesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnvioRejeicoesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnvioRejeicoesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
