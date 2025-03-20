import { Component, OnInit, inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CartStore } from './cart.store';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  private router = inject(Router);
  private cartStore = inject(CartStore);

  itemCount: number = 0;
  hasItems$: Observable<boolean>;

  constructor() {
    // Track whether there are items in the cart to disable checkout button
    this.hasItems$ = this.cartStore.hasItems$;
  }

  ngOnInit(): void {
    // Subscribe to the unique product count for the 'Items in cart' display
    this.cartStore.uniqueProductCount$.subscribe(count => {
      this.itemCount = count;
    });
  }

  checkout(): void {
    // First check if there are items in the cart
    this.cartStore.hasItems$.subscribe(hasItems => {
      if (hasItems) {
        this.router.navigate(['/checkout']);
      } else {
        alert('Your cart is empty! Please add some items before checking out.');
      }
    }).unsubscribe(); // Unsubscribe immediately after use
  }
}