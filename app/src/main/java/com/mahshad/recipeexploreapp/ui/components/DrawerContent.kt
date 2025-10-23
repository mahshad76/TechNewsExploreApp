package com.mahshad.recipeexploreapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent() {
    ModalDrawerSheet() {
        NavigationDrawerItem(
            label = { Text("Profile") },
            icon = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Profile",
                    modifier = Modifier.size(24.dp),
                )
            },
            selected = false,
            onClick = {})
        NavigationDrawerItem(
            label = { Text("Setting") },
            icon = {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = "Setting",
                    modifier = Modifier.size(24.dp),
                )
            },
            selected = false,
            onClick = {})
    }
}