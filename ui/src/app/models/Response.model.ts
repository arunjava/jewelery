export interface Response<T> {
  message: string;
  result: T;
  statusCode: number;
}
