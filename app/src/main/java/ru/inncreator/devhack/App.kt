package ru.inncreator.devhack

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import ru.inncreator.devhack.fragments.events.EventsFragmentsViewModel
import ru.inncreator.devhack.fragments.main.MainFragmentViewModel
import ru.inncreator.devhack.fragments.openevent.OpenEventFragmentViewModel
import ru.inncreator.devhack.fragments.profile.ProfileFragmentViewModel
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        val vmModule = module {
            viewModel {
                MainFragmentViewModel()
            }
            viewModel { EventsFragmentsViewModel() }
            viewModel { OpenEventFragmentViewModel() }
            viewModel { ProfileFragmentViewModel() }
        }

        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            androidFileProperties()
            modules(listOf(vmModule))
        }
    }
}