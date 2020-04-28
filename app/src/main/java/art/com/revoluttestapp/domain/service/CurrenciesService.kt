package art.com.revoluttestapp.domain.service

import art.com.revoluttestapp.domain.model.Money
import art.com.revoluttestapp.domain.usecase.ChangeBaseCurrencyUseCase
import art.com.revoluttestapp.domain.usecase.ConvertMoneyUseCase
import art.com.revoluttestapp.domain.usecase.UpdateCurrenciesUseCase
import art.com.revoluttestapp.shared.Result
import art.com.revoluttestapp.data.CurrenciesProvider
import art.com.revoluttestapp.data.CurrenciesRepository
import art.com.revoluttestapp.domain.model.CurrencyType
import java.math.BigDecimal

class CurrenciesService(private val currenciesRepository: CurrenciesRepository,
                        private val currenciesProvider: CurrenciesProvider) : CurrenciesApi {

    override fun changeBaseCurrency(newBaseCurrency: CurrencyType): Result<CurrencyType> {
        return ChangeBaseCurrencyUseCase(newBaseCurrency, currenciesRepository, currenciesProvider).invoke()
    }

    override fun convertMoney(amount: BigDecimal): Result<List<Money>> {
        return ConvertMoneyUseCase(amount, currenciesRepository).invoke()
    }

    override fun updateCurrencies(): Result<Unit> {
        return UpdateCurrenciesUseCase(currenciesRepository, currenciesProvider).invoke()
    }

}