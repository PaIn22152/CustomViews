package com.perdev.customviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.perdev.customviews.utils.RXCallback
import com.perdev.customviews.utils.rxInterval
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_color_progress_bar.*
import javax.security.auth.callback.Callback

class ColorProgressBarActivity : AppCompatActivity() {

    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_progress_bar)

        var p = 0
        cpb_acpb_p.setMax(1000)
        cpb_acpb_p.setProgress(p)

        disposable = rxInterval(10, true, object : RXCallback {
            override fun call() {
                if (p <= 1000) {
                    cpb_acpb_p.setProgress(p++)
                } else {
                    disposable!!.dispose()
                }

            }
        })

    }
}
