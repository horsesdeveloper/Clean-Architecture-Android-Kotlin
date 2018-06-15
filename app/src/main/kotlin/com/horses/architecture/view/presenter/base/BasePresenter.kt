package com.horses.architecture.view.presenter.base

/**
 * @author @briansalvattore on 28/02/2018.
 */
open class BasePresenter<V : BasePresenter.View> {

    protected var view: V? = null

    fun attachView(view: V) {
        this.view = view
    }

    open fun detachView() {
        view = null
    }

    interface View {
        fun showLoading()

        fun hideLoading()

        fun showError(message: String)
    }
}