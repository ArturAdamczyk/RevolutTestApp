package art.com.revoluttestapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import art.com.revoluttestapp.domain.model.CurrencyType
import art.com.revoluttestapp.domain.model.Money
import art.com.revoluttestapp.domain.service.CurrenciesApi
import art.com.revoluttestapp.presentation.BaseMoney
import art.com.revoluttestapp.presentation.ConvertedMoneyItem
import art.com.revoluttestapp.presentation.MoneyConverterViewModel
import art.com.revoluttestapp.presentation.MoneyConverterViewModelDataFactory
import art.com.revoluttestapp.shared.logger.Logger
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.math.BigDecimal
import art.com.revoluttestapp.shared.Result
import art.com.revoluttestapp.shared.TestDispatchers
import art.com.revoluttestapp.shared.logger.AppDispatchers
import io.mockk.*
import kotlinx.coroutines.runBlocking

private typealias DataFactory = MoneyConverterViewModelDataFactory

@RunWith(JUnit4::class)
class MoneyConverterViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var currenciesApi: CurrenciesApi
    @MockK
    private lateinit var dataFactory: DataFactory
    @MockK
    private lateinit var logger: Logger
    private val dispatchers: AppDispatchers = TestDispatchers()
    private lateinit var viewModelUnderTest: MoneyConverterViewModel
    private val moneyAmount = BigDecimal("100")
    private val baseMoney = BaseMoney(0,"", 0)
    private val convertedMoneyList = listOf(ConvertedMoneyItem(0,"",0, BigDecimal("200")))

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        `simulate view model data factory behaviour`()
        `simulate currencies api behaviour`()

        viewModelUnderTest = MoneyConverterViewModel(currenciesApi, dataFactory, logger, dispatchers)
    }

    @Test
    fun `should be able to convert given money amount to other currencies`() {
        runBlocking {
            viewModelUnderTest.convertMoney(moneyAmount)
        }

        assert(viewModelUnderTest.observeConvertedMoneyList().value != null)
        assert(viewModelUnderTest.observeConvertedMoneyList().value!!.size == 1)
        assert(viewModelUnderTest.observeConvertedMoneyList().value!![0] == convertedMoneyList[0])
    }

    @Test
    fun `should be able load data on start`() {
        verifyOrder {
            currenciesApi.changeBaseCurrency(any())
            currenciesApi.updateCurrencies()
            currenciesApi.convertMoney(any())
        }
    }

    @Test
    fun `should be able to change base currency`() {
        runBlocking {
            viewModelUnderTest.changeBaseCurrency(CurrencyType.EUR.value, BigDecimal("100"))
        }

        verifyOrder {
            currenciesApi.changeBaseCurrency(any())
            currenciesApi.updateCurrencies()
            currenciesApi.convertMoney(any())
        }

        assert(viewModelUnderTest.observeBaseMoney().value != null)
        assert(viewModelUnderTest.observeBaseMoney().value == baseMoney)
    }

    private fun `simulate view model data factory behaviour`() {
        `simulate base money creation success`(baseMoney)
        `simulate converted money list creation success`(convertedMoneyList)
    }

    private fun `simulate currencies api behaviour`() {
        `simulate change base currency success`()
        `simulate currencies update success`()
        `simulate money convert success`()
    }

    private fun `simulate base money creation success`(baseMoney: BaseMoney) {
        coEvery { dataFactory.createBaseMoney(any()) } returns baseMoney
    }

    private fun `simulate converted money list creation success`(convertedMoneyList: List<ConvertedMoneyItem>) {
        coEvery { dataFactory.createConvertedMoneyItemList(any()) } returns convertedMoneyList
    }

    private fun `simulate change base currency success`() {
        coEvery { currenciesApi.changeBaseCurrency(any()) } returns Result.Success(CurrencyType.EUR)
    }

    private fun `simulate currencies update success`() {
        coEvery { currenciesApi.updateCurrencies() } returns Result.Success(Unit)
    }

    private fun `simulate money convert success`() {
        coEvery { currenciesApi.convertMoney(moneyAmount) } returns Result.Success(listOf(Money(CurrencyType.EUR, BigDecimal("200"))))
    }

}