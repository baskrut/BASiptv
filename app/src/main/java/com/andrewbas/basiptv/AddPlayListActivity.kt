package com.andrewbas.basiptv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andrewbas.basiptv.Utils.RetrofitInterface

class AddPlayListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_play_list)
    }

    fun getPlayListFromNet(){

    }

    fun getPlayListFromStorage(){

    }

    fun parsePlayList(){

    }

    fun downloadFile(link: String){

        val website: String = link
        val description: String = link

        val downloadService: RetrofitInterface = createService(RetrofitInterface::class, )
    }

}
