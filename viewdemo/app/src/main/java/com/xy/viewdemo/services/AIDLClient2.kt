package com.xy.viewdemo.services

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.xy.viewdemo.AidlManager
import com.xy.viewdemo.ICallback
import com.xy.viewdemo.log

/**
 * Project    ViewDemo
 * Path       com.xy.viewdemo.services
 * Date       2024/05/17 - 18:31
 * Author     Payne.
 * About      类描述：
 */
class AIDLClient2 : Service() {
    companion object {
        lateinit var client2: AIDLClient2
        private var bound = false
    }
    
    private lateinit var serviceConnection: ServiceConnection
    private var aidlManager: AidlManager? = null
    override fun onBind(intent: Intent?): IBinder? = null
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        "AIDLClient2 onStartCommand".log()
        client2 = this
        init()
        return super.onStartCommand(intent, flags, startId)
    }
    
    private fun init() {
        "AIDLClient2 init  bound=$bound".log()
        if (bound) {
            return
        }
        serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName, service: IBinder) {
                bound = true
                "AIDLClient2 onServiceConnected  name=$name".log()
                aidlManager = AidlManager.Stub.asInterface(service)
                aidlManager!!.registerCallback(object : ICallback.Stub() {
                    override fun server2Client(data: ByteArray) {
                        "AIDLClient2 server2Client data=$data  size=${data.size}  data[0]=${data[0]}".log()
                        data[0] = (data[0] + 1).toByte()
                    }
                })
            }
            
            override fun onServiceDisconnected(name: ComponentName) {
                bound = false
                "AIDLClient2 onServiceDisconnected  name=$name".log()
                aidlManager = null
            }
        }
        
        val tracker = Intent()
        tracker.setAction("com.xy.viewdemo.aidl.action")
        tracker.setPackage("com.xy.viewdemo")
        
        
        //两种方式都可以绑定
//        tracker.setComponent(new ComponentName("com.android.gps.tracker","com.jimi.wifikit.aidl.WifiKitAidlService"));
        val bound: Boolean = bindService(tracker, serviceConnection, BIND_AUTO_CREATE)
        "AIDLClient2 bound=$bound".log()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        "AIDLClient2 onDestroy".log()
    }
    
    fun client2Server() {
        aidlManager?.client2Server("I'm client 2.")?.log()
    }
    
    
}