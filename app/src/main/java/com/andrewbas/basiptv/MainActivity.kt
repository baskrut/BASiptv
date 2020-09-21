package com.andrewbas.basiptv

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goToAddPlaylistActivity()

    }

   fun  goToAddPlaylistActivity(){
       intent = Intent(this, AddPlayListActivity::class.java)
       startActivity(intent)
   }

}
