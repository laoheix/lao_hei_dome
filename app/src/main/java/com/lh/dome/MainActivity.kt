package com.lh.dome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.lh.dome.ui.theme.LaoHeiDomeTheme
import com.lh.dome.ui.widgets.TaiChi
import com.tw.ui.navigator.TWNavigation
import com.tw.ui.navigator.TWNavigationBar
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaoHeiDomeTheme {
                val navController = rememberNavController()
                val scope = rememberCoroutineScope()
                var selectRoute by remember {
                    mutableStateOf(TaiChi.TAI_CHI_ROUTE)
                }
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        TWNavigationBar(select = selectRoute) { route ->
                            selectRoute = route
                            navController.navigate(route) {
                                popUpTo(route) { saveState = true }
                                launchSingleTop = true
                            }
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    }
                ) {
                    TWNavigation(navController, select = selectRoute)
                }
            }
        }
    }
}
