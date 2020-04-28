package art.com.revoluttestapp.shared.logger

class DebugLogger: Logger {

    override fun logException(throwable: Throwable) {
        throwable.printStackTrace()
    }

}