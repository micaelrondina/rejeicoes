import * as $ from 'jquery';
import { MediaMatcher } from '@angular/cdk/layout';
import { Router } from '@angular/router';
import {
  ChangeDetectorRef,
  Component,
  NgZone,
  OnDestroy,
  ViewChild,
  HostListener,
  Directive,
  AfterViewInit
} from '@angular/core';
import { AppHeaderComponent } from './header/header.component';
import { AppSidebarComponent } from './sidebar/sidebar.component';
import { MenuItems } from '../menu-items/menu-items';
import { LoggedUser } from '../../shared/models/logged-user';
import { SharedService } from '../../shared/shared.service';

/** @title Responsive sidenav */
@Component({
  selector: 'app-full-layout',
  templateUrl: 'full.component.html',
  styleUrls: ['full.component.css']
})
export class FullComponent implements OnDestroy, AfterViewInit {
  mobileQuery: MediaQueryList;

  private _mobileQueryListener: () => void;

  constructor(
    changeDetectorRef: ChangeDetectorRef,
    media: MediaMatcher,
    public menuItems: MenuItems
  ) {
    this.mobileQuery = media.matchMedia('(min-width: 768px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }
  ngAfterViewInit() {}
  
  getLoginName(): LoggedUser{
    return SharedService.getInstance().loggedUser;
  }
}
