package com.kashyap.threadtest

import android.os.Handler
import android.os.Message
import android.util.Log

class DownloadHandler(mainActivity: MainActivity) : Handler() {
    var mainActivity :MainActivity?=null
    init {
        this.mainActivity = mainActivity
    }
    val TAG = "sweet"
    override fun handleMessage(msg: Message) {
        downloadSong(msg.obj.toString())
    }


    private fun downloadSong(song : String) {
        Log.d(TAG, "run: starting download")
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Log.d(TAG, "$song : Download Completed")
        mainActivity?.runOnUiThread(object :Runnable{
            override fun run() {
                mainActivity?.log(song)
            }

        })

    }
}