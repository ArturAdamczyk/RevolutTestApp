package art.com.revoluttestapp.data

import com.google.gson.annotations.SerializedName

class CurrenciesDTO(
    @SerializedName("baseCurrency")
    val baseCurrency: String,
    @SerializedName("rates")
    val rates: Rates
){
    class Rates(
        @SerializedName("AUD")
        val aud: String?,
        @SerializedName("BGN")
        val bgn: String?,
        @SerializedName("BRL")
        val brl: String?,
        @SerializedName("CAD")
        val cad: String?,
        @SerializedName("CHF")
        val chf: String?,
        @SerializedName("CNY")
        val cny: String?,
        @SerializedName("CZK")
        val czk: String?,
        @SerializedName("DKK")
        val dkk: String?,
        @SerializedName("EUR")
        val eur: String?,
        @SerializedName("GBP")
        val gbp: String?,
        @SerializedName("HKD")
        val hkd: String?,
        @SerializedName("HRK")
        val hrk: String?,
        @SerializedName("HUF")
        val huf: String?,
        @SerializedName("IDR")
        val idr: String?,
        @SerializedName("ILS")
        val ils: String?,
        @SerializedName("INR")
        val inr: String?,
        @SerializedName("ISK")
        val isk: String?,
        @SerializedName("JPY")
        val jpy: String?,
        @SerializedName("KRW")
        val krw: String?,
        @SerializedName("MXN")
        val mxn: String?,
        @SerializedName("MYR")
        val myr: String?,
        @SerializedName("NOK")
        val nok: String?,
        @SerializedName("NZD")
        val nzd: String?,
        @SerializedName("PHP")
        val php: String?,
        @SerializedName("PLN")
        val pln: String?,
        @SerializedName("RON")
        val ron: String?,
        @SerializedName("RUB")
        val rub: String?,
        @SerializedName("SEK")
        val sek: String?,
        @SerializedName("SGD")
        val sgd: String?,
        @SerializedName("THB")
        val thb: String?,
        @SerializedName("USD")
        val usd: String?,
        @SerializedName("ZAR")
        val zar: String?
    )
}
