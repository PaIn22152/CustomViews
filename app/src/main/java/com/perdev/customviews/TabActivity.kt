package com.perdev.customviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.perdev.viewlib.widget.TabLayout
import kotlinx.android.synthetic.main.activity_tab.*

class TabActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)

        var title: ArrayList<String> = ArrayList()
        title.add("t1")
        title.add("t2")
        title.add("t3")
        title.add("t4")
        tl_at_tab.setTitle(title)

//        vp_at.adapter=MyPagerAdapter(supportFragmentManager,0)
//        tl_at_tab.bindViewPager(vp_at,object : TabLayout.OnPageChangeCallback{
//            override fun onPageSelected(
//                position: Int,
//                selectView: TextView?,
//                othersView: MutableList<TextView>?
//            ) {
//
//            }
//        })


        vp2_at.adapter = MyPager2Adapter(this)
        tl_at_tab.bindViewPager2(vp2_at, object : TabLayout.OnPageChangeCallback {
            override fun onPageSelected(
                position: Int,
                selectView: TextView?,
                othersView: MutableList<TextView>?
            ) {

            }
        })
    }
}
