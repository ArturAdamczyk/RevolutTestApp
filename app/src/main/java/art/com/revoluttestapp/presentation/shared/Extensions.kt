package art.com.revoluttestapp.presentation.shared

import android.content.res.Resources
import android.text.Editable
import android.view.View
import android.widget.EditText
import art.com.revoluttestapp.R

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

fun View.visible() {
    visibility = View.VISIBLE
}

fun Float.toSp(): Float = (this / Resources.getSystem().displayMetrics.scaledDensity)

fun EditText.adjustFontSize(textLength: Int) {
    when(textLength){
        in 0..8 -> this.textSize = resources.getDimension(R.dimen.font_large_plus).toSp()
        in 9..11 -> this.textSize = resources.getDimension(R.dimen.font_x_large).toSp()
        else -> this.textSize = resources.getDimension(R.dimen.font_medium).toSp()
    }
}