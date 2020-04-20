import {Component, OnInit} from '@angular/core';
import {Form} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {CatalogueService} from '../services/catalogue.service';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  // form: Form ;
  private urlEncoded: string;
  private urlDencoded: string;
  public currentProduit: ProductsModels;


  constructor(private router: Router, private  activateRoute: ActivatedRoute, private catalogueSevice: CatalogueService) {
  }

  ngOnInit() {

    console.log('-------------- hello ');

    console.log(this.activateRoute);

    if (this.activateRoute.snapshot.params['idparam'] > -1) {
      return;
    }


    // urldecode
    this.urlEncoded = this.activateRoute.snapshot.params['idparam'];
    this.urlDencoded = atob(this.urlEncoded);
    console.log(this.urlDencoded);

    // initialise le formulaire html d'edition
    this.catalogueSevice.getResource(this.urlDencoded)
      .subscribe(value => {
        this.currentProduit = value;
      }, error => {
        console.log(error);
      });


  }


  onEditProduit(value: ProductsModels) {
    this.catalogueSevice.patchProduit(this.urlDencoded, this.currentProduit)
      .subscribe(value => {
        alert('Mise à jours effectué avec succes');
        this.router.navigateByUrl('/products');
        console.log(value);
      }, error => {
        console.log(error);
      });
  }
}
