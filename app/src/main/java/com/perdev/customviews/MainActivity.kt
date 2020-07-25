package com.perdev.customviews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.perdev.viewlib.utils.ScreenAdaptUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tv_am_pie.setOnClickListener {
            startActivity<PieActivity>()
        }
        tv_am_dial.setOnClickListener {
            startActivity<DialActivity>()
        }
        tv_am_rcl.setOnClickListener {
            startActivity<RCLayoutActivity>()
        }
        tv_am_ccv.setOnClickListener {
            startActivity<CornerCoverActivity>()
        }
        tv_am_cpb.setOnClickListener {
            startActivity<ColorProgressBarActivity>()
        }
        tv_am_bitmap.setOnClickListener {
            startActivity<BitmapFilterActivity>()
        }
        tv_am_scroll.setOnClickListener {
            startActivity<ScrollTestActivity>()
        }
        tv_am_screen.setOnClickListener {
            startActivity<ScreenAdaptActivity>()
        }
        tv_am_snow.setOnClickListener {
            startActivity<SnowActivity>()
        }
        tv_am_7.setOnClickListener {
            startActivity<TabActivity>()
        }

        tv_am_6.setOnClickListener {

        }




    }
}
