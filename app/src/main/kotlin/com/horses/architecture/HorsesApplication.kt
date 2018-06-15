package com.horses.architecture

import android.app.Activity
import android.app.Application
import com.horses.architecture.di.component.AppComponent
import com.horses.architecture.di.component.DaggerAppComponent
import com.horses.architecture.di.module.AppModule
import java.lang.Exception
import java.util.*

class HorsesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {

        lateinit var appComponent: AppComponent

        private val activities = Stack<Activity>()

        fun addActivity(activity: Activity) {
            activities.add(activity)
        }

        fun removeActivity(activity: Activity) {
            activities.remove(activity)
        }

        fun closeAll() {
            for (activity in activities) {
                try {
                    activity.finish()
                }
                catch (ignore: Exception) {}
            }
            activities.clear()
        }
    }
}