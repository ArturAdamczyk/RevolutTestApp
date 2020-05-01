package art.com.revoluttestapp.data.network

import art.com.revoluttestapp.domain.model.Currency
import java.math.BigDecimal

class CurrenciesDataMapper(private val currenciesMap: CurrenciesMap): ModelMapper<CurrenciesDTO, List<Currency>> {

    override fun mapToDomain(dto: CurrenciesDTO): List<Currency> {
        return mapRates(dto.rates)
    }

    private fun mapRates(rates: Map<String, String>): List<Currency>{
        val currencies: MutableList<Currency> = mutableListOf()
        rates.forEach { currencies.add(
                Currency(currenciesMap.get().getValue(it.key),
                BigDecimal(it.value)))
        }
        return currencies
    }
}