import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatistiquesEquipementComponent } from './statistiques-equipement.component';

describe('StatistiquesEquipementComponent', () => {
  let component: StatistiquesEquipementComponent;
  let fixture: ComponentFixture<StatistiquesEquipementComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StatistiquesEquipementComponent]
    });
    fixture = TestBed.createComponent(StatistiquesEquipementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
