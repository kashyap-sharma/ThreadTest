package com.kashyap.threadtest

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() , View.OnClickListener{
    var text : TextView ? = null
    var run : Button ? = null
    var clear : Button ? = null
    var pr : ProgressBar ? = null
    val TAG = "Sweet"
    val MESSAGE_KEY = "message_key"
    var handler : Handler ? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        //handler with looper handling the message sned
        handler= object : Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                text?.text = msg.data.getString(MESSAGE_KEY)
            }
        }


        text = findViewById(R.id.textView)
        run = findViewById(R.id.button)
        clear = findViewById(R.id.button2)
        pr = findViewById(R.id.progressBar)
        pr?.visibility = View.GONE
        run?.setOnClickListener(this)
        clear?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.button -> runsome()
            R.id.button2 -> clearsome()
        }
    }

    private fun clearsome() {
        text?.text = ""
    }

    private fun shows(b: Boolean) {
        if(b){
            pr?.visibility = View.VISIBLE
        }else{
            pr?.visibility = View.GONE
        }
    }

    private fun runsome() {
        shows(true)

//        for (song in Playlist.songs){
//            val thread =DownloadThread(song)
//            thread.name = "Kashyap's Thread"
//            thread.start()
//        }
        // Downloading songs together

        val thread =DownloadThread()
        thread.name = "Kashyap's Thread"
        thread.start()
        text?.append("I am running\n")

    }



}