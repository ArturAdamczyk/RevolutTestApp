package art.com.revoluttestapp.presentation.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import art.com.revoluttestapp.shared.Result
import art.com.revoluttestapp.shared.AppDispatchers
import art.com.revoluttestapp.shared.logger.Logger
import kotlinx.coroutines.*

abstract class BaseViewModel(private val logger : Logger,
                             private val dispatchers: AppDispatchers
): ViewModel() {

    fun <T> execute(
        function: () -> Result<T>,
        onSuccess: (T) -> Unit = {},
        onError: (Throwable) -> Unit = {},
        timeout: Long = 5000
    ): Job {
        return viewModelScope.launch(dispatchers.io()) {
            try{
                withTimeout(timeout){
                    when(val result = function.invoke()){
                        is Result.Success -> runOnUI(this) { onSuccess.invoke(result.value) }
                        is Result.Error -> runOnUI(this) { onError.invoke(result.throwable) }
                    }
                }
            }catch(e: Exception){
                logger.logException(e)
                runOnUI(this) { onError.invoke(e) }
            }
        }
    }

    fun <T> poll(
        function: () -> Result<T>,
        onSuccess: (T) -> Unit = {},
        onError: (Throwable) -> Unit = {},
        timeout: Long = 5000,
        interval: Long = 1000
    ): Job {
        return viewModelScope.launch(dispatchers.io()) {
            while(true){
                try{
                    delay(interval)
                    withTimeout(timeout){
                        when(val result = function.invoke()){
                            is Result.Success -> runOnUI(this) { onSuccess.invoke(result.value) }
                            is Result.Error -> runOnUI(this) { onError.invoke(result.throwable) }
                        }
                    }
                }catch(e: Exception){
                    logger.logException(e)
                    runOnUI(this) { onError.invoke(e) }
                    break
                }
            }
        }
    }

    private fun runOnUI(scope: CoroutineScope, function: () -> Unit){
        scope.launch(dispatchers.ui()){ function.invoke() }
    }
}

