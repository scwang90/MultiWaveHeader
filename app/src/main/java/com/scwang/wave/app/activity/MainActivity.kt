package com.scwang.wave.app.activity

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.scwang.wave.app.R
import com.scwang.wave.app.fragment.WaveConsoleFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener {

    enum class Tabs(val menuId: Int, val clazz: KClass<out androidx.fragment.app.Fragment>) {
        WavePair(R.id.navigation_home, WaveConsoleFragment::class),
        Wave(R.id.navigation_dashboard, WaveConsoleFragment::class),
        Wave2(R.id.navigation_notifications, WaveConsoleFragment::class),
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
                .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.content, Tabs.values().first { it.menuId==item.itemId }.clazz.java.newInstance())
                .commit()
        return true
    }

}
