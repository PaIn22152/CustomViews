package com.xy.viewdemo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo
 * Date       2024/05/08 - 11:27
 * Author     Payne.
 * About      类描述：
 */
class TextActivity : AppCompatActivity() {
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text)
        
        findViewById<Button>(R.id.btn_change).setOnClickListener {
            findViewById<TextImageView>(R.id.tiv_view).changeContent(RandomUtil.randomStr(4))
        }
    
    
        
    }
}