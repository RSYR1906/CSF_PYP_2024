import { Injectable } from '@angular/core';
import { ComponentStore } from '@ngrx/component-store';
import { Observable } from 'rxjs';
import { LineItem } from './models';

interface CartState {
  lineItems: LineItem[];
  uniqueProducts: Set<string>; // To track unique product IDs
}

@Injectable({
  providedIn: 'root'
})
export class CartStore extends ComponentStore<CartState> {
  
  constructor() {
    super({
      lineItems: [],
      uniqueProducts: new Set<string>()
    });
  }

  // Selectors
  readonly lineItems$: Observable<LineItem[]> = this.select(state => state.lineItems);
  
  readonly uniqueProductCount$: Observable<number> = this.select(
    state => state.uniqueProducts.size
  );
  
  readonly totalItemCount$: Observable<number> = this.select(
    state => state.lineItems.length
  );
  
  readonly hasItems$: Observable<boolean> = this.select(
    state => state.lineItems.length > 0
  );
  
  // Updaters
  readonly addToCart = this.updater((state, lineItem: LineItem) => {
    const updatedLineItems = [...state.lineItems, lineItem];
    const updatedUniqueProducts = new Set(state.uniqueProducts);
    updatedUniqueProducts.add(lineItem.prodId);
    
    return {
      lineItems: updatedLineItems,
      uniqueProducts: updatedUniqueProducts
    };
  });
  
  readonly clearCart = this.updater(() => {
    return {
      lineItems: [],
      uniqueProducts: new Set<string>()
    };
  });
}