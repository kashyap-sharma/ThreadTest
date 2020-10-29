package com.kashyap.threadtest

import android.os.Bundle
import android.os.Message
import android.util.Log

class DownloadThread() : Thread() {
//class DownloadThread(songName: String) : Thread() { //commented bcz this was downoading all together
    val TAG = "sweet"
    //var songNames = ""


//    init {
//        this.songNames = songName
//    }
    // Commented bcz it was downloading all together.


    override fun run() {
        //this part is added to download song one by one
        for (song in Playlist.songs){
            downloadSong(song)
        }

        //downloadSong()  //commented bcz it was downloading all together
    }
    private fun downloadSong(song : String) {
        Log.d(TAG, "run: starting download")
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Log.d(TAG, "$song : Download Completed")
        
    }
}