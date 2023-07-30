package com.lh.dome.ui.widgets

import android.content.Context
import android.text.TextUtils
import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

class MarqueeText(context: Context) : TextView(context) {

    init {
        // 初始化以下textview已提供的跑马灯属性
        ellipsize = TextUtils.TruncateAt.MARQUEE
        isSingleLine = true
        // 永久循环
        marqueeRepeatLimit = -1
        isSelected = true
    }

    // 重写isFocused方法，让textview失踪获取焦点
    override fun isFocused(): Boolean = true
}

class MarqueeView() {
    companion object {
        const val MARQUEE_ROUTE = "marquee_route"
    }

    @Composable
    fun Marquee(str: String) {
        AndroidView(
            factory = { context ->
                MarqueeText(context).apply {
                    text = str
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}