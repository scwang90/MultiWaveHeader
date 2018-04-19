package com.scwang.wave.app.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.scwang.wave.app.R
import com.scwang.wave.app.fragment.WavePairFragment
import com.scwang.wave.app.util.StatusBarUtil
import kotlinx.android.synthetic.main.activity_user_login.*
import java.util.*


class UserLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)
        //状态栏透明和间距处理
        StatusBarUtil.immersive(this)

        login.setOnClickListener {
//            startActivity(Intent(this, MainActivity::class.java))
            FragmentActivity.start(this, WavePairFragment::class.java)
        }

        val waves = arrayOf("520,10,1.7,1.5,20", "220,0,1,1,-15")
        waveHeader.setWaves(TextUtils.join(" ", Arrays.asList(*waves)))
//        var waveHeader = findViewById(R.id.waveHeader) as MultiWaveHeader

//        waveHeader.setStartColorId(R.color.colorPrimary);
//        waveHeader.setCloseColorId(R.color.colorPrimaryDark);
//        waveHeader.setColorAlpha(.5f);
//
//        waveHeader.setWaveHeight(50);
//        waveHeader.setGradientAngle(360);
//        waveHeader.setProgress(.8f);
//        waveHeader.setVelocity(1f);


    }
}
