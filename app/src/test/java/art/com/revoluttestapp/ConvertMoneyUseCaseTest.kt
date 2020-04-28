package art.com.revoluttestapp

import art.com.revoluttestapp.data.CurrenciesFactory
import art.com.revoluttestapp.data.CurrenciesRepository
import art.com.revoluttestapp.domain.model.CurrencyType
import art.com.revoluttestapp.domain.model.Money
import art.com.revoluttestapp.domain.usecase.ConvertMoneyUseCase
import art.com.revoluttestapp.shared.ResultAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal

@RunWith(MockitoJUnitRunner.Silent::class)
class ConvertMoneyUseCaseTest  {

    @Mock
    private lateinit var currenciesRepository: CurrenciesRepository
    private lateinit var systemUnderTest: ConvertMoneyUseCase
    private val moneyAmount = BigDecimal("100")

    @Before
    fun setUp() {
        systemUnderTest = ConvertMoneyUseCase(moneyAmount, currenciesRepository)
    }

    @Test
    fun `should load currencies from repository when invoked`() {
        `assume currencies with filled exchange rates are set`()
        `assume that base currency is set to EUR`()

        systemUnderTest.invoke()

        Mockito.verify(currenciesRepository).loadCurrencies()
    }

    @Test
    fun `should return converted money list when invoked`() {
        `assume currencies with filled exchange rates are set`()
        `assume that base currency is set to EUR`()

        val result = systemUnderTest.invoke()

        ResultAssert.assertResult(result).isSuccess<Money>()
    }

    private fun `assume currencies with filled exchange rates are set`() {
        BDDMockito.given(currenciesRepository.loadCurrencies()).willReturn(CurrenciesFactory.getList())
    }

    private fun `assume that base currency is set to EUR`() {
        BDDMockito.given(currenciesRepository.loadBaseCurrency()).willReturn(CurrencyType.EUR)
    }

}

