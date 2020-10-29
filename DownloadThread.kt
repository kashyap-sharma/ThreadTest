package com.kashyap.threadtest

import android.os.Bundle
import android.os.Looper
import android.os.Message
import android.util.Log

class DownloadThread() : Thread() {
    var downloadHandler : DownloadHandler ?  = null
    override fun run() {
        Looper.prepare()
        downloadHandler = DownloadHandler();
        Looper.loop()
    }

}