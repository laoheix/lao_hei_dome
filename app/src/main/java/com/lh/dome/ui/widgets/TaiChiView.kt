package com.lh.dome.ui.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaiChiView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    private var mBlackPaint: Paint = Paint().apply {
        isAntiAlias = true
        strokeWidth = 2f
        color = Color.BLACK
        style = Paint.Style.FILL
    }
    private var mWhitePaint: Paint = Paint().apply {
        isAntiAlias = true
        strokeWidth = 2f
        color = Color.WHITE
        style = Paint.Style.FILL
    }
    private lateinit var mCanvas: Canvas
    private var mHolder: SurfaceHolder = holder.apply {
        addCallback(this@TaiChiView)
    }

    // 添加动画
    private var mDegrees = 0f
    private var isRunning = true

    fun stop() {
        isRunning = false
    }

    fun start() {
        isRunning = true
    }


    private fun draw() {
        mCanvas = mHolder.lockCanvas()

        mCanvas.drawColor(Color.LTGRAY)

        val rectLeft = width / 4f
        val rectTop = height / 2f - rectLeft
        val rectRight = width * 3 / 4f
        val rectBottom = height / 2f + rectLeft

        mCanvas.save()
        mCanvas.rotate(mDegrees, width / 2f, height / 2f)

        mCanvas.drawArc(RectF(rectLeft, rectTop, rectRight, rectBottom), 270f, -180f, true, mBlackPaint)
        mCanvas.drawArc(RectF(rectLeft, rectTop, rectRight, rectBottom), 270f, 180f, true, mWhitePaint)

        mCanvas.drawArc(
            RectF(width / 2f - rectLeft / 2f, rectTop, width / 2f + rectLeft / 2f, height / 2f),
            270f,
            -180f,
            true,
            mWhitePaint
        )
        mCanvas.drawArc(
            RectF(width / 2f - rectLeft / 2f, height / 2f, width / 2f + rectLeft / 2f, rectBottom),
            270f,
            180f,
            true,
            mBlackPaint
        )

        mCanvas.drawCircle(width / 2f, height / 2f - rectLeft / 2f, rectLeft / 4f, mBlackPaint)
        mCanvas.drawCircle(width / 2f, height / 2f + rectLeft / 2f, rectLeft / 4f, mWhitePaint)

        mCanvas.restore()
        mHolder.unlockCanvasAndPost(mCanvas)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        CoroutineScope(Dispatchers.Default).launch {
            while (isRunning) {
                draw()
                mDegrees++
            }
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        isRunning = false
    }

}

class TaiChi {
    companion object {
        const val TAI_CHI_ROUTE = "tai_chi_route"
    }

    @Composable
    fun TaiChiScreen(isStop: Boolean = false) {
        AndroidView(
            factory = { context ->
                TaiChiView(context)
            },
            modifier = Modifier.fillMaxSize(),
            update = {
                if (isStop) it.stop() else it.start()
            }
        )
    }
}