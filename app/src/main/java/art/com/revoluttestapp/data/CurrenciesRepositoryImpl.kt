package art.com.revoluttestapp.data

import art.com.revoluttestapp.domain.model.Currency
import art.com.revoluttestapp.domain.model.CurrencyType

class CurrenciesRepositoryImpl: CurrenciesRepository{

    private var  currencyList: List<Currency> = listOf()
    private var baseCurrency: CurrencyType = CurrencyType.EUR

    override fun saveCurrencies(currencyList: List<Currency>) {
        this.currencyList = currencyList
    }

    override fun loadCurrencies(): List<Currency> {
        return this.currencyList
    }

    override fun saveBaseCurrency(baseCurrency: CurrencyType) {
        this.baseCurrency = baseCurrency
    }

    override fun loadBaseCurrency(): CurrencyType {
        return this.baseCurrency
    }

}