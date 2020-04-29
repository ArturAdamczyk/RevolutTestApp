package art.com.revoluttestapp.shared

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestDispatchers : AppDispatchers {

    override fun io(): CoroutineDispatcher {
        return Dispatchers.Unconfined
    }

    override fun ui(): CoroutineDispatcher {
        return Dispatchers.Unconfined
    }

}