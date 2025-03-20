import { Component, Input, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CartStore } from '../cart.store';
import { LineItem } from '../models';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrl: './order-form.component.css'
})
export class OrderFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private cartStore = inject(CartStore);
  private prodSvc = inject(ProductService);

  @Input({ required: true })
  productId!: string;

  // Add inputs for product details
  @Input()
  productName: string = '';

  @Input()
  productPrice: number = 0;

  form!: FormGroup;

  ngOnInit(): void {
    this.form = this.createForm();
  }

  addToCart() {
    console.log('Adding product to cart with ID:', this.productId);

    const lineItem: LineItem = {
      prodId: this.productId,
      quantity: this.form.value['quantity'],
      name: this.productName,
      price: this.productPrice
    };

    // Add the item to the cart store
    this.cartStore.addToCart(lineItem);
    
    // Reset the form
    this.form = this.createForm();
  }

  private createForm(): FormGroup {
    return this.fb.group({
      quantity: this.fb.control<number>(1, [Validators.required, Validators.min(1)])
    });
  }
}