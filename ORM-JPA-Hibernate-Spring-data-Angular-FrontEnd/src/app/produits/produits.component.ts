import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CatalogueService} from '../services/catalogue.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-produits',
  templateUrl: './produits.component.html',
  styleUrls: ['./produits.component.css']
})
export class ProduitsComponent implements OnInit {

  produits: any;

  public size = 5;
  public currentPage = 0;
  public totalPages = 0;
  public pages: Array<number>;
  public currentkeyword = '';
  private conf: boolean;
  private url: string;
  private urlencode: string;

  constructor(private  catalogueService: CatalogueService, private myRoute: Router) {
  }

  ngOnInit() {
  }

  onGetProducts() {
    this.currentkeyword = '';
    // this.currentPage = 0;
    this.catalogueService.getProduct(this.currentPage, this.size)
      .subscribe(
        value => {
          this.produits = value;
          /*this.totalPages = this.produits.total.totalPages ;*/

          this.totalPages = value['page'].totalPages;

          this.pages = new Array<number>(this.totalPages);  // utiliser un array car totalpages:number  n'est pas itéraible dans *ngfor
        },
        error => {
          console.log(error);
        }
      );
  }

  onPageProducts(currentPage: number) {
    this.currentPage = currentPage;
    this.chercherProduit();
  }

  onChercher(form: any) {
    this.currentPage = 0;
    this.currentkeyword = form.keyword;
    this.chercherProduit();
  }

  chercherProduit() {
    this.catalogueService.getProductByKeyword(this.currentkeyword, this.currentPage, this.size)
      .subscribe(
        value => {
          this.produits = value;
          /*this.totalPages = this.produits.total.totalPages ;*/
          this.totalPages = value['page'].totalPages;
          this.pages = new Array<number>(this.totalPages);  // utiliser un array car totalpages:number  n'est pas itéraible dans *ngfor
        },
        error => {
          console.log(error);
        }
      );


  }

  /*
  {
"_embedded": {
"produits": [
{
"id": 4,
"designation": "Ordinateur LX567",
"prix": 7000,
"quantite": 7,
"_links": {
"self": {
"href": "http://localhost:8080/produits/4"
   */

  public onDeleteProduit(produit: any) {
    // tslint:disable-next-line:max-line-length

    this.conf = confirm('Etes vous sur de vouloir supprimer le produit?');
    // tslint:disable-next-line:triple-equals
    if (!this.conf) {
      return;
    }

    console.log(produit);
    this.catalogueService.deleleResource(produit._links.self.href)
      .subscribe(value => {
        this.chercherProduit();
      }, error => {
        console.log(error);
      });
  }


  onEditProduit(produit: any) {

    // tslint:disable-next-line:comment-format
    //this.myRoute.navigateByUrl('/edit-Product/' + produit.id);  // transmission de 'ID produit' dans l'url

    // ou  encoder 'url=_links.self.href' en tant que prametre pour référencer directemet le lien '
    /*
{
"_embedded": {
"produits": [
{
"id": 4,
"designation": "Ordinateur LX567",
"prix": 7000,
"quantite": 7,
"_links": {
   "self": {
      "href": "http://localhost:8080/produits/4"
 */
    this.url = produit._links.self.href;
    this.urlencode = btoa(this.url);
    console.log(this.url + ' ' + this.urlencode);
   this.myRoute.navigateByUrl('/edit-product/' + this.urlencode);
    console.log('----------- navigation produit');
  }
}
