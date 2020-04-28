package art.com.revoluttestapp.shared

sealed class Result<T> {

    data class Success<T>(val value: T) : Result<T>()

    data class Error<T>(val throwable: Throwable) : Result<T>()

}