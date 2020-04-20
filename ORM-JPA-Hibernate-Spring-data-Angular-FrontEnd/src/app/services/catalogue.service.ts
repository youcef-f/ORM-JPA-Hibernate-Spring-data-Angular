import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {observable, Observable, throwError} from 'rxjs';
import {catchError, retry} from 'rxjs/operators';

@Injectable({
  providedIn: 'root' /* l'ajoute au provider dans app.module.ts */
})
export class CatalogueService {

  public host = 'http://localhost:8080';


  constructor(private httpClient: HttpClient) {
  }

  public getProduct(page: number, size: number): Observable<object> {
    return this.httpClient.get(this.host + '/produits?page=' + page + '&size=' + size);
  }

  public getProductByKeyword(keyword: string, page: number, size: number): Observable<object> {
    // tslint:disable-next-line:max-line-length
    return this.httpClient.get(this.host + '/produits/search/byDesginationContainPage?design=' + keyword + '&page=' + page + '&size=' + size);
  }



  public deleleResource(url: string): Observable<object> {
    return this.httpClient.delete(url);
  }


  public loadCategorie(): Observable<object> {
    return this.httpClient.get(this.host + '/categories');
  }


  save(url: string, produit: ProductsModels): Observable<ProductsModels> {
    return this.httpClient.post<ProductsModels>(url, produit);
  }

  public getResource(url: string): Observable<ProductsModels> {
    return this.httpClient.get<ProductsModels>(url);
  }
  public patchProduit(url: string, produit): Observable<ProductsModels> {
    return this.httpClient.patch<ProductsModels>(url, produit);
  }

}
