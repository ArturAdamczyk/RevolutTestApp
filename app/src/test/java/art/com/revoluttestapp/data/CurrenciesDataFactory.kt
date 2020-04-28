package art.com.revoluttestapp.data

import art.com.revoluttestapp.domain.model.Currency
import art.com.revoluttestapp.domain.model.CurrencyType
import java.math.BigDecimal

object CurrenciesFactory{

    fun getList(): List<Currency>{
        val currenciesList = mutableListOf<Currency>()
        currenciesList.add(Currency(CurrencyType.USD, BigDecimal("4.19")))
        currenciesList.add(Currency(CurrencyType.EUR, BigDecimal("4.54")))
        currenciesList.add(Currency(CurrencyType.CHF, BigDecimal("4.31")))
        currenciesList.add(Currency(CurrencyType.IDR, BigDecimal("0.00027")))
        currenciesList.add(Currency(CurrencyType.BRL, BigDecimal("0")))
        return currenciesList
    }

}