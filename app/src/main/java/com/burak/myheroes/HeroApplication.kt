package com.burak.myheroes

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by mburak on 4.09.2021.
 */
@HiltAndroidApp
class HeroApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}