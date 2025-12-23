package com.mahshad.recipeexploreapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Interests
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mahshad.home.navigation.HomeGraphRoute
import com.mahshad.interests.InterestsScreenRoute
import com.mahshad.setting.navigation.SettingNavigationGraphRoute
import com.mahshad.technewsexploreapp.R
import kotlin.reflect.KClass

@Composable
fun DrawerContent(
    currentRoute: KClass<*>,
    navigateToHome: () -> Unit,
    navigateToInterests: () -> Unit,
    navigateToSetting: () -> Unit,
    closeDrawer: () -> Unit
) {
    ModalDrawerSheet() {
        Logo(
            "Tech News",
            R.drawable.rocket_svgrepo_com,
            modifier = Modifier.padding(horizontal = 28.dp, vertical = 24.dp)
        )
        NavigationDrawerItem(
            label = { Text("Home") },
            icon = {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "Home",
                    modifier = Modifier.size(24.dp),
                )
            },
            selected = currentRoute == HomeGraphRoute,
            onClick = {
                navigateToHome()
                closeDrawer()
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Profile") },
            icon = {
                Icon(
                    Icons.Filled.Interests,
                    contentDescription = "Interests",
                    modifier = Modifier.size(24.dp),
                )
            },
            selected = currentRoute == InterestsScreenRoute,
            onClick = {
                navigateToInterests()
                closeDrawer()
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Setting") },
            icon = {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = "Setting",
                    modifier = Modifier.size(24.dp),
                )
            },
            selected = currentRoute == SettingNavigationGraphRoute,
            onClick = {
                navigateToSetting()
                closeDrawer()
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }
}