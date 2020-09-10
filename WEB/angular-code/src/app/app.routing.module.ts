import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PaginaNaoEncontradaComponent } from './shared/pagina-nao-encontrada/pagina-nao-encontrada.component';
import { FullComponent } from './layouts/full/full.component';

const appRoutes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full'},
  { path: 'none', component: PaginaNaoEncontradaComponent},
  {
    path: '', component: FullComponent, 
    children: [
      {
        path: '',
        loadChildren: './incomingelo/incomingelo.module#IncomingELOModule'
      }
    ]
  },
  { path: '**', redirectTo: 'none' }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }