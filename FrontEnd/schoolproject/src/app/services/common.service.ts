import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export abstract class CommonService<E> {
  
  protected headers: HttpHeaders = new HttpHeaders( { 'Content-Type': 'application/json' } );
  protected baseUrl: string;

  constructor( protected http: HttpClient ) { }
  
  public listing() : Observable<E[]> {
    return this.http.get<E[]>( this.baseUrl );
  }

  public listingPerPage( page: string, size: string ): Observable<any> {
    const params: HttpParams = new HttpParams()
      .set( 'page', page )
      .set( 'size', size );

    return this.http.get<any>( this.baseUrl + '/pagination', { params: params } );
  }
  // Metodos muy genericos
  // GET
  public get( id: number ) : Observable<E> {
    return this.http.get<E>( this.baseUrl + '/' + id );
  }
  // POST
  public save( e: E ) : Observable<E> {
    return this.http.post<E>( this.baseUrl, e, { headers: this.headers } );
  }
  // PUT
  public update( e: E ) : Observable<E> {
    return this.http.put<E>( this.baseUrl, e, { headers: this.headers } );
  }
  // DELETE
  public delete( id: number ): Observable<void> {
    return this.http.delete<void>( this.baseUrl + '/' + id );
  }

}