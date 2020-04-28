package art.com.revoluttestapp.presentation.shared

import art.com.revoluttestapp.shared.logger.AppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AndroidDispatchers : AppDispatchers {

    override fun io(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    override fun ui(): CoroutineDispatcher {
        return Dispatchers.Main
    }

}