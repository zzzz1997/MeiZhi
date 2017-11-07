package com.zzapp.meizhi.ui

import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.zzapp.meizhi.R
import kotlinx.android.synthetic.main.about.*

/**
 * Project MeiZhi
 * Date 2017-11-04
 *
 * @author zzzz
 */
class AboutActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = ContextCompat.getColor(this,R.color.colorPrimaryDark)
        }

        aboutToolbar.setNavigationOnClickListener {
            finish()
        }
    }
}