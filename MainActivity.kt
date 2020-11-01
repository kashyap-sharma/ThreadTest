package com.kashyap.threadtest

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() , View.OnClickListener, AsyncFragment.MyTaskHandler{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //
        initViews()
        mFragment = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG) as AsyncFragment?
        if (mFragment == null){
            mFragment = AsyncFragment()
            supportFragmentManager.beginTransaction().add(mFragment!!,FRAGMENT_TAG).commit()
        }
    }

    private fun runsome() {
       mFragment?.runTask()
    }

    override fun handleTask(message: String?) {
        textss?.append(message+"\n")
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
    val MESSAGE_KEY = "message_key"
    val FRAGMENT_TAG = "fragment_tag"
    var mtaskRunning:Boolean = true
    private var mFragment:AsyncFragment?=null

    



}
