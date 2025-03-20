// IMPORTANT: DO NOT MODIFY ANY OF THESE INTERFACES
// If this class is changed, any assessment task relying on this class will
// not be marked

export interface Product {
  prodId: string
  name: string
  brand: string
  price: number
  discountPrice: number
  image: string
  quantity: string
}

export interface LineItem {
  prodId: string
  quantity: number
  name: string
  price: number
}

export interface Cart {
  lineItems: LineItem[]
}

export interface Order {
  name: string
  address: string
  priority: boolean
  comments: string
  cart: Cart
}

