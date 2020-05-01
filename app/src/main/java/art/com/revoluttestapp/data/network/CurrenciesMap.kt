package art.com.revoluttestapp.data.network

import art.com.revoluttestapp.domain.model.CurrencyType

object CurrenciesMap {

    private val currenciesMap: Map<String, CurrencyType> = mapOf(
        "AUD" to CurrencyType.AUD,
        "BGN" to CurrencyType.BGN,
        "BRL" to CurrencyType.BRL,
        "CAD" to CurrencyType.CAD,
        "CHF" to CurrencyType.CHF,
        "CNY" to CurrencyType.CNY,
        "CZK" to CurrencyType.CZK,
        "DKK" to CurrencyType.DKK,
        "EUR" to CurrencyType.EUR,
        "GBP" to CurrencyType.GBP,
        "HKD" to CurrencyType.HKD,
        "HRK" to CurrencyType.HRK,
        "HUF" to CurrencyType.HUF,
        "IDR" to CurrencyType.IDR,
        "ILS" to CurrencyType.ILS,
        "INR" to CurrencyType.INR,
        "ISK" to CurrencyType.ISK,
        "JPY" to CurrencyType.JPY,
        "KRW" to CurrencyType.KRW,
        "MXN" to CurrencyType.MXN,
        "MYR" to CurrencyType.MYR,
        "NOK" to CurrencyType.NOK,
        "NZD" to CurrencyType.NZD,
        "PHP" to CurrencyType.PHP,
        "PLN" to CurrencyType.PLN,
        "RON" to CurrencyType.RON,
        "RUB" to CurrencyType.RUB,
        "SEK" to CurrencyType.SEK,
        "SGD" to CurrencyType.SGD,
        "THB" to CurrencyType.THB,
        "USD" to CurrencyType.USD,
        "ZAR" to CurrencyType.ZAR
    )

    fun get(): Map<String, CurrencyType>{
        return currenciesMap
    }

}