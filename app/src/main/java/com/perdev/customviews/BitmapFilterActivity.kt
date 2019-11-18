package com.perdev.customviews

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.perdev.viewlib.jnipackage.BitmapFilter
import kotlinx.android.synthetic.main.activity_bitmap_filter.*
import org.jetbrains.anko.toast
import java.lang.Exception

class BitmapFilterActivity : AppCompatActivity() {


    var out1: Bitmap? = null
    var out2: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitmap_filter)


        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.ARGB_8888
        var org1 = BitmapFactory.decodeResource(
            this.resources,
            R.mipmap.bb1, options
        )
        out1 = Bitmap.createBitmap(
            org1.getWidth(),
            org1.getHeight(),
            Bitmap.Config.ARGB_8888
        )

        var org2 = BitmapFactory.decodeResource(
            this.resources,
            R.mipmap.panda, options
        )
        out2 = Bitmap.createBitmap(
            org2.getWidth(),
            org2.getHeight(),
            Bitmap.Config.ARGB_8888
        )

        tv_abf_filter_1.setOnClickListener {

            BitmapFilter.blackWhite(org1, out1)
            iv_abf_1.setImageBitmap(out1)

            BitmapFilter.blackWhite(org2, out2)
            iv_abf_2.setImageBitmap(out2)

        }

        tv_abf_filter_2.setOnClickListener {
            BitmapFilter.cold(org1, out1)
            iv_abf_1.setImageBitmap(out1)

            BitmapFilter.cold(org2, out2)
            iv_abf_2.setImageBitmap(out2)
        }

        tv_abf_filter_3.setOnClickListener {
            BitmapFilter.warm(org1, out1)
            iv_abf_1.setImageBitmap(out1)

            BitmapFilter.warm(org2, out2)
            iv_abf_2.setImageBitmap(out2)
        }

        tv_abf_filter_4.setOnClickListener {

            try {
                val light = et_abf_light.text.toString().toDouble()

                BitmapFilter.light(org1, out1, light)
                iv_abf_1.setImageBitmap(out1)

                BitmapFilter.light(org2, out2, light)
                iv_abf_2.setImageBitmap(out2)
            } catch (e: Exception) {
                toast("输入错误")
            }

        }

        tv_abf_filter_5.setOnClickListener {
            BitmapFilter.left2right(org1, out1)
            iv_abf_1.setImageBitmap(out1)

            BitmapFilter.left2right(org2, out2)
            iv_abf_2.setImageBitmap(out2)
        }

        tv_abf_filter_6.setOnClickListener {
            BitmapFilter.reversal(org1, out1)
            iv_abf_1.setImageBitmap(out1)

            BitmapFilter.reversal(org2, out2)
            iv_abf_2.setImageBitmap(out2)
        }

        tv_abf_filter_7.setOnClickListener {
            BitmapFilter.test(org1, out1)
            iv_abf_1.setImageBitmap(out1)

            BitmapFilter.test(org2, out2)
            iv_abf_2.setImageBitmap(out2)
        }

        tv_abf_filter.setOnClickListener {
            iv_abf_1.setImageBitmap(org1)
            iv_abf_2.setImageBitmap(org2)
        }
    }
}
