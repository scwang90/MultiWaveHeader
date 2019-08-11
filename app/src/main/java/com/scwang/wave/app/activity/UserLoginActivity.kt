package com.scwang.wave.app.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.scwang.wave.app.R
import com.scwang.wave.app.fragment.WaveConsoleFragment
import com.scwang.wave.app.util.StatusBarUtil
import kotlinx.android.synthetic.main.activity_user_login.*


class UserLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)
        //状态栏透明和间距处理
        StatusBarUtil.immersive(this)

        login.setOnClickListener {
//            startActivity(Intent(this, MainActivity::class.java))
            FragmentActivity.start(this, WaveConsoleFragment::class.java)
        }

    }
}
