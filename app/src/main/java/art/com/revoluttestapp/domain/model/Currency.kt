package art.com.revoluttestapp.domain.model

import java.math.BigDecimal

data class Currency(val currencyType: CurrencyType,
                    val exchangeRate: BigDecimal){

    fun convert(value: BigDecimal): BigDecimal {
        val convertedValue = (value * exchangeRate)
        return convertedValue
            .setScale(2, BigDecimal.ROUND_HALF_UP)
            .stripTrailingZeros()
    }
}