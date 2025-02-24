package kg.geeks.rick_and_morty.app

import android.app.Application
import androidx.room.Room
import kg.geeks.rick_and_morty.data.db.AppDatabase
import kg.geeks.rick_and_morty.data.serviceLocator.dataModule
import kg.geeks.rick_and_morty.data.serviceLocator.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "app_database"
        ).build()

        startKoin {
            androidContext(this@App)
            modules(dataModule, uiModule)
        }
    }
}