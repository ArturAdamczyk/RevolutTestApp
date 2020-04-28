package art.com.revoluttestapp

import android.app.Application
import art.com.revoluttestapp.data.*
import art.com.revoluttestapp.domain.service.CurrenciesApi
import art.com.revoluttestapp.domain.service.CurrenciesService
import art.com.revoluttestapp.presentation.MoneyConverterViewModel
import art.com.revoluttestapp.presentation.MoneyConverterViewModelDataFactory
import art.com.revoluttestapp.presentation.shared.AndroidDispatchers
import art.com.revoluttestapp.presentation.shared.CurrencyResourcesProvider
import art.com.revoluttestapp.shared.logger.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App: Application() {

    override fun onCreate() {
        startKoin {
            androidContext(this@App)
            modules(dependencies)
        }
        super.onCreate()
    }

    private val dependencies = module {
        viewModel { MoneyConverterViewModel( get(), get(), get(), get()) }
        single <CurrenciesApi> { CurrenciesService(get(), get()) }
        single <CurrenciesRepository> {CurrenciesRepositoryImpl() }
        single <CurrenciesProvider> { RevolutApiCurrenciesProvider(get(), get()) }
        single { RevolutApiClient() }
        factory { CurrenciesDataMapper() }
        single { CurrencyResourcesProvider() }
        factory { MoneyConverterViewModelDataFactory(get(), get()) }
        single <Logger> {
            if(BuildConfig.DEBUG) DebugLogger()
            else ProductionLogger()
        }
        single { ResourcesProvider(this@App.resources) }
        single <AppDispatchers> { AndroidDispatchers() }
    }
}