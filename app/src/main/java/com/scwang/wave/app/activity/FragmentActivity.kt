package com.scwang.wave.app.activity

import android.R.id.widget_frame
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import java.util.*

/**
 * Fragment 容器页面
 * Created by SCWANG on 2017/10/10.
 */

class FragmentActivity : AppCompatActivity() {
    //</editor-fold>

    var fragment: Fragment? = null
        private set
    //</editor-fold>

    //<editor-fold desc=方法">

    //@InjectExtra(value = EXTRA_FRAGMENT,remark = "Fragment类名")
    lateinit var mFragmentClazz: String
    private val fragmentClass: Class<*>
        @Throws(ClassNotFoundException::class)
        get() {
            var type: Class<*>? = typeCache[mFragmentClazz]
            if (type != null) {
                return type
            }
            type = Class.forName(mFragmentClazz)
            typeCache[mFragmentClazz] = type
            return type
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFragmentClazz = intent.getStringExtra(EXTRA_FRAGMENT)
        val frameLayout = FrameLayout(this)
        frameLayout.id = widget_frame
        setContentView(frameLayout)
        replaceFragment()
    }

    private fun replaceFragment() {
        try {
            val fragment = fragmentClass.newInstance() as Fragment
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(widget_frame, fragment)
            transaction.commit()
            this.fragment = fragment
        } catch (e: Exception) {
            e.printStackTrace()
            //            AfExceptionHandler.handle(e, "AfFragmentActivity Fragment 类型错误：" + mFragmentClazz);
        }

    }

    companion object {

        private const val EXTRA_FRAGMENT = "EXTRA_FRAGMENT"

        //<editor-fold desc="跳转封装">
        private fun start(context: Context?, clazz: Class<*>, vararg params: Any) {
            if (context != null && Fragment::class.java.isAssignableFrom(clazz)) {
                context.startActivity(newIntent(clazz, context, *params))
            }
        }

        fun start(activity: Activity, clazz: Class<*>, vararg params: Any) {
            start(activity as Context, clazz, *params)
        }

        fun start(fragment: Fragment?, clazz: Class<*>, vararg params: Any) {
            if (fragment != null) {
                start(fragment.activity as Context, clazz, *params)
            }
        }

        private fun newIntent(clazz: Class<*>, context: Context, vararg params: Any): Intent {
            val intent = Intent(context, FragmentActivity::class.java)
            intent.putExtra(EXTRA_FRAGMENT, clazz.name)
            return intent
        }

        //<editor-fold desc="反射缓存">
        private val typeCache = HashMap<String, Class<*>>()
    }

    //</editor-fold>


}
