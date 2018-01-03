package com.scwang.multiwaveheader.app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user_login.*

class UserLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)
        //状态栏透明和间距处理
        StatusBarUtil.immersive(this)

        login.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}
