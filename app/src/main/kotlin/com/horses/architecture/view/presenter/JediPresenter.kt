package com.horses.architecture.view.presenter

import android.util.Log
import com.horses.architecture.domain.model.Jedi
import com.horses.architecture.domain.usecase.GetJedis
import com.horses.architecture.view.presenter.base.BasePresenter
import io.reactivex.observers.DisposableObserver

class JediPresenter(var getJedis : GetJedis): BasePresenter<JediPresenter.View>() {

    fun getList() {
        getJedis.execute(object : DisposableObserver<List<Jedi>>() {
            override fun onComplete() {
                view?.hideLoading()
            }

            override fun onNext(value: List<Jedi>) {
                Log.d("JediPresenter", "onNext() called with: value = [ $value ]")
            }

            override fun onError(e: Throwable) {
                view?.let {
                    it.hideLoading()
                    it.showError(e.message ?: "Error, try again")
                }
            }

        })
    }

    override fun detachView() {
        getJedis.dispose()
        super.detachView()
    }

    interface View : BasePresenter.View {

    }
}