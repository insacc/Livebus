package com.insac.can.sample

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.insac.can.livebus.core.LiveBus
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        LiveBus.getInstance().subscribeLiveEvent("LiveEvent", String::class.java)
                .observe(this, Observer { it ->
                    it?.let {
                        live_event.text = it
                    }
                })
        LiveBus.getInstance().subscribeSingleLiveEvent("SingleLiveEvent", String::class.java)
                .observe(this, Observer { it ->
                    it?.let {
                        single_live_event.text = it
                    }
                })
        LiveBus.getInstance().subscribeStickyLiveEvent("StickyLiveEvent", String::class.java)
                .observe(this, Observer { it ->
                    it?.let {
                        sticky_live_event.text = it
                    }
                })
        /*LiveBus.getInstance().subscribeStickySingleLiveEvent("StickySingleLiveEvent", String::class.java)
                .observe(this, Observer {
                    it?.let {
                        sticky_single_live_event.text = it
                    }
                })*/

        LiveBus.getInstance().subscribeLiveEvent("LiveEventBG", String::class.java)
                .observe(this, Observer { it ->
                    it?.let {
                        bg_live_event.text = it
                    }
                })
        LiveBus.getInstance().subscribeSingleLiveEvent("SingleLiveEventBG", String::class.java)
                .observe(this, Observer { it ->
                    it?.let {
                        bg_single_live_event.text = it
                    }
                })
        LiveBus.getInstance().subscribeStickyLiveEvent("StickyLiveEventBG", String::class.java)
                .observe(this, Observer { it ->
                    it?.let {
                        bg_sticky_live_event.text = it
                    }
                })
    }
}