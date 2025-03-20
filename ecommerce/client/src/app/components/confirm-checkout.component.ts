import { HttpClient } from '@angular/common/http';
import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CartStore } from '../cart.store';
import { LineItem } from '../models';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-confirm-checkout',
  templateUrl: './confirm-checkout.component.html',
  styleUrl: './confirm-checkout.component.css'
})
export class ConfirmCheckoutComponent implements OnInit {
  private cartStore = inject(CartStore);
  private fb = inject(FormBuilder);
  private prodSvc = inject(ProductService);
  private router = inject(Router);
  private http = inject(HttpClient);

  lineItems: LineItem[] = [];
  orderForm!: FormGroup;
  
  constructor() {
    this.orderForm = this.fb.group({
      name: ['', [Validators.required]],
      address: ['', [Validators.required]],
      priority: [false],
      comments: ['']
    });
  }

  ngOnInit(): void {
    // Get the line items from the cart store
    this.cartStore.lineItems$.subscribe(items => {
      this.lineItems = items;
    });
  }

  // Calculate the total price for a line item
  calculateItemTotal(item: LineItem): number {
    return item.price * item.quantity;
  }

  // Calculate the total price for all items
  calculateTotal(): number {
    return this.lineItems.reduce((total, item) => {
      return total + this.calculateItemTotal(item);
    }, 0);
  }

  // Submit the order
  submitOrder(): void {
    if (this.orderForm.valid) {
      // Transform line items to match backend expectations
      // The key change is mapping 'prodId' to 'productId' for the backend
      const transformedLineItems = this.lineItems.map(item => {
        return {
          productId: item.prodId, // Map prodId to productId for Java backend
          name: item.name || 'Unknown Product',
          quantity: item.quantity || 1,
          price: item.price || 0
        };
      });
      
      const order: any = {
        name: this.orderForm.value.name,
        address: this.orderForm.value.address,
        priority: this.orderForm.value.priority || false,
        comments: this.orderForm.value.comments || '',
        cart: {
          lineItems: transformedLineItems
        }
      };
      
      // Log the order object for debugging
      console.log('Sending order:', JSON.stringify(order));
      
      // Submit the order using HTTP
      this.http.post<any>('/api/order', order).subscribe({
        next: (response) => {
          console.log('Order placed successfully', response);
          alert(`Order placed successfully! Your order ID is: ${response.orderId}`);
          this.cartStore.clearCart();
          this.router.navigate(['/']);
        },
        error: (error) => {
          console.error('Error placing order', error);
          // Show more detailed error information
          let errorMsg = 'There was a problem placing your order.';
          if (error.error && error.error.error) {
            errorMsg += ' ' + error.error.error;
          }
          alert(errorMsg);
        }
      });
    }
  }
}