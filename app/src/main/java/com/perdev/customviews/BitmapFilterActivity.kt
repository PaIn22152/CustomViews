package com.perdev.customviews

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.perdev.viewlib.jnipackage.BitmapFilter
import kotlinx.android.synthetic.main.activity_bitmap_filter.*

class BitmapFilterActivity : AppCompatActivity() {


    var filter = false

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

        tv_abf_filter.setOnClickListener {
            if (!filter) {
                BitmapFilter.cold(org1, out1)
                iv_abf_1.setImageBitmap(out1)

                BitmapFilter.blackWhite(org2, out2)
                iv_abf_2.setImageBitmap(out2)
            } else {
                iv_abf_1.setImageBitmap(org1)
                iv_abf_2.setImageBitmap(org2)
            }
            filter = !filter
        }
    }
}
