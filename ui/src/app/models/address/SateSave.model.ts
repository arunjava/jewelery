import { Country } from "./Country.model";

export interface StateRequestModel {
  stateName: string;
  stateCode: string;
  country: Country;
}
