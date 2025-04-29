package com.example.compose_praticle_demo

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import com.example.compose_praticle_demo.utils.MyPreference
import com.example.compose_praticle_demo.utils.PrefKey
import dagger.hilt.android.HiltAndroidApp
import java.util.Locale
import javax.inject.Inject


@HiltAndroidApp
class MyApp : Application() {
    @Inject
    lateinit var mPref: MyPreference

    companion object {
        private var instance: MyApp? = null
        fun applicationContext(): MyApp {
            return instance as MyApp
        }
    }

    fun updateLocale(context: Context, localeCode: String): Context {
        val locale = Locale(localeCode)
        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)

        val newContext = context.createConfigurationContext(config)

        // Save selected language
        mPref.setValueString(PrefKey.SELECTED_LANGUAGE, localeCode)

        return newContext
    }


    fun recreateApp(context: Context,) {
        // Device Specific issue - Notify activity of the locale change
        if (context is ComponentActivity) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            context.startActivity(intent)
            context.overridePendingTransition(0, 0)
        }
    }

    override fun onCreate() {
        super.onCreate()

        // You need to add your own dsn for sentry log
        instance = this
    }

    init {
        instance = this
    }
}