package com.insac.can.sample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.insac.can.livebus.core.LiveBus
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        live_event_button.setOnClickListener { it ->
            it?.let {
                LiveBus.getInstance().setLiveEventValue("LiveEvent", "TestLiveEvent")
                openSecondActivity()
            }
        }

        single_live_event_button.setOnClickListener { it ->
            it?.let {
                LiveBus.getInstance().setSingleLiveEventValue("SingleLiveEvent", "TestLiveEvent")
                openSecondActivity()
            }
        }

        sticky_live_event_button.setOnClickListener { it ->
            it?.let {
                LiveBus.getInstance().setStickyLiveEventValue("StickyLiveEvent", "TestLiveEvent")
                openSecondActivity()
            }
        }

        bg_live_event_button.setOnClickListener { it ->
            it?.let {
                doAsync {
                    LiveBus.getInstance().postLiveEventValue("LiveEventBG", "TestLiveEvent")
                }
                openSecondActivity()
            }
        }

        bg_single_live_event_button.setOnClickListener { it ->
            it?.let {
                doAsync {
                    LiveBus.getInstance().postSingleLiveEventValue("SingleLiveEventBG", "TestLiveEvent")
                }
                openSecondActivity()
            }
        }

        bg_sticky_live_event_button.setOnClickListener { it ->
            it?.let {
                doAsync {
                    LiveBus.getInstance().postStickyLiveEventValue("StickyLiveEventBG", "TestLiveEvent")
                }
                openSecondActivity()
            }
        }

        /*sticky_single_live_event_button.setOnClickListener {
            it?.let {
                LiveBus.getInstance().postStickySingleEvent("StickySingleLiveEvent", "TestLiveEvent")
                openSecondActivity()
            }
        }*/

        open_second_activity_button.setOnClickListener{ it ->
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
