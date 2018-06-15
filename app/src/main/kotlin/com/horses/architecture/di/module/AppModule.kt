package com.horses.architecture.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author by GERARDO on 6/04/2018.
 */
@Module
class AppModule (val context: Context){

    @Provides
    @Singleton
    fun providesContext() = context
}