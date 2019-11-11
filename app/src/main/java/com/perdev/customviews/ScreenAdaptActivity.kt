package com.perdev.customviews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.perdev.viewlib.utils.SWUtil
import com.perdev.viewlib.utils.ScreenAdaptUtil
import kotlinx.android.synthetic.main.activity_screen_adapt.*

/**
 *
 * 屏幕适配方案
 *
 * SW适配符
 *
 * 今日头条
 *
 * 两种方案不能同时使用，否则今日头条会影响SW适配，
 * 因为SW适配方案是在xml时生效，根据手机的SW使用不同的dp值，
 * 而今日头条方案是在渲染时，dp转px时生效，在SW方案之后
 * */
class ScreenAdaptActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)



        //今日头条
//        ScreenAdaptUtil.doDensity(this)

        setContentView(R.layout.activity_screen_adapt)




        //SW适配符
//        tv_asa_sw.setText("SW = " + SWUtil.getSW(this) + "dp")


    }
}
