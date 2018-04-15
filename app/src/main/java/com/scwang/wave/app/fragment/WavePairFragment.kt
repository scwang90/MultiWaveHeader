package com.scwang.wave.app.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar

import com.scwang.wave.app.R
import kotlinx.android.synthetic.main.fragment_wave_pair.*

/**
 * A simple [Fragment] subclass.
 *
 */
class WavePairFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wave_pair, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seeVelocity.setOnSeekBarChangeListener(this)
        seekProgress.setOnSeekBarChangeListener(this)
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (seekBar == seekProgress) {
            multiWaveHeader.progress = 1f * progress / 100
        } else if (seekBar == seeVelocity) {
            multiWaveHeader.velocity = 1f * progress / 10
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }
}
