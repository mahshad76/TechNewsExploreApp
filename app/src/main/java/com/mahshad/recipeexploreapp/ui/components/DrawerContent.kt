package com.mahshad.recipeexploreapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mahshad.recipeexploreapp.navigation.TechNewsExploreDestinations
import com.mahshad.technewsexploreapp.R

@Composable
fun DrawerContent(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToSetting: () -> Unit,
    closeDrawer: () -> Unit
) {
    ModalDrawerSheet() {
        Logo(
            "RecipeExplore",
            R.drawable.meal_application_tracking_organic_food_analysis_healthcare_nutrition_svgrepo_com,
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
            selected = currentRoute == TechNewsExploreDestinations.HOME_ROUTE,
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
                    Icons.Filled.Person,
                    contentDescription = "Profile",
                    modifier = Modifier.size(24.dp),
                )
            },
            selected = currentRoute == TechNewsExploreDestinations.PROFILE_ROUTE,
            onClick = {
                navigateToProfile()
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
            selected = currentRoute == TechNewsExploreDestinations.SETTING_ROUTE,
            onClick = {
                navigateToSetting()
                closeDrawer()
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }
}