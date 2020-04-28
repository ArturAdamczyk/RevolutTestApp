package art.com.revoluttestapp.data

import art.com.revoluttestapp.domain.model.Currency
import art.com.revoluttestapp.domain.model.CurrencyType

interface CurrenciesRepository{

    fun saveCurrencies(currencyList: List<Currency>)

    fun loadCurrencies(): List<Currency>

    fun saveBaseCurrency(baseCurrency: CurrencyType)

    fun loadBaseCurrency(): CurrencyType

}
