package art.com.revoluttestapp

import art.com.revoluttestapp.data.ConvertedMoneyFactory
import art.com.revoluttestapp.data.CurrenciesFactory
import art.com.revoluttestapp.data.CurrenciesProvider
import art.com.revoluttestapp.data.CurrenciesRepository
import art.com.revoluttestapp.domain.model.CurrencyType
import art.com.revoluttestapp.domain.service.CurrenciesApi
import art.com.revoluttestapp.domain.service.CurrenciesService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import art.com.revoluttestapp.shared.Result
import org.mockito.BDDMockito
import java.math.BigDecimal

@RunWith(MockitoJUnitRunner.Silent::class)
class CurrenciesServiceTest {
    @Mock
    private lateinit var currenciesRepository: CurrenciesRepository
    @Mock
    private lateinit var currenciesProvider: CurrenciesProvider
    private lateinit var serviceUnderTest: CurrenciesApi

    @Before
    fun setUp() {
        serviceUnderTest = CurrenciesService(currenciesRepository, currenciesProvider)
    }

    @Test
    fun `should change base currency to USD`() {
        val expected = CurrencyType.USD

        val actual = serviceUnderTest.changeBaseCurrency(CurrencyType.USD)

        assert(actual is Result.Success && actual.value == expected)
    }

    @Test
    fun `should convert given money amount to other currencies`() {
        val expected = ConvertedMoneyFactory.getList()
        `assume that base currency is set to PLN`()
        `assume currencies with filled exchange rates are set`()

        val actual = serviceUnderTest.convertMoney(BigDecimal("100"))

        assert(actual is Result.Success && actual.value.size == 5)
        when(actual){
            is Result.Success -> {
                assert(actual.value[0].amount.compareTo(expected[0].amount) == 0)
                assert(actual.value[1].amount.compareTo(expected[1].amount) == 0)
                assert(actual.value[2].amount.compareTo(expected[2].amount) == 0)
                assert(actual.value[3].amount.compareTo(expected[3].amount) == 0)
                assert(actual.value[4].amount.compareTo(expected[4].amount) == 0)
            }
        }

    }

    @Test
    fun `should update currencies exchange rates`() {
        val expected = Unit
        `assume that base currency is set to EUR`()

        val actual = serviceUnderTest.updateCurrencies()

        assert(actual is Result.Success && actual.value == expected)
    }

    private fun `assume that base currency is set to EUR`() {
        BDDMockito.given(currenciesRepository.loadBaseCurrency()).willReturn(CurrencyType.EUR)
    }

    private fun `assume that base currency is set to PLN`() {
        BDDMockito.given(currenciesRepository.loadBaseCurrency()).willReturn(CurrencyType.EUR)
    }

    private fun `assume currencies with filled exchange rates are set`() {
        BDDMockito.given(currenciesRepository.loadCurrencies()).willReturn(CurrenciesFactory.getList())
    }
}

