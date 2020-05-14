import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { AppRoutingModule } from '../app-routing.module';



@NgModule({
  declarations: [NavbarComponent],
  exports: [NavbarComponent],       // Para poder exportar los componentes
  imports: [
    CommonModule,
    AppRoutingModule                // Para que funcionen las rutas
  ]
})
export class LayoutModule { }
