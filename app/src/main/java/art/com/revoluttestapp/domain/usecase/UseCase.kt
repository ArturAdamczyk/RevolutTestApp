package art.com.revoluttestapp.domain.usecase

import art.com.revoluttestapp.shared.Result

interface UseCase<T>{

    operator fun invoke(): Result<T>

}