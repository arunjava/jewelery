import { State } from "./State.model";

export interface DistrictSaveRequest {
  districtName: string;
  districtCode: string;
  state: State;
}
