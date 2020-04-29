package art.com.revoluttestapp.shared

import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatchers {

    fun io(): CoroutineDispatcher

    fun ui(): CoroutineDispatcher

}