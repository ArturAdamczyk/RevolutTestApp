package art.com.revoluttestapp.shared.logger

import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatchers {

    fun io(): CoroutineDispatcher

    fun ui(): CoroutineDispatcher

}