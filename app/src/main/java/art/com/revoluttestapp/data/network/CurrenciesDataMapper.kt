package art.com.revoluttestapp.data.network

import art.com.revoluttestapp.domain.model.Currency
import art.com.revoluttestapp.domain.model.CurrencyType
import java.math.BigDecimal

class CurrenciesDataMapper:
    ModelMapper<CurrenciesDTO, List<Currency>> {

    override fun mapToDomain(dto: CurrenciesDTO): List<Currency> {
        return mapRates(dto.rates)
    }

    private fun mapRates(rates: CurrenciesDTO.Rates): List<Currency>{
        val currencies: MutableList<Currency> = mutableListOf()
        rates.aud?.let{ currencies.add(Currency(currencyType = CurrencyType.AUD, exchangeRate = BigDecimal(rates.aud))) }
        rates.bgn?.let{ currencies.add(Currency(currencyType = CurrencyType.BGN, exchangeRate = BigDecimal(rates.bgn))) }
        rates.brl?.let{ currencies.add(Currency(currencyType = CurrencyType.BRL, exchangeRate = BigDecimal(rates.brl))) }
        rates.cad?.let{ currencies.add(Currency(currencyType = CurrencyType.CAD, exchangeRate = BigDecimal(rates.cad))) }
        rates.chf?.let{ currencies.add(Currency(currencyType = CurrencyType.CHF, exchangeRate = BigDecimal(rates.chf))) }
        rates.cny?.let{ currencies.add(Currency(currencyType = CurrencyType.CNY, exchangeRate = BigDecimal(rates.cny))) }
        rates.czk?.let{ currencies.add(Currency(currencyType = CurrencyType.CZK, exchangeRate = BigDecimal(rates.czk))) }
        rates.dkk?.let{ currencies.add(Currency(currencyType = CurrencyType.DKK, exchangeRate = BigDecimal(rates.dkk))) }
        rates.eur?.let{ currencies.add(Currency(currencyType = CurrencyType.EUR, exchangeRate = BigDecimal(rates.eur))) }
        rates.gbp?.let{ currencies.add(Currency(currencyType = CurrencyType.GBP, exchangeRate = BigDecimal(rates.gbp))) }
        rates.hkd?.let{ currencies.add(Currency(currencyType = CurrencyType.HKD, exchangeRate = BigDecimal(rates.hkd))) }
        rates.hrk?.let{ currencies.add(Currency(currencyType = CurrencyType.HRK, exchangeRate = BigDecimal(rates.hrk))) }
        rates.huf?.let{ currencies.add(Currency(currencyType = CurrencyType.HUF, exchangeRate = BigDecimal(rates.huf))) }
        rates.idr?.let{ currencies.add(Currency(currencyType = CurrencyType.IDR, exchangeRate = BigDecimal(rates.idr))) }
        rates.ils?.let{ currencies.add(Currency(currencyType = CurrencyType.ILS, exchangeRate = BigDecimal(rates.ils))) }
        rates.inr?.let{ currencies.add(Currency(currencyType = CurrencyType.INR, exchangeRate = BigDecimal(rates.inr))) }
        rates.isk?.let{ currencies.add(Currency(currencyType = CurrencyType.ISK, exchangeRate = BigDecimal(rates.isk))) }
        rates.jpy?.let{ currencies.add(Currency(currencyType = CurrencyType.JPY, exchangeRate = BigDecimal(rates.jpy))) }
        rates.krw?.let{ currencies.add(Currency(currencyType = CurrencyType.KRW, exchangeRate = BigDecimal(rates.krw))) }
        rates.mxn?.let{ currencies.add(Currency(currencyType = CurrencyType.MXN, exchangeRate = BigDecimal(rates.mxn))) }
        rates.myr?.let{ currencies.add(Currency(currencyType = CurrencyType.MYR, exchangeRate = BigDecimal(rates.myr))) }
        rates.nok?.let{ currencies.add(Currency(currencyType = CurrencyType.NOK, exchangeRate = BigDecimal(rates.nok))) }
        rates.nzd?.let{ currencies.add(Currency(currencyType = CurrencyType.NZD, exchangeRate = BigDecimal(rates.nzd))) }
        rates.php?.let{ currencies.add(Currency(currencyType = CurrencyType.PHP, exchangeRate = BigDecimal(rates.php))) }
        rates.pln?.let{ currencies.add(Currency(currencyType = CurrencyType.PLN, exchangeRate = BigDecimal(rates.pln))) }
        rates.ron?.let{ currencies.add(Currency(currencyType = CurrencyType.RON, exchangeRate = BigDecimal(rates.ron))) }
        rates.rub?.let{ currencies.add(Currency(currencyType = CurrencyType.RUB, exchangeRate = BigDecimal(rates.rub))) }
        rates.sek?.let{ currencies.add(Currency(currencyType = CurrencyType.SEK, exchangeRate = BigDecimal(rates.sek))) }
        rates.sgd?.let{ currencies.add(Currency(currencyType = CurrencyType.SGD, exchangeRate = BigDecimal(rates.sgd))) }
        rates.thb?.let{ currencies.add(Currency(currencyType = CurrencyType.THB, exchangeRate = BigDecimal(rates.thb))) }
        rates.usd?.let{ currencies.add(Currency(currencyType = CurrencyType.USD, exchangeRate = BigDecimal(rates.usd))) }
        rates.zar?.let{ currencies.add(Currency(currencyType = CurrencyType.ZAR, exchangeRate = BigDecimal(rates.zar))) }
        return currencies
    }
}