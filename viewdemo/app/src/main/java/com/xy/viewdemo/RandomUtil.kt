package com.xy.viewdemo

import kotlin.random.Random

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo
 * Date       2024/05/08 - 15:57
 * Author     Payne.
 * About      类描述：
 */
class RandomUtil {
    companion object {
        val random = Random(100)
        val chars = ArrayList<Char>()
        
        init {
            for (c in 'a'..'z') {
                chars.add(c)
            }
            for (c in 'A'..'Z') {
                chars.add(c)
            }
        }
        
        fun randomInt(max: Int): Int {
            return random.nextInt(max)
        }
        
        fun randomChar(): Char {
            return chars[randomInt(chars.size)]
        }
        
        fun randomStr(len: Int): String {
            var s = ""
            for (i in 0 until len) {
                s += randomChar()
            }
            if (randomInt(10) == 0) {
                s += ' '
            }
            return s
        }
        
    }
}