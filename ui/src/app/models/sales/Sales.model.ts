export interface Sales {
  productID: number;
  productName: string;
  uomID: number;
  uomDesc: string;
  invoiceNumber: string;
  costPrice: number;
  sellingPrice: number;
  mrp: number;
  custID: number;
  qty: number;
  makingCharges: number;
  wastageCharges: number;
  soldAmt: number;
  customerSchemeID: number;
  txnDate: Date;
  cgst: number;
  sgst: number;
}
