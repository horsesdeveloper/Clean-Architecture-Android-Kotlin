package com.horses.architecture.di

import com.horses.architecture.HorsesApplication
import com.horses.architecture.di.component.DaggerPresenterComponent
import com.horses.architecture.di.component.PresenterComponent
import javax.inject.Singleton

/**
 * @author @briansalvattore on 01/03/2018.
 */
@Singleton
object Orchestrator {

    val presenterComponent: PresenterComponent by lazy {
        DaggerPresenterComponent
                .builder()
                .appComponent(HorsesApplication.appComponent)
                .build()
    }
}