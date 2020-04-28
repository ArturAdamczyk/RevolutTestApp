package art.com.revoluttestapp

import art.com.revoluttestapp.data.CurrenciesFactory
import art.com.revoluttestapp.data.CurrenciesProvider
import art.com.revoluttestapp.data.CurrenciesRepository
import art.com.revoluttestapp.domain.model.CurrencyType
import art.com.revoluttestapp.domain.usecase.UpdateCurrenciesUseCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class UpdateCurrenciesUseCaseTest  {

    @Mock
    private lateinit var currenciesRepository: CurrenciesRepository
    @Mock
    private lateinit var currenciesProvider: CurrenciesProvider
    private lateinit var systemUnderTest: UpdateCurrenciesUseCase

    @Before
    fun setUp() {
        systemUnderTest = UpdateCurrenciesUseCase(currenciesRepository, currenciesProvider)
    }

    @Test
    fun `should fetch currencies from provider when invoked`() {
        `assume currencies with filled exchange rates are provided`()
        `assume that base currency is set to EUR`()
        val baseCurrency = "EUR"

        systemUnderTest.invoke()

        Mockito.verify(currenciesProvider).fetchCurrencies(baseCurrency)
    }

    @Test
    fun `should save result to repository when invoked`() {
        `assume currencies with filled exchange rates are provided`()
        `assume that base currency is set to EUR`()
        val result = CurrenciesFactory.getList()

        systemUnderTest.invoke()

        Mockito.verify(currenciesRepository).saveCurrencies(result)
    }

    private fun `assume currencies with filled exchange rates are provided`() {
        BDDMockito.given(currenciesProvider.fetchCurrencies("EUR")).willReturn(CurrenciesFactory.getList())
    }

    private fun `assume that base currency is set to EUR`() {
        BDDMockito.given(currenciesRepository.loadBaseCurrency()).willReturn(CurrencyType.EUR)
    }

}