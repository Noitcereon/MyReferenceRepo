export interface ICommonResponse<T> {
  payload: T;
  error: null | IErrorDetails;
  success: Boolean;
}

// IErrorDetails is only used this file, therefore it is not in its own file.
interface IErrorDetails {
  status: number;
  message: String;
}
