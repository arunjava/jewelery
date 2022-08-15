import { Scheme } from '../scheme/Scheme.model';
export interface CustomerScheme {
    
    id: number;
    customerID: number;
    schemeID: number;
    uomExchangeID: number;
    uomExchangeVal: number;
    scheme: Scheme;

}
