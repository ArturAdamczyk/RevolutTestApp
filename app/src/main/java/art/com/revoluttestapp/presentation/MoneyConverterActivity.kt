package art.com.revoluttestapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import art.com.revoluttestapp.R
import art.com.revoluttestapp.presentation.shared.EventObserver
import art.com.revoluttestapp.presentation.shared.adjustFontSize
import art.com.revoluttestapp.presentation.shared.toEditable
import art.com.revoluttestapp.presentation.shared.visible
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_money_converter.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.math.BigDecimal

class MoneyConverterActivity : AppCompatActivity() {

    private val viewModel: MoneyConverterViewModel by viewModel()
    private val moneyListAdapter = ConvertedMoneyListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_money_converter)
        setupAdapter()
        setupRecyclerView()
        setupMoneyInputForm()
        setupObservers()
    }

    private fun setupObservers(){
        with(viewModel){
            observeConvertedMoneyList().observe(this@MoneyConverterActivity, Observer(::updateAdapterData))
            observeBaseMoney().observe(this@MoneyConverterActivity, Observer(::refreshBaseCurrencyLabels))
            observeErrorMessage().observe(this@MoneyConverterActivity, EventObserver(::showMessage))
        }
    }

    private fun updateAdapterData(moneyItems: List<ConvertedMoneyItem>){
        moneyListAdapter.updateData(moneyItems)
    }

    private fun showMessage(message: String){
        Snackbar.make(moneyConverterViewContainer, message, Snackbar.LENGTH_LONG).show()
    }

    private fun refreshBaseCurrencyLabels(baseMoney: BaseMoney){
        baseCurrencyContainer.visible()
        baseCurrencyNameLabel.text = this.resources.getString(baseMoney.currencyName)
        baseCurrencyTypeLabel.text = baseMoney.currencyType
        if (viewModel.getMoneyAmount().compareTo(BigDecimal.ZERO) == 0) {
            moneyAmountInput.hint = "0".toEditable()
            moneyAmountInput.text.clear()
        }else{
            moneyAmountInput.text = viewModel.getMoneyAmount().toString().toEditable()
            moneyAmountInput.setSelection(moneyAmountInput.text.length)
        }
        moneyAmountInput.adjustFontSize(viewModel.getMoneyAmount().toString().length)
        baseCurrencyLogo.setImageDrawable(ContextCompat.getDrawable(this, baseMoney.currencyIcon))
    }

    private fun setupMoneyInputForm(){
        moneyAmountInput.text = viewModel.getMoneyAmount().toString().toEditable()
        moneyAmountInput.adjustFontSize(viewModel.getMoneyAmount().toString().length)
        moneyAmountInput.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if(s.isNotEmpty()) viewModel.convertMoney((s.toString().toBigDecimal()))
                else viewModel.convertMoney(0.toBigDecimal())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

        })
    }

    private fun setupAdapter(){
        moneyListAdapter.apply{
            onItemClick = {
                viewModel.changeBaseCurrency(it.currencyType, it.amount)
            }
        }
    }

    private fun setupRecyclerView(){
        with(moneyListRecyclerView) {
            adapter = moneyListAdapter
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }
}
