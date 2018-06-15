package com.horses.architecture.view.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.horses.architecture.R
import com.horses.architecture.data.entity.JediEntity
import com.horses.architecture.data.repository.JedisRepository
import com.horses.architecture.view.presenter.JediPresenter
import com.horses.library.base.extensions.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        startActivity(PokemonListActivity::class.java)

        //JediPresenter().getList()
    }
}
