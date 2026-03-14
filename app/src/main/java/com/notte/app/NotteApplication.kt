package com.notte.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for Notte.
 * Annotated with @HiltAndroidApp to enable Hilt dependency injection.
 */
@HiltAndroidApp
class NotteApplication : Application()
