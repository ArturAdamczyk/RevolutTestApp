package art.com.revoluttestapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import art.com.revoluttestapp.R

class ConvertedMoneyListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<ConvertedMoneyItem> = mutableListOf()
    var onItemClick: (moneyItem: ConvertedMoneyItem) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.money_item_row, parent, false)
        val holder = ConvertedMoneyItemViewHolder(inflatedView)
        holder.setIsRecyclable(true)
        return holder
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = position


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
         (holder as ConvertedMoneyItemViewHolder).fillView(items[position], onItemClick)
    }

    fun updateData(items: List<ConvertedMoneyItem>) {
        this.items = items
        notifyDataSetChanged()
    }
}