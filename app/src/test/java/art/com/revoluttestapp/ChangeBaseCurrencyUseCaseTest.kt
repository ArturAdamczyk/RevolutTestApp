package art.com.revoluttestapp

import art.com.revoluttestapp.data.CurrenciesFactory
import art.com.revoluttestapp.data.CurrenciesProvider
import art.com.revoluttestapp.data.CurrenciesRepository
import art.com.revoluttestapp.domain.model.CurrencyType
import art.com.revoluttestapp.domain.usecase.ChangeBaseCurrencyUseCase
import art.com.revoluttestapp.shared.ResultAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class ChangeBaseCurrencyUseCaseTest {

    @Mock
    private lateinit var currenciesRepository: CurrenciesRepository
    @Mock
    private lateinit var currenciesProvider: CurrenciesProvider
    private lateinit var systemUnderTest: ChangeBaseCurrencyUseCase
    private val newBaseCurrency = CurrencyType.EUR

    @Before
    fun setUp() {
        systemUnderTest = ChangeBaseCurrencyUseCase(newBaseCurrency, currenciesRepository, currenciesProvider)
    }

    @Test
    fun `should fetch currencies from provider when invoked`() {
        `assume currencies with filled exchange rates are provided`()

        systemUnderTest.invoke()

        Mockito.verify(currenciesProvider).fetchCurrencies(newBaseCurrency.value)
    }

    @Test
    fun `should save new base currency to repository when invoked`() {
        `assume currencies with filled exchange rates are provided`()

        systemUnderTest.invoke()

        Mockito.verify(currenciesRepository).saveBaseCurrency(newBaseCurrency)
    }

    @Test
    fun `should save currencies to repository when invoked`() {
        `assume currencies with filled exchange rates are provided`()
        val currencies = CurrenciesFactory.getList()

        systemUnderTest.invoke()

        Mockito.verify(currenciesRepository).saveCurrencies(currencies)
    }

    @Test
    fun `should return new base currency when invoked`() {
        `assume currencies with filled exchange rates are provided`()

        val result = systemUnderTest.invoke()

        ResultAssert.assertResult(result).isSuccess<CurrencyType>()
    }

    private fun `assume currencies with filled exchange rates are provided`() {
        BDDMockito.given(currenciesProvider.fetchCurrencies("EUR")).willReturn(CurrenciesFactory.getList())
    }

}