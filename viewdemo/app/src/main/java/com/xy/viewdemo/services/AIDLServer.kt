package com.xy.viewdemo.services

import android.app.Service
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.IBinder
import android.os.RemoteCallbackList
import com.xy.viewdemo.AidlManager
import com.xy.viewdemo.ICallback
import com.xy.viewdemo.log


/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo.services
 * Date       2024/05/17 - 18:30
 * Author     Payne.
 * About      类描述：
 */
class AIDLServer : Service() {
    
    
    private val myBinder by lazy { MyBinder() }
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        "AIDLServer onStartCommand".log()
        return super.onStartCommand(intent, flags, startId)
    }
    
    override fun onBind(intent: Intent): IBinder {
        "AIDLServer onBind".log()
        return myBinder
    }
    
    override fun onDestroy() {
        super.onDestroy()
        callbackList.kill()
    }
    
    companion object {
        private val callbackList: RemoteCallbackList<ICallback> by lazy { RemoteCallbackList<ICallback>() }
        private val data = byteArrayOf(0x00, 0x01, 0x02)
        
        class MyBinder : AidlManager.Stub() {
            override fun client2Server(name: String): String {
                return "server add.  input=$name"
            }
            
            override fun registerCallback(back: ICallback) {
                callbackList.register(back)
            }
            
        }
        
        fun server2Client(): Boolean {
            "AIDLServer server2Client data=$data  size=${data.size}  data[0]=${data[0]}".log()
            try {
                val count = callbackList.beginBroadcast()
                for (i in 0 until count) {
                    callbackList.getBroadcastItem(i).server2Client(data)
                }
                return true
            } catch (e: Exception) {
                e.printStackTrace()
                "onLocationUpdate  e=$e".log()
            } finally {
                try {
                    //finishBroadcast必须和beginBroadcast()成对出现
                    callbackList.finishBroadcast()
                } catch (ee: Exception) {
                }
            }
            return false
        }
        
    }
}