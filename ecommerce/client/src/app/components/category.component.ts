import { Component, OnInit, inject } from '@angular/core';
import {Observable} from 'rxjs';
import {Product} from '../models';
import {ProductService} from '../product.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrl: './category.component.css'
})
export class CategoryComponent implements OnInit {

  // NOTE: you are free to modify this component

  private prodSvc = inject(ProductService)
  private activatedRoute = inject(ActivatedRoute)

  category: string = "not set"

  products$!: Observable<Product[]>

  ngOnInit(): void {
    this.category = this.activatedRoute.snapshot.params['category']
    this.products$ = this.prodSvc.getProductsByCategory(this.category)
  }
}
