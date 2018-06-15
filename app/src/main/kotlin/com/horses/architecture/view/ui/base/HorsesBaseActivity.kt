package com.horses.architecture.view.ui.base

import com.horses.architecture.di.Orchestrator
import com.horses.library.base.ui.BaseActivity

abstract class HorsesBaseActivity : BaseActivity() {

    protected val component by lazy { Orchestrator.presenterComponent }

    fun showLoading() {

    }

    fun hideLoading() {

    }

    fun showError(message: String) {

    }
}