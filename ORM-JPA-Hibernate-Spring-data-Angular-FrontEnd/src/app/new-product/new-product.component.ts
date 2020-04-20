import {Component, OnInit} from '@angular/core';
import {CatalogueService} from '../services/catalogue.service';
import {rootRoute} from '@angular/router/src/router_module';
import {Router} from '@angular/router';
import {v} from '@angular/core/src/render3';

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.css']
})
export class NewProductComponent implements OnInit {

  public categories: any;
  private currentProductAdded: ProductsModels;
  saveOk = false;

  constructor(private  catalogueService: CatalogueService, private myRoute: Router) {
  }

  ngOnInit() {


    console.log('NewProductComponent ngOnInit')
    this.catalogueService.loadCategorie()
      .subscribe(
        value => {
          console.log(value);
          this.categories = value;
        },
        error => console.log(error));
  }


  onSaveProduit(form: any) {
    console.log(form);
    this.catalogueService.save(this.catalogueService.host + '/saveProduitWithCatgorie', form)
      .subscribe(
        value => {
          console.log(value);
         // this.myRoute.navigateByUrl('/products') ;
          this.currentProductAdded = value;
          this.saveOk = true;
        },
        error => {
          console.log(error);
          this.saveOk = false;
        }
      );

  }


  onNewProduit() {
    this.saveOk = false;
  }
}
