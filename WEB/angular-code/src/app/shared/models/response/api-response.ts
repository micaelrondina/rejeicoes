import { ApiError } from "./api-error";

export interface ApiResponse<T> {
    dados: T;
    falha: ApiError;
}