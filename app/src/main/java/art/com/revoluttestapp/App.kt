package art.com.revoluttestapp

import android.app.Application
import art.com.revoluttestapp.data.*
import art.com.revoluttestapp.data.network.CurrenciesDataMapper
import art.com.revoluttestapp.data.network.CurrenciesMap
import art.com.revoluttestapp.data.network.RevolutApiClient
import art.com.revoluttestapp.data.network.RevolutApiCurrenciesProvider
import art.com.revoluttestapp.domain.service.CurrenciesApi
import art.com.revoluttestapp.domain.service.CurrenciesService
import art.com.revoluttestapp.presentation.money_converter.MoneyConverterViewModel
import art.com.revoluttestapp.presentation.money_converter.MoneyConverterViewModelDataFactory
import art.com.revoluttestapp.presentation.shared.AndroidDispatchers
import art.com.revoluttestapp.presentation.shared.CurrencyResourcesProvider
import art.com.revoluttestapp.shared.AppDispatchers
import art.com.revoluttestapp.shared.ResourcesProvider
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
        viewModel { MoneyConverterViewModel(get(), get(), get(), get()) }
        single <CurrenciesApi> { CurrenciesService(get(), get()) }
        single <CurrenciesRepository> {CurrenciesRepositoryImpl() }
        single <CurrenciesProvider> { RevolutApiCurrenciesProvider(get(), get()) }
        single { RevolutApiClient() }
        factory { CurrenciesDataMapper(get()) }
        single { CurrenciesMap }
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