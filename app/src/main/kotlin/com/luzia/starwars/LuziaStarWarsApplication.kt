package com.luzia.starwars

import android.app.Application
import android.content.pm.ApplicationInfo
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy.Builder
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LuziaStarWarsApplication : Application() { // , Configuration.Provider {
//    @Inject
//    lateinit var workerFactory: WorkerFactory
//
//    override val workManagerConfiguration: Configuration
//        get() = Configuration.Builder()
//            .setWorkerFactory(workerFactory)
//            .build()

    override fun onCreate() {
        super.onCreate()
        setStrictModePolicy()
//        Sync.initialize(this, workManagerConfiguration)
    }

    /**
     * Return true if the application is debuggable.
     */
    private fun isDebuggable(): Boolean = 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE

    /**
     * Set a thread policy that detects all potential problems on the main thread, such as network
     * and disk access.
     *
     * If a problem is found, the offending call will be logged and the application will be killed.
     */
    private fun setStrictModePolicy() {
        if (isDebuggable())
            StrictMode.setThreadPolicy(Builder().detectAll().penaltyLog().penaltyDeath().build())
    }
}
