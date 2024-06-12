package com.xy.viewdemo.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.xy.viewdemo.R
import com.xy.viewdemo.log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.locks.ReentrantLock

class ThreadActivity : AppCompatActivity() {
    
    val handler by lazy { Handler(Looper.getMainLooper()) }
    var path = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)
        
        path = externalCacheDir!!.path + "/testfile.test"
        
        val secondThread = MyThread()
        handler.postDelayed({
            secondThread.start()
        }, 2000)
        handler.postDelayed({
//            secondThread.interrupt()
        }, 5000)
        
    }
    
    private val monitor = Object()
    
    private fun sync1() {
        synchronized(monitor) {
            monitor.wait()
            
        }
    }
    
    private fun sync2() {
        synchronized(monitor) {
            monitor.notifyAll()
        }
    }
    
    inner class MyThread2 : Thread() {
        override fun run() {
        
        }
    }
    
    inner class MyThread : Thread() {
        
        val queue = LinkedBlockingQueue<Int>()
        
        val file = File(path)
        lateinit var fos: FileOutputStream
        lateinit var fis: FileInputStream
        lateinit var serverSocket: ServerSocket
        lateinit var clientSocket: Socket
        val lock = ReentrantLock().apply {
            lock()
        }
        
        
        override fun run() {
            name = "MyThread888"
            "MyThread start  path=$path".log()

//            if (file.exists()) {
//                file.delete()
//            }
//            file.parentFile.mkdirs()
//            file.createNewFile()
//            fos = FileOutputStream(path)
//            fis = FileInputStream(path)
            
            serverSocket = ServerSocket(6060)
            
            
            
            try {
                while (!isInterrupted) {
//                    "while run".log()

//                    Thread.sleep(3000)
                    
                    //阻塞不响应中断
//                    serverSocket.accept()
//                    "serverSocket.accept()".log()

//                    clientSocket = Socket("localhost", 6060)
//                    clientSocket.getInputStream().read()
//                    "clientSocket.getInputStream().read()".log()

//                    lock.lock()

//                    val data = ByteArray(100)
//                    val read = fis.read(data)
//                    "is reading  read=$read".log()

//                    fos.write(1)
//                    "is writing ".log()
                    
                    //阻塞响应中断
//                    val i = queue.take()
//                    "while i=$i".log()
                }
            } catch (e: InterruptedException) {
                "MyThread e=$e".log()
            } finally {
                "finally MyThread end".log()
            }
            
        }
    }
}