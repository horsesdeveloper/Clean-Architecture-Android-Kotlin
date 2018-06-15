package com.horses.library.base.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.horses.library.base.extensions.enableError
import java.io.Serializable

/**
 * @author @briansalvattore on 03/01/2018.
 */
abstract class BaseFragment : Fragment() {

    private lateinit var parent: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(getFragmentView(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parent = view
        onCreate()
    }

    // TODO: 05/03/2018 change for extension
    @SuppressWarnings("unused")
    protected fun startActivity(cls: Class<out AppCompatActivity>, vararg extras: Serializable) {

        val intent = Intent(activity, cls)

        for (i in extras.indices) {
            intent.putExtra("extra" + i, extras[i])
        }

        startActivity(intent)
    }

    abstract fun getFragmentView(): Int
    abstract fun onCreate()

    protected fun setTitle(title: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).setTitle(title)
        }
    }

    fun activeAllWrappers() {
        activeAllWrappers(parent as ViewGroup)
    }

    fun hideAllWrappers() {
        activeAllWrappers(parent as ViewGroup)
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

    /** ↓↓↓ begin start custom activity ↓↓↓ */
}