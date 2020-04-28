package art.com.revoluttestapp.data

import art.com.revoluttestapp.domain.model.CurrencyType
import art.com.revoluttestapp.domain.model.Money
import java.math.BigDecimal

object ConvertedMoneyFactory{

    fun getList(): List<Money>{
        val convertedMoneyList = mutableListOf<Money>()
        convertedMoneyList.add(Money(CurrencyType.USD, BigDecimal("419")))
        convertedMoneyList.add(Money(CurrencyType.EUR, BigDecimal("454")))
        convertedMoneyList.add(Money(CurrencyType.CHF, BigDecimal("431")))
        convertedMoneyList.add(Money(CurrencyType.IDR, BigDecimal("0.03")))
        convertedMoneyList.add(Money(CurrencyType.BRL, BigDecimal("0")))
        return convertedMoneyList
    }

}