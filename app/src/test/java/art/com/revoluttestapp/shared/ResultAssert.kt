package art.com.revoluttestapp.shared

import org.assertj.core.api.AbstractAssert

class ResultAssert(result: Result<*>) : AbstractAssert<ResultAssert, Result<*>>(result, ResultAssert::class.java) {

    companion object {
        fun assertResult(result: Result<*>): ResultAssert {
            return ResultAssert(result)
        }
    }

    fun <T> isSuccess(): T {
        isInstanceOf(Result.Success::class.java)
        return (actual as Result.Success<T>).value
    }

}