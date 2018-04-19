package com.scwang.wave.app.fragment


import android.content.res.Resources
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.larswerkman.lobsterpicker.OnColorListener
import com.scwang.wave.app.R
import com.scwang.wave.app.util.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_wave_pair.*
import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar

/**
 * A simple [Fragment] subclass.
 */
class WavePairFragment : Fragment(), DiscreteSeekBar.OnProgressChangeListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wave_pair, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        StatusBarUtil.immersive(activity)
        StatusBarUtil.setPaddingSmart(context, toolbar)
        toolbar.setNavigationOnClickListener {
            activity.finish()
        }

        seekAngle.progress = multiWaveHeader.gradientAngle
        seekVelocity.progress = (multiWaveHeader.velocity * 10).toInt()
        seekAlpha.progress = (multiWaveHeader.colorAlpha * 100).toInt()
        seekProgress.progress = (multiWaveHeader.progress * 100).toInt()
        seekWave.progress = (multiWaveHeader.waveHeight / Resources.getSystem().displayMetrics.density).toInt()
        checkBoxRunning.isChecked = multiWaveHeader.isRunning
        checkBoxDirection.isChecked = multiWaveHeader.scaleY == -1f

        seekWave.setOnProgressChangeListener(this)
        seekAngle.setOnProgressChangeListener(this)
        seekVelocity.setOnProgressChangeListener(this)
        seekProgress.setOnProgressChangeListener(this)
        seekAlpha.setOnProgressChangeListener(this)
        seekNumber.setOnProgressChangeListener(this)
        checkBoxRunning.setOnCheckedChangeListener({_,value->
            if (value) {
                multiWaveHeader.start()
            } else {
                multiWaveHeader.stop()
            }
        })
        checkBoxDirection.setOnCheckedChangeListener({_,value->
            multiWaveHeader.scaleY = if (value) -1f else 1f
            toolbar.setBackgroundColor(if (value) ContextCompat.getColor(context,R.color.colorPrimary) else 0)
        })

        sliderStartColor.addOnColorListener(object : OnColorListener{
            override fun onColorChanged(color: Int) {
                onColorSelected(color)
            }
            override fun onColorSelected(color: Int) {
                multiWaveHeader.startColor = color
            }
        })
        sliderCloseColor.addOnColorListener(object : OnColorListener{
            override fun onColorChanged(color: Int) {
                onColorSelected(color)
            }
            override fun onColorSelected(color: Int) {
                multiWaveHeader.closeColor = color
            }
        })

    }

    override fun onProgressChanged(seekBar: DiscreteSeekBar, value: Int, fromUser: Boolean) {
        when (seekBar) {
            seekProgress -> multiWaveHeader.progress = 1f * value / 100
            seekVelocity -> multiWaveHeader.velocity = 1f * value / 10
            seekAlpha -> multiWaveHeader.colorAlpha = 1f * value / 100
            seekAngle -> multiWaveHeader.gradientAngle = value
        }
    }

    override fun onStartTrackingTouch(seekBar: DiscreteSeekBar) {
    }

    override fun onStopTrackingTouch(seekBar: DiscreteSeekBar) {
        if (seekWave == seekBar) {
            multiWaveHeader.waveHeight = seekBar.progress
        } else if (seekNumber == seekBar) {
            if (seekBar.progress == 2) {
                /**
                 * 格式-format
                 * offsetX offsetY scaleX scaleY velocity（dp/s）
                 * 水平偏移量 竖直偏移量 水平拉伸比例 竖直拉伸比例 速度
                 */
                multiWaveHeader.setWaves("0,0,1,1,25\n90,0,1,1,25")
            } else {
                val waves = "70,25,1.4,1.4,-26\n100,5,1.4,1.2,15\n420,0,1.15,1,-10\n520,10,1.7,1.5,20\n220,0,1,1,-15".split("\n")
                multiWaveHeader.setWaves(waves.subList(0, seekBar.progress).joinToString("\n"))
            }
        }
    }
}
