package com.zzapp.meizhi.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.webkit.*
import android.widget.Toast
import com.zzapp.meizhi.R
import kotlinx.android.synthetic.main.web.*

/**
 * Project MeiZhi
 * Date 2017-11-06
 *
 * @author zzzz
 */

class WebActivity : AppCompatActivity() {

    private val COPYED = "已复制链接到剪切板"
    private val URL = "url"

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_web,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.copy -> {
                val cb = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val cd = ClipData.newPlainText(URL,intent.getStringExtra(URL))
                cb.primaryClip = cd
                Toast.makeText(applicationContext,COPYED,Toast.LENGTH_SHORT).show()
            }
            R.id.open -> {
                val intent = Intent("android.intent.action.VIEW")
                intent.data = Uri.parse(web.url)
                startActivity(intent)
            }
            else -> return false
        }

        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = ContextCompat.getColor(this,R.color.colorPrimaryDark)
        }

        initView()
    }

    private fun initView(){
        setSupportActionBar(webToolbar)
        webToolbar.setNavigationOnClickListener {
            finish()
        }

        val webSettings = web.settings

        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true

        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false

        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webSettings.allowFileAccess = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.loadsImagesAutomatically = true
        webSettings.defaultTextEncodingName = "utf-8"

        web.loadUrl(intent.getStringExtra(URL))
        web.webViewClient = object: WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view!!.loadUrl(url)
                return true
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view!!.loadUrl(request!!.url.toString())
                return true
            }
        }
        web.webChromeClient = object: WebChromeClient(){
            override fun onReceivedTitle(view: WebView?, titles: String?) {
                webToolbar.title = titles
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if(newProgress == 100){
                    progress.visibility = View.GONE
                } else {
                    if(progress.visibility == View.GONE){
                        progress.visibility = View.VISIBLE
                    }
                    progress.progress = newProgress
                }
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && web.canGoBack()) {
            web.goBack()
            return true
        }

        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        web.destroy()
    }
}