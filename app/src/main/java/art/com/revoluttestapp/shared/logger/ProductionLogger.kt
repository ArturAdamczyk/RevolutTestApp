package art.com.revoluttestapp.shared.logger

class ProductionLogger: Logger {

    override fun logException(throwable: Throwable) {
        // no-op
    }

}