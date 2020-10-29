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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        thread = DownloadThread()
        thread?.name = "Kashyap's Thread"
        thread?.start()
        text?.append("I am running\n")
        initViews()
    }

    private fun runsome() {
        shows(true)
        //send message to download handler
        for (song in Playlist.songs){
            val message = Message.obtain()
            message.obj = song
            thread?.downloadHandler?.sendMessage(message)
        }
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

    private fun initViews() {
        //handler with looper handling the message send
        text = findViewById(R.id.textView)
        run = findViewById(R.id.button)
        clear = findViewById(R.id.button2)
        pr = findViewById(R.id.progressBar)
        pr?.visibility = View.GONE
        run?.setOnClickListener(this)
        clear?.setOnClickListener(this)
    }

    private fun shows(b: Boolean) {
        if(b){
            pr?.visibility = View.VISIBLE
        }else{
            pr?.visibility = View.GONE
        }
    }

    var text : TextView ? = null
    var run : Button ? = null
    var clear : Button ? = null
    var pr : ProgressBar ? = null
    val TAG = "Sweet"
    val MESSAGE_KEY = "message_key"
    var handler : Handler ? =null
    var thread: DownloadThread ? =null



}
