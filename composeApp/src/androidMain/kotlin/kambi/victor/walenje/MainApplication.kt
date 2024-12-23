package kambi.victor.walenje

import android.app.Application
import kambi.victor.walenje.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MainApplication : Application() {
  @Override
  override fun onCreate() {
    super.onCreate()

    /* Koin Initialization */
    initKoin {
      androidContext(this@MainApplication)
      androidLogger()
    }
  }
}
