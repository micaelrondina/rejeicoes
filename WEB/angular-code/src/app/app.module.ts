import { LoaderInterceptorService } from './shared/services/loader-interceptor.service';
import { LoaderComponent } from './layouts/loader/loader.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LocationStrategy, PathLocationStrategy, registerLocaleData } from '@angular/common';
import { AppComponent } from './app.component';

import { FlexLayoutModule } from '@angular/flex-layout';
import { FullComponent } from './layouts/full/full.component';
import { AppHeaderComponent } from './layouts/full/header/header.component';
import { AppSidebarComponent } from './layouts/full/sidebar/sidebar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material-module';

import { SpinnerComponent } from './layouts/spinner.component';
import { AppRoutingModule } from './app.routing.module';
import { PaginaNaoEncontradaComponent } from './shared/pagina-nao-encontrada/pagina-nao-encontrada.component';
import { LayoutsModule } from './layouts/layouts.module';
import { AuthGuard } from './shared/auth.guard';
import { SharedService } from './shared/shared.service';
import { LoggedUserService } from './shared/services/logged-user.service';
import { ParametroService } from './shared/services/parametro.service';
import { LogoffService } from './shared/services/logoff.service';
import ptBr from '@angular/common/locales/pt';
import { ExcelService } from './shared/services/excel.service';
registerLocaleData(ptBr) //sem isso dao erro

@NgModule({
  declarations: [
    AppComponent,
    FullComponent,
    AppHeaderComponent,
    SpinnerComponent,
    AppSidebarComponent,
    LoaderComponent,
    PaginaNaoEncontradaComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    FlexLayoutModule,
    HttpClientModule,
    LayoutsModule,
    AppRoutingModule
  ],
  providers: [
    LoggedUserService,
    ParametroService,
    LogoffService,
    AuthGuard,
    SharedService,
    ExcelService,
    {
      provide: LocationStrategy,
      useClass: PathLocationStrategy
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: LoaderInterceptorService,
      multi: true
    },
    { provide: LOCALE_ID, useValue: 'pt-PT' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
