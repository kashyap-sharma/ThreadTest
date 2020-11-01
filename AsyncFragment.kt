package com.kashyap.threadtest

import android.content.Context
import android.os.AsyncTask
import com.kashyap.threadtest.AsyncFragment.MyTaskHandler
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.kashyap.threadtest.AsyncFragment

class AsyncFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    interface MyTaskHandler {
        fun handleTask(message: String?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: fragment attached")
        if (context is MyTaskHandler) {
            mTaskHandler = context
        }
    }

    companion object {
        const val TAG = "sweet"
        private var mTaskHandler: MyTaskHandler? = null
    }

    public fun runTask(){
        val myTask = MyTask()
        myTask?.execute("Red","Green","Blue","Yellow")
    }

    class MyTask : AsyncTask<String?, String?, String?>() {
        override fun doInBackground(vararg params: String?): String? {
            for (value in params) {
                Log.d("Sweet", "doInBackground: $value")

                //if Progress cancelled
                if (isCancelled){
                    publishProgress("Task is cancelled.")
                    break
                }

                publishProgress(value)
                //it will send values for OnProgressUpdate
                try {
                    Thread.sleep(3000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            return "Download Completed" //this will be returned in onPostExecute
        }

        override fun onProgressUpdate(vararg values: String?) {
            mTaskHandler?.handleTask(values[0])
        }
        override fun onPostExecute(result: String?) {
            mTaskHandler?.handleTask(result)
        }

    }
}