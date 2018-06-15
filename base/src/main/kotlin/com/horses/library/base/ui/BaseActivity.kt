package com.horses.library.base.ui

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.MenuItem
import android.view.ViewGroup
import com.horses.library.PermissionsActivity
import com.horses.library.base.R
import com.horses.library.base.extensions.enableError

/**
 * @author @briansalvattore on 25/11/2017.
 * Not modify!!!
 */
abstract class BaseActivity : PermissionsActivity() {

    protected var toolbar: Toolbar? = null
    protected var drawerLayout: DrawerLayout? = null
    private var drawerToggle: ActionBarDrawerToggle? = null
    private var navigationView: NavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getView())

        onCreate()
    }

    @SuppressLint("PrivateResource")
    protected fun setSupportActionBar(title: String): ActionBar {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val white = ContextCompat.getColor(this, android.R.color.white)

        toolbar?.setTitleTextColor(white)

        val drawable = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white_24dp)
        drawable?.setColorFilter(white, PorterDuff.Mode.SRC_ATOP)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(drawable)
        supportActionBar!!.title = title

        return supportActionBar!!
    }

    fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    protected fun setupDrawer() {
        drawerLayout = findViewById(R.id.drawer)

        drawerToggle = object : ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {}
        drawerLayout!!.addDrawerListener(drawerToggle as ActionBarDrawerToggle)
    }

    protected fun setupDrawerContent(listener: SimpleNavigationItemSelectedListener) {
        navigationView = findViewById(R.id.nav)

        navigationView!!.setNavigationItemSelectedListener { item ->
            listener.onNavigationItemSelected(item)
            drawerLayout!!.closeDrawers()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            if (drawerToggle != null) {
                return drawerToggle!!.onOptionsItemSelected(item)
            } else onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        if (drawerToggle != null)
            drawerToggle!!.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (drawerToggle != null)
            drawerToggle!!.onConfigurationChanged(newConfig)
    }

    @SuppressLint("RtlHardcoded")
    override fun onBackPressed() {
        if (drawerLayout != null) {
            if (drawerLayout!!.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout!!.closeDrawers()
                return
            }
        }
        super.onBackPressed()
    }

    abstract fun getView(): Int
    open fun onCreate() { }

    interface SimpleNavigationItemSelectedListener {
        fun onNavigationItemSelected(item: MenuItem)
    }

    fun activeAllWrappers() {
        activeAllWrappers(findViewById(android.R.id.content))
    }

    private fun activeAllWrappers(parent: ViewGroup) {
        for (i in 0..parent.childCount) {

            val view = parent.getChildAt(i)

            if (view is TextInputLayout) {
                view.enableError()
            }
            else if (view is ViewGroup) {
                activeAllWrappers(parent.getChildAt(i) as ViewGroup)
            }
        }
    }

    fun hideAllWrappers() {
        activeAllWrappers(findViewById(android.R.id.content))
    }
}