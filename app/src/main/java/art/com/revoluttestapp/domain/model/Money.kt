package art.com.revoluttestapp.domain.model

import java.math.BigDecimal

data class Money(val currencyType: CurrencyType,
                 val amount: BigDecimal
)