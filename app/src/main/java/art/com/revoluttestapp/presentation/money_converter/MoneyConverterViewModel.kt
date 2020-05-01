package art.com.revoluttestapp.presentation.money_converter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import art.com.revoluttestapp.domain.model.CurrencyType
import art.com.revoluttestapp.domain.service.CurrenciesApi
import art.com.revoluttestapp.presentation.money_converter.list.ConvertedMoneyItem
import art.com.revoluttestapp.presentation.shared.BaseViewModel
import art.com.revoluttestapp.presentation.shared.Event
import art.com.revoluttestapp.shared.AppDispatchers
import art.com.revoluttestapp.shared.logger.Logger
import kotlinx.coroutines.Job
import java.math.BigDecimal

private typealias DataFactory = MoneyConverterViewModelDataFactory

class MoneyConverterViewModel(private val currenciesApi: CurrenciesApi,
                              private val dataFactory: DataFactory,
                              logger: Logger,
                              dispatchers: AppDispatchers
): BaseViewModel(logger, dispatchers) {

    private var amount: BigDecimal = 100.toBigDecimal()
    private var moneyListUpdater: Job? = null
    private var convertedMoneyList = MutableLiveData<List<ConvertedMoneyItem>>()
    private var baseMoney = MutableLiveData<BaseMoney>()
    private var errorMessage = MutableLiveData<Event<String>>()
    private var initialDataLoadedCorrectly = false
    private var initialDataLoadErrorMessageDisplayed = false

    init{
        loadData()
    }

    fun observeConvertedMoneyList(): LiveData<List<ConvertedMoneyItem>> = convertedMoneyList

    fun observeBaseMoney(): LiveData<BaseMoney> = baseMoney

    fun observeErrorMessage(): LiveData<Event<String>> = errorMessage

    fun getMoneyAmount(): BigDecimal = amount

    fun changeBaseCurrency(currency: String, amount: BigDecimal){
        execute(
            function = {
                cancelDataPolling()
                currenciesApi.changeBaseCurrency(CurrencyType.valueOf(currency))
            },
            onSuccess = {
                this.amount = amount
                baseMoney.value = dataFactory.createBaseMoney(it)
                updateConvertedMoneyList()
            },
            onError = { errorMessage.value = Event(dataFactory.createConnectionErrorMessage()) }
        )
    }

    fun convertMoney(amount: BigDecimal) {
        this.amount = amount
        execute(
            function = { currenciesApi.convertMoney(this.amount) },
            onSuccess = { convertedMoneyList.postValue(dataFactory.createConvertedMoneyItemList(it)) })
    }

    fun stopPollingData(){
        cancelDataPolling()
    }

    fun resumePollingData(){
        moneyListUpdater?.let{
            if(!it.isActive) updateConvertedMoneyList()
        }?: updateConvertedMoneyList()
    }

    private fun cancelDataPolling(){
        if(moneyListUpdater?.isActive == true) moneyListUpdater?.cancel()
    }

    private fun loadData(currency: String = CurrencyType.EUR.value){
        execute(
            function = { currenciesApi.changeBaseCurrency(CurrencyType.valueOf(currency)) },
            onSuccess = {
                baseMoney.value = dataFactory.createBaseMoney(it)
                updateConvertedMoneyList()
            },
            onError = { handleInitialDataLoadError() }
        )
    }

    private fun handleInitialDataLoadError(){
        handleInitialDataLoadErrorMessage()
        loadData()
    }

    private fun handleInitialDataLoadErrorMessage(){
        if(!initialDataLoadErrorMessageDisplayed){
            initialDataLoadErrorMessageDisplayed = true
            errorMessage.value = Event(dataFactory.createConnectionErrorMessage())
        }
    }

    private fun updateConvertedMoneyList(){
        baseMoney.value?.let {
            initialDataLoadedCorrectly = true
            moneyListUpdater = poll(
                function = { currenciesApi.updateCurrencies() },
                onSuccess = { convertMoney(this.amount) }
            )
        }
    }

    override fun onCleared() {
        cancelDataPolling()
        super.onCleared()
    }

}

