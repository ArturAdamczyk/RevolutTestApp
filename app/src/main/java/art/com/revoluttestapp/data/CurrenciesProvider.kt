package art.com.revoluttestapp.data

import art.com.revoluttestapp.domain.model.Currency

interface CurrenciesProvider {

    fun fetchCurrencies(baseCurrency: String): List<Currency>

}