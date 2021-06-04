package ru.inncreator.devhack

import android.app.Application
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import ru.inncreator.devhack.fragments.main.MainFragmentViewModel
import timber.log.Timber

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        val vmModule = module {
            viewModel {
                MainFragmentViewModel()
            }
        }

        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            androidFileProperties()
            modules(listOf(vmModule))
        }
    }
}