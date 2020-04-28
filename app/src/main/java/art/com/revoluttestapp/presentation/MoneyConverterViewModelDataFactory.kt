package art.com.revoluttestapp.presentation

import art.com.revoluttestapp.R
import art.com.revoluttestapp.domain.model.CurrencyType
import art.com.revoluttestapp.domain.model.Money
import art.com.revoluttestapp.presentation.shared.CurrencyResourcesProvider
import art.com.revoluttestapp.shared.logger.ResourcesProvider

class MoneyConverterViewModelDataFactory(private val currencyResourcesProvider: CurrencyResourcesProvider,
                                         private val resourcesProvider: ResourcesProvider
) {

    fun createConvertedMoneyItemList(moneyList: List<Money>): List<ConvertedMoneyItem> {
        val convertedMoneyList = mutableListOf<ConvertedMoneyItem>()
        moneyList
            .forEach {
                convertedMoneyList.add(ConvertedMoneyItem(
                            currencyName = currencyResourcesProvider.getCurrencyName(it.currencyType.value),
                            currencyType = it.currencyType.value,
                            currencyIcon = currencyResourcesProvider.getCurrencyIconUrl(it.currencyType.value),
                            amount = it.amount))
        }
        return convertedMoneyList
    }

    fun createBaseMoney(currencyType: CurrencyType): BaseMoney{
        return BaseMoney(
                    currencyName = currencyResourcesProvider.getCurrencyName(currencyType.value),
                    currencyType = currencyType.value,
                    currencyIcon = currencyResourcesProvider.getCurrencyIconUrl(currencyType.value)
        )
    }

    fun createConnectionErrorMessage(): String{
        return resourcesProvider.getString(R.string.internet_connection_error)
    }

}