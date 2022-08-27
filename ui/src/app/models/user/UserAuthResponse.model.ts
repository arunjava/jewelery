export interface UserAuthResponse {

  userId: number;
  firstName: string;
  middleName: string;
  lastName: string;
  username: string;
  roles: Array<string>;
  token: string;

}
