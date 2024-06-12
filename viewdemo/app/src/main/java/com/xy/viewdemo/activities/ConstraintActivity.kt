package com.xy.viewdemo.activities

import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Placeholder
import com.xy.viewdemo.R

class ConstraintActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint)
        
        
        /**
         * constraintSet test code  start
         * */
        /*val constraint_root = findViewById<ConstraintLayout>(R.id.constraint_root)
        val v1 = findViewById<View>(R.id.v1)
        v1.setOnClickListener {
            val constraintSet = ConstraintSet().apply {
                isForceId = false//防止布局中有无id控件时报错，需要设置isForceId=false
                clone(this@ConstraintActivity, R.layout.activity_constraint2)  //从xml中复制约束条件
//                clone(constraint_root)
//                clear(R.id.v1, ConstraintSet.START)//移除约束
//                clear(R.id.v1, ConstraintSet.TOP)
//                connect(//重新建立约束
//                    R.id.v1,
//                    ConstraintSet.BOTTOM,
//                    ConstraintSet.PARENT_ID,
//                    ConstraintSet.BOTTOM
//                )
//                connect(
//                    R.id.v1,
//                    ConstraintSet.END,
//                    ConstraintSet.PARENT_ID,
//                    ConstraintSet.END
//                )
            }
            TransitionManager.beginDelayedTransition(constraint_root)//过渡动画
            constraintSet.applyTo(constraint_root)//布局更新
        }*/
        /**
         * constraintSet test code  start
         * */
        
        /**
         * flow test code  start
         * */
//        val flow = findViewById<Flow>(R.id.flow)
//        val constraint_root = findViewById<ConstraintLayout>(R.id.constraint_root)
//
//        val wrap = arrayOf(Flow.WRAP_NONE, Flow.WRAP_CHAIN, Flow.WRAP_ALIGNED)
//        var index = 0
//        findViewById<View>(R.id.tv1).setOnClickListener {
//            TransitionManager.beginDelayedTransition(constraint_root)
//            flow.setWrapMode(wrap[(++index) % wrap.size])
//        }
        /**
         * flow test code  start
         * */
        
        /**
         * placeholder test code  start
         * */
        val placeholder = findViewById<Placeholder>(R.id.placeholder)
        val constraint_root = findViewById<ConstraintLayout>(R.id.constraint_root)
        
        findViewById<View>(R.id.tv1).setOnClickListener {
            TransitionManager.beginDelayedTransition(constraint_root)
            placeholder.setContentId(R.id.tv1)
        }
        findViewById<View>(R.id.tv2).setOnClickListener {
            TransitionManager.beginDelayedTransition(constraint_root)
            placeholder.setContentId(R.id.tv2)
        }
        findViewById<View>(R.id.tv3).setOnClickListener {
            TransitionManager.beginDelayedTransition(constraint_root)
            placeholder.setContentId(R.id.tv3)
        }
        findViewById<View>(R.id.tv4).setOnClickListener {
            TransitionManager.beginDelayedTransition(constraint_root)
            placeholder.setContentId(R.id.tv4)
        }
        /**
         * placeholder test code  end
         * */
        
        
    }
}