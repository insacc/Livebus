package com.insac.can.sample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.insac.can.livebus.core.LiveBus
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        live_event_button.setOnClickListener {
            it?.let {
                LiveBus.getInstance().postLiveEvent("LiveEvent", "TestLiveEvent")
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
