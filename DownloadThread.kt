package com.kashyap.threadtest

import android.os.Bundle
import android.os.Looper
import android.os.Message
import android.util.Log

class DownloadThread(mainActivity: MainActivity) : Thread() {
    var mainActivity :MainActivity?=null
    init {
        this.mainActivity = mainActivity
    }
    var downloadHandler : DownloadHandler ?  = null
    override fun run() {
        Looper.prepare()
        downloadHandler = DownloadHandler(mainActivity!!);
        Looper.loop()
    }

}