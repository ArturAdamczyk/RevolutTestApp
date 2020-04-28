package art.com.revoluttestapp.data

import art.com.revoluttestapp.domain.model.Currency

class RevolutApiCurrenciesProvider(private val revolutApiClient: RevolutApiClient,
                                   private val currenciesDataMapper: CurrenciesDataMapper)
    : CurrenciesProvider {

    override fun fetchCurrencies(baseCurrency: String): List<Currency> {
        return currenciesDataMapper.mapToDomain(revolutApiClient.fetchCurrencies(baseCurrency))
    }

}