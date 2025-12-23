package com.mahshad.recipeexploreapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.mahshad.ui.icons.TneIcons.Home
import com.mahshad.ui.icons.TneIcons.Interests
import com.mahshad.ui.icons.TneIcons.Rocket
import com.mahshad.ui.icons.TneIcons.Settings
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
            Rocket,
            modifier = Modifier.padding(horizontal = 28.dp, vertical = 24.dp)
        )
        NavigationDrawerItem(
            label = { Text("Home") },
            icon = {
                Icon(
                    Home,
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
                    Interests,
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
                    Settings,
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