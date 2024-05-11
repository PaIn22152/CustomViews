package com.xy.viewdemo.utils

import java.util.Random


/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo
 * Date       2024/05/08 - 15:57
 * Author     Payne.
 * About      类描述：
 */
class RandomUtil {
    companion object {
        val random = Random()
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
            return s
        }
        
        fun randomText(len: Int): String {
            var s = ""
            var space = 0
            var wordLen = 0
            var wordSize = 0
            for (i in 0 until len) {
                if (i + space >= len - 1) {
                    break
                }
                s += randomChar()
                wordLen++
                if (wordLen >= 3) {
                    if (randomInt(8 - wordLen) == 0) {
                        s += ' '
                        space++
                        wordSize++
                        wordLen = 0
                    } else if (wordSize >= 3) {
                        if (randomInt(8 - wordSize) == 0) {
                            if (randomInt(3) == 0) {
                                s += '.'
                                s += ' '
                            } else {
                                s += ','
                                s += ' '
                            }
                            space++
                            space++
                            wordLen = 0
                            wordSize = 0
                        }
                    }
                }
                
            }
            s += '.'
            return s.lowercase()
        }
        
    }
}