import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FiltroStatusTransacaoComponent } from './filtro.status.transacao.component';

describe('FiltroStatusTransacaoComponent', () => {
  let component: FiltroStatusTransacaoComponent;
  let fixture: ComponentFixture<FiltroStatusTransacaoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FiltroStatusTransacaoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FiltroStatusTransacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
