package art.com.revoluttestapp.domain.usecase

import art.com.revoluttestapp.data.CurrenciesRepository
import art.com.revoluttestapp.domain.model.Money
import art.com.revoluttestapp.shared.Result
import java.math.BigDecimal

class ConvertMoneyUseCase(private val amount: BigDecimal,
                          private val currenciesRepository: CurrenciesRepository) : UseCase<List<Money>> {

    override fun invoke(): Result<List<Money>> {
        return try{
            val currencies = currenciesRepository.loadCurrencies()
            val moneyList: MutableList<Money> = mutableListOf()
            currencies.forEach {
                moneyList.add(Money(
                    it.currencyType,
                    it.convert(amount))
                )
            }
            Result.Success(moneyList)
        }catch(e: Exception){
            Result.Error(e)
        }
    }

}