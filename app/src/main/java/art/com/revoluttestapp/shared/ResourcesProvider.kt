package art.com.revoluttestapp.shared

import android.content.res.Resources

class ResourcesProvider(private val resources: Resources) {

    fun getString(resId: Int): String = resources.getString(resId)

}