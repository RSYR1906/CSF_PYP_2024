import { HttpClient } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";
import { Observable } from "rxjs";
import { Order, Product } from "./models";

@Injectable()
export class ProductService {

  private http = inject(HttpClient);

  // IMPORTANT: DO NOT MODIFY THIS METHOD.
  // If this method is changed, any assessment task relying on this method will
  // not be marked
  getProductCategories(): Observable<string[]> {
    return this.http.get<string[]>('/api/categories');
  }

  // IMPORTANT: DO NOT MODIFY THIS METHOD.
  // If this method is changed, any assessment task relying on this method will
  // not be marked
  getProductsByCategory(category: string): Observable<Product[]> {
    return this.http.get<Product[]>(`/api/category/${category}`);
  }

  // IMPORTANT: DO NOT MODIFY THIS METHOD.
  // If this method is changed, any assessment task relying on this method will
  // not be marked
  checkout(order: Order) {
    return this.http.post<any>('/api/order', order).subscribe({
      next: (response) => {
        console.log('Order placed successfully', response);
        // You could add additional logic here like showing a success message
      },
      error: (error) => {
        console.error('Error placing order', error);
        // Handle error appropriately
      }
    });
  }
}