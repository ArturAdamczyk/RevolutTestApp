package art.com.revoluttestapp.presentation.shared

import art.com.revoluttestapp.R

class CurrencyResourcesProvider {

    private val currenciesIcons: Map<String, Int> = mapOf(
        "AUD" to R.drawable.aud,
        "BGN" to R.drawable.bgn,
        "BRL" to R.drawable.brl,
        "CAD" to R.drawable.cad,
        "CHF" to R.drawable.chf,
        "CNY" to R.drawable.cny,
        "CZK" to R.drawable.czk,
        "DKK" to R.drawable.dkk,
        "EUR" to R.drawable.eur,
        "GBP" to R.drawable.gbp,
        "HKD" to R.drawable.hkd,
        "HRK" to R.drawable.hrk,
        "HUF" to R.drawable.huf,
        "IDR" to R.drawable.idr,
        "ILS" to R.drawable.ils,
        "INR" to R.drawable.inr,
        "ISK" to R.drawable.isk,
        "JPY" to R.drawable.jpy,
        "KRW" to R.drawable.krw,
        "MXN" to R.drawable.mxn,
        "MYR" to R.drawable.myr,
        "NOK" to R.drawable.nok,
        "NZD" to R.drawable.nzd,
        "PHP" to R.drawable.php,
        "PLN" to R.drawable.pln,
        "RON" to R.drawable.ron,
        "RUB" to R.drawable.rub,
        "SEK" to R.drawable.sek,
        "SGD" to R.drawable.sgd,
        "THB" to R.drawable.thb,
        "USD" to R.drawable.usd,
        "ZAR" to R.drawable.zar
    )

    private val currenciesNames: Map<String, Int> = mapOf(
        "AUD" to R.string.currency_name_aud,
        "BGN" to R.string.currency_name_bgn,
        "BRL" to R.string.currency_name_brl,
        "CAD" to R.string.currency_name_cad,
        "CHF" to R.string.currency_name_chf,
        "CNY" to R.string.currency_name_cny,
        "CZK" to R.string.currency_name_czk,
        "DKK" to R.string.currency_name_dkk,
        "EUR" to R.string.currency_name_eur,
        "GBP" to R.string.currency_name_gbp,
        "HKD" to R.string.currency_name_hkd,
        "HRK" to R.string.currency_name_hrk,
        "HUF" to R.string.currency_name_huf,
        "IDR" to R.string.currency_name_idr,
        "ILS" to R.string.currency_name_ils,
        "INR" to R.string.currency_name_inr,
        "ISK" to R.string.currency_name_isk,
        "JPY" to R.string.currency_name_jpy,
        "KRW" to R.string.currency_name_krw,
        "MXN" to R.string.currency_name_mxn,
        "MYR" to R.string.currency_name_myr,
        "NOK" to R.string.currency_name_nok,
        "NZD" to R.string.currency_name_nzd,
        "PHP" to R.string.currency_name_php,
        "PLN" to R.string.currency_name_pln,
        "RON" to R.string.currency_name_ron,
        "RUB" to R.string.currency_name_rub,
        "SEK" to R.string.currency_name_sek,
        "SGD" to R.string.currency_name_sgd,
        "THB" to R.string.currency_name_thb,
        "USD" to R.string.currency_name_usd,
        "ZAR" to R.string.currency_name_zar
    )

    fun getCurrencyIconUrl(currencyType: String): Int {
        return currenciesIcons.getValue(currencyType)
    }

    fun getCurrencyName(currencyType: String): Int {
        return currenciesNames.getValue(currencyType)
    }

}