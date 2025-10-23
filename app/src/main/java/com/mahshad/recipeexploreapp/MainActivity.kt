package com.mahshad.recipeexploreapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mahshad.recipeexploreapp.ui.RecipeExploreApp
import com.mahshad.recipeexploreapp.ui.theme.RecipeExploreAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeExploreAppTheme {
                RecipeExploreApp()
            }
        }
    }
}