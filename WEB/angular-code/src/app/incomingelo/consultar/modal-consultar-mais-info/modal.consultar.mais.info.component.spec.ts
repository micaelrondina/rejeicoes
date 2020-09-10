import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ModalConsultarMaisInfoComponent } from './modal.consultar.mais.info.component';

describe('ModalConsultarMaisInfoComponent', () => {
  let component: ModalConsultarMaisInfoComponent;
  let fixture: ComponentFixture<ModalConsultarMaisInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModalConsultarMaisInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalConsultarMaisInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
