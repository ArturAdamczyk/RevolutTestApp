package art.com.revoluttestapp.domain.service

import art.com.revoluttestapp.domain.model.CurrencyType
import art.com.revoluttestapp.domain.model.Money
import art.com.revoluttestapp.shared.Result
import java.math.BigDecimal

interface CurrenciesApi {

    fun changeBaseCurrency(newBaseCurrency: CurrencyType): Result<CurrencyType>

    fun convertMoney(amount: BigDecimal): Result<List<Money>>

    fun updateCurrencies(): Result<Unit>

}