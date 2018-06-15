package com.horses.architecture.view.ui.activity

import com.horses.architecture.R
import com.horses.architecture.view.presenter.JediPresenter
import com.horses.architecture.view.ui.base.HorsesBaseActivity
import javax.inject.Inject

class MainActivity : HorsesBaseActivity(), JediPresenter.View {

    @Inject
    lateinit var presenter: JediPresenter

    override fun getView() = R.layout.activity_main

    override fun onCreate() {
        component.inject(this)

        presenter.attachView(this)
        presenter.getList()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
