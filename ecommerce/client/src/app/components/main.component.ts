import { Component, OnInit, inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent implements OnInit {

  // NOTE: you are free to modify this component

  private prodSvc = inject(ProductService)
  private router = inject(Router)

  categories$!: Observable<string[]>

  ngOnInit(): void {
    this.categories$ = this.prodSvc.getProductCategories()
  }

  viewCategory(category: string) {
    this.router.navigate(['/category', category])
  }

}
