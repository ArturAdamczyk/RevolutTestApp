package art.com.revoluttestapp.shared

import art.com.revoluttestapp.shared.logger.AppDispatchers
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