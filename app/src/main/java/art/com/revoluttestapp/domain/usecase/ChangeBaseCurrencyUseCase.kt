package art.com.revoluttestapp.domain.usecase

import art.com.revoluttestapp.data.CurrenciesProvider
import art.com.revoluttestapp.data.CurrenciesRepository
import art.com.revoluttestapp.domain.model.CurrencyType
import art.com.revoluttestapp.shared.Result

class ChangeBaseCurrencyUseCase(private val newBaseCurrency: CurrencyType,
                                private val currenciesRepository: CurrenciesRepository,
                                private val currenciesProvider: CurrenciesProvider) : UseCase<CurrencyType> {

    override fun invoke(): Result<CurrencyType> {
        return try{
            val fetchedCurrencies = currenciesProvider.fetchCurrencies(newBaseCurrency.value)
            currenciesRepository.saveBaseCurrency(newBaseCurrency)
            currenciesRepository.saveCurrencies(fetchedCurrencies)
            Result.Success(newBaseCurrency)
        }catch(e: Exception){
            Result.Error(e)
        }
    }

}