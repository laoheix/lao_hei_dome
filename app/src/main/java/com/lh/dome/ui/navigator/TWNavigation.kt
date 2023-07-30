package com.tw.ui.navigator


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lh.dome.ui.widgets.MarqueeView
import com.lh.dome.ui.widgets.TaiChi


@Composable
fun TWNavigation(navController: NavHostController, select: String = TaiChi.TAI_CHI_ROUTE) {
    NavHost(navController = navController, startDestination = TaiChi.TAI_CHI_ROUTE) {
        composable(TaiChi.TAI_CHI_ROUTE) {
            TaiChi().TaiChiScreen(select != TaiChi.TAI_CHI_ROUTE)
        }
        composable(MarqueeView.MARQUEE_ROUTE) {
            MarqueeView().Marquee("如果我是一只火鸟，那你便是那火苗，燃烧吧！火鸟！！！燃烧吧！火鸟！！！！！！！！！！！！！！！！！！！")
        }
    }
}


@Composable
fun TWNavigationBar(
    select: String = TaiChi.TAI_CHI_ROUTE,
    clickable: (label: String) -> Unit
) {
    NavigationRail(modifier = Modifier.fillMaxHeight().fillMaxWidth(0.8f)) {
        TextButton(
            onClick = { clickable(TaiChi.TAI_CHI_ROUTE) },
            modifier = Modifier.fillMaxWidth()
                .padding(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = if (select == TaiChi.TAI_CHI_ROUTE) Color.Green else Color.LightGray),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(text = "太极图")
        }
        TextButton(
            onClick = { clickable(MarqueeView.MARQUEE_ROUTE) },
            modifier = Modifier.fillMaxWidth()
                .padding(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = if (select == MarqueeView.MARQUEE_ROUTE) Color.Green else Color.LightGray),
            shape = RoundedCornerShape(20.dp)

        ) {
            Text(text = "文本跑马灯")
        }
    }
}