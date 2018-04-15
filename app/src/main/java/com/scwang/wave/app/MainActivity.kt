package com.scwang.wave.app

import android.os.Bundle
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.scwang.wave.app.fragment.WavePairFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener {

    enum class Tabs(val menuId: Int, val clazz: KClass<out Fragment>) {
        WavePair(R.id.navigation_home, WavePairFragment::class),
        Wave(R.id.navigation_dashboard, WavePairFragment::class),
        Wave2(R.id.navigation_notifications, WavePairFragment::class),
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(this)
        navigation.selectedItemId = R.id.navigation_home
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        supportFragmentManager
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.content, Tabs.values().first { it.menuId==item.itemId }.clazz.java.newInstance())
                .commit()
        return true
    }

}
