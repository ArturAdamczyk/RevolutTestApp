package art.com.revoluttestapp.presentation

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import art.com.revoluttestapp.R
import art.com.revoluttestapp.presentation.shared.adjustFontSize
import kotlinx.android.synthetic.main.money_item_row.view.*
import java.math.BigDecimal

class ConvertedMoneyItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun fillView(item: ConvertedMoneyItem,
                 onItemClick: (moneyItem: ConvertedMoneyItem) -> Unit = {}){
        itemView.apply{
            currencyLogo.setImageDrawable(ContextCompat.getDrawable(this.context, item.currencyIcon))
            currencyNameLabel.text = this.resources.getString(item.currencyName)
            currencyTypeLabel.text = item.currencyType
            fillMoneyLabel(item)
            setOnClickListener { onItemClick.invoke(item) }
        }
    }

    private fun fillMoneyLabel(item: ConvertedMoneyItem){
        itemView.apply {
            if (item.amount.compareTo(BigDecimal.ZERO) == 0) {
                moneyValueLabel.setHintTextColor(
                    ContextCompat.getColor(this.context, R.color.colorGray)
                )
                moneyValueLabel.hint = "0"
            }else{
                moneyValueLabel.setHintTextColor(
                    ContextCompat.getColor(this.context, R.color.colorBlack)
                )
                moneyValueLabel.hint = item.amount.toPlainString()
            }
            moneyValueLabel.adjustFontSize(item.amount.toPlainString().length)
        }
    }

}