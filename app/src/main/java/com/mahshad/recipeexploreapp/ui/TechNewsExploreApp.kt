package com.mahshad.recipeexploreapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mahshad.home.navigation.HomeGraphRoute
import com.mahshad.home.navigation.homeNavigationGraph
import com.mahshad.interests.navigation.interestsNavigationGraph
import com.mahshad.recipeexploreapp.navigation.TechExploreNavigationActions
import com.mahshad.recipeexploreapp.ui.components.DrawerContent
import com.mahshad.ui.icons.TneIcons.Menu
import com.mahshad.welcome.navigation.WelcomeGraphRoute
import com.mahshad.welcome.navigation.welcomeNavigationGraph
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechNewsExploreApp() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = WelcomeGraphRoute::class
    val currentGraphRouteString = navBackStackEntry?.destination?.parent?.route
    val hideAppShell = currentGraphRouteString == WelcomeGraphRoute::class.qualifiedName
    val navigationActions = remember(navController) {
        TechExploreNavigationActions(navController)
    }
    val closeDrawer = {
        coroutineScope.launch {
            if (drawerState.isClosed) drawerState.open() else drawerState.close()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                currentRoute,
                navigateToInterests = { navigationActions.navigateToInterests() },
                navigateToSetting = { navigationActions.navigateToSetting() },
                navigateToHome = { navigationActions.navigateToHome() },
                closeDrawer = { closeDrawer() },
            )
        }
    ) {
        Scaffold(topBar = {
            if (!hideAppShell) {
                TopAppBar(
                    title = { Text("") },
                    modifier = Modifier.alpha(if (hideAppShell) 0f else 1f),
                    navigationIcon = {
                        IconButton(onClick = { closeDrawer() }) {
                            Icon(Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        }) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = currentRoute,
                modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
            ) {
                welcomeNavigationGraph(
                    navController,
                    { navController.navigate(HomeGraphRoute) })
                homeNavigationGraph(navController)
                interestsNavigationGraph(navController)
            }
        }
    }
}
