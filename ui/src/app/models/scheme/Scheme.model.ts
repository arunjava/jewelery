import { Offer } from "../offers/Offer.model";

export interface Scheme {
  scheme_id: number;
  scheme_name: string;
  scheme_desc: string;
  scheme_active: boolean;
  beginDate: Date;
  endDate: Date;
  uomID: number;
  duration: number;
  offers: Array<Offer>;
}
