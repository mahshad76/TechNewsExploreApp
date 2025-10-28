package com.mahshad.recipeexploreapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mahshad.recipeexploreapp.navigation.TechNewsExploreDestinations
import com.mahshad.recipeexploreapp.navigation.TechNewsExploreDestinations.HOME_ROUTE
import com.mahshad.recipeexploreapp.navigation.TechNewsExploreDestinations.PROFILE_ROUTE
import com.mahshad.recipeexploreapp.navigation.TechNewsExploreDestinations.SETTING_ROUTE
import com.mahshad.recipeexploreapp.navigation.RecipeExploreNavigationActions
import com.mahshad.recipeexploreapp.ui.components.DrawerContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeExploreApp() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        navBackStackEntry?.destination?.route ?: TechNewsExploreDestinations.HOME_ROUTE
    val navigationActions = remember(navController) {
        RecipeExploreNavigationActions(navController)
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
                navigateToProfile = { navigationActions.navigateToProfile() },
                navigateToSetting = { navigationActions.navigateToSetting() },
                navigateToHome = { navigationActions.navigateToHome() },
                closeDrawer = { closeDrawer() },
            )
        }
    ) {
        Scaffold(topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = {
                        closeDrawer()
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                })
        }) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = currentRoute,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(PROFILE_ROUTE) {
                    Text("profile")
                }
                composable(HOME_ROUTE) {
                    Text("home")
                }
                composable(SETTING_ROUTE) {
                    Text("setting")
                }
            }
        }
    }
}
