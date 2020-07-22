package com.perdev.customviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.perdev.viewlib.utils.SWUtil
import com.perdev.viewlib.utils.ScreenAdaptUtil
import kotlinx.android.synthetic.main.activity_screen_adapt.*
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager


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
        //在setContentView之前调用
//        ScreenAdaptUtil.doDensity(this)



        setContentView(R.layout.activity_screen_adapt)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)


        //SW适配符
        //通过ScreenMatch插件，生成values-sw360dp等文件夹，在xml中使用dimens下的值
//        tv_asa_sw.setText("SW = " + SWUtil.getSW(this) + "dp")

        val metrices = resources.displayMetrics
        val dpi = metrices.densityDpi
        val density = metrices.density
        val width = metrices.widthPixels.toFloat()
        val height = metrices.heightPixels.toFloat()

        Log.i("35214_dpi==>", dpi.toString() + "")
        Log.i("35214_density==>", density.toString() + "")
        Log.i("35214_width==>", width.toString() + "")
        Log.i("35214_height==>", height.toString() + "")

        Log.i("35214_scaledDensity==>", metrices.scaledDensity.toString() + "")
        Log.i("35214_xdpi==>", metrices.xdpi.toString() + "")
        Log.i("35214_ydpi==>", metrices.ydpi.toString() + "")


    }
}
