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
                openSecondActivity()
            }
        }

        single_live_event_button.setOnClickListener {
            it?.let {
                LiveBus.getInstance().postSingleEvent("SingleLiveEvent", "TestLiveEvent")
                openSecondActivity()
            }
        }

        sticky_live_event_button.setOnClickListener {
            it?.let {
                LiveBus.getInstance().postStickyEvent("StickyLiveEvent", "TestLiveEvent")
                openSecondActivity()
            }
        }

        sticky_single_live_event_button.setOnClickListener {
            it?.let {
                LiveBus.getInstance().postStickySingleEvent("StickySingleLiveEvent", "TestLiveEvent")
                openSecondActivity()
            }
        }

        open_second_activity_button.setOnClickListener{
            it?.let {
                openSecondActivity()
            }
        }
    }

    private fun openSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}
