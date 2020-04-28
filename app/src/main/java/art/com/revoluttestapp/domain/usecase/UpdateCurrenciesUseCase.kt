package art.com.revoluttestapp.domain.usecase

import art.com.revoluttestapp.data.CurrenciesProvider
import art.com.revoluttestapp.data.CurrenciesRepository
import art.com.revoluttestapp.shared.Result

class UpdateCurrenciesUseCase(private val currenciesRepository: CurrenciesRepository,
                              private val currenciesProvider: CurrenciesProvider) : UseCase<Unit> {

    override fun invoke(): Result<Unit> {
        return try{
            val baseCurrency = currenciesRepository.loadBaseCurrency()
            val fetchedCurrencies = currenciesProvider.fetchCurrencies(baseCurrency.value)
            currenciesRepository.saveCurrencies(fetchedCurrencies)
            Result.Success(Unit)
        }catch(e: Exception){
            Result.Error(e)
        }
    }
}