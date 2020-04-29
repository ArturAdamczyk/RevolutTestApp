package art.com.revoluttestapp.data.network

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.result.Result

class RevolutApiClient{

    private val BASE_URL = "https://hiring.revolut.codes/api/android"

    fun fetchCurrencies(baseCurrency: String): CurrenciesDTO {
        val (request, response, result) = Fuel
            .get("$BASE_URL/latest", listOf("base" to baseCurrency))
            .responseObject<CurrenciesDTO>()
        when(result){
            is Result.Success -> { return result.get() }
            is Result.Failure -> { throw ConnectionError(
                result.getException()
            )
            }
        }
    }
}