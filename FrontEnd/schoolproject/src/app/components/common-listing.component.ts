import { OnInit, ViewChild } from '@angular/core';

import Swal from 'sweetalert2'
import { PageEvent, MatPaginator } from '@angular/material/paginator';
import { CommonService } from '../services/common.service';
import { Generic } from '../models/generic';

export abstract class CommonListingComponent<E extends Generic, S extends CommonService<E>> implements OnInit {

  public tittle: string;
  protected modelName: string;
  public list: E[] = [];
  
  // Table pagination config
  public totalRows: number = 0;
  public pageIndex: number = 0;
  public pageSize: number = 5;
  public pageSizeOptions: number[] = [5, 10, 25, 50];

  @ViewChild( MatPaginator ) paginator: MatPaginator; 

  // Se puede inyectar dependencias de esta forma
  constructor( protected studentService: S ) {
  }

  ngOnInit(): void {
    this.getStudents();
  }

  public pagination( event: PageEvent ): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.getStudents();
  }

  private getStudents() {
    this.studentService.listingPerPage( this.pageIndex.toString(), this.pageSize.toString() )
      .subscribe( content => { 
          this.list = content.content as E[];
          this.totalRows = content.totalElements as number;
          this.paginator._intl.itemsPerPageLabel = 'Registros por página:';
          //this.paginator._intl.
      } );
  }

  public delete( e: E ): void {
    Swal.fire( {
      title: 'Confirmar eliminar',
      text: `¿Esta seguro que desea eliminar a ${ e.name }?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminar!'
    } ).then( ( result ) => {
      if( result.value ) {
        this.studentService.delete( e.id ).subscribe( () => {
          this.getStudents();
          Swal.fire( 'Eliminado: ', `${ this.modelName } ${ e.name } eliminado con exito.`, 'success' );
        } );  
      }
    } );
  }
}
