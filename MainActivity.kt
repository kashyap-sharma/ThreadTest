package com.kashyap.threadtest

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.rangeTo
import androidx.fragment.app.FragmentManager
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() , View.OnClickListener{
    //Example of ExecuterService
    //Classes used  : Work
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

    }

    private fun runsome() {
        for ( i in 0 until 10){
            val work = Work(i+1)
            executorService?.execute(work)
        }
    }



    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.button -> runsome()
            R.id.button2 -> clearsome()
        }
    }

    private fun clearsome() {
        textss?.text = ""
    } 

    private fun initViews() {

        textss = findViewById(R.id.textView)
        run = findViewById(R.id.button)
        clear = findViewById(R.id.button2)
        pr = findViewById(R.id.progressBar)
        pr?.visibility = View.GONE
        run?.setOnClickListener(this)
        clear?.setOnClickListener(this)
        textss?.text="I am running\n"

        executorService = Executors.newFixedThreadPool(5)
        //Creating pool of 5 threads
    }

    override fun onDestroy() {
        super.onDestroy()
        executorService?.shutdown()
    }

    public fun shows(b: Boolean) {
        if(b){
            pr?.visibility = View.VISIBLE
        }else{
            pr?.visibility = View.GONE
        }
    }
    public fun log(b: String) {
        Log.d(TAG, "log: $b")
    }

    companion object{
        private val TAG = "Sweet"
        var textss : TextView ? = null
    }

    var run : Button ? = null
    var clear : Button ? = null
    var pr : ProgressBar ? = null
    var executorService: ExecutorService ?=null



    



}
