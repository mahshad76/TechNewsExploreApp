package com.mahshad.home.navigation

import DetailScreen
import DetailScreenRoute
import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.mahshad.home.HomeScreen
import com.mahshad.home.HomeScreenRoute
import com.mahshad.model.Article
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

@Serializable
data object HomeGraphRoute

fun NavGraphBuilder.homeNavigationGraph(navController: NavController) {
    val ArticleNavType = object : NavType<Article>(isNullableAllowed = false) {
        override fun get(bundle: Bundle, key: String): Article? {
            return bundle.getString(key)?.let { Json.decodeFromString(it) }
        }

        override fun parseValue(value: String): Article {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: Article): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: Article) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }

    navigation<HomeGraphRoute>(startDestination = HomeScreenRoute::class) {
        composable<HomeScreenRoute> {
            HomeScreen({ article: Article ->
                navController.navigateFromHomeToDetail(article)
            })
        }
        composable<DetailScreenRoute>(typeMap = mapOf(typeOf<Article>() to ArticleNavType))
        { backStackEntry ->
            val detailScreenRoute = backStackEntry.toRoute<DetailScreenRoute>()
            DetailScreen(detailScreenRoute.article)
        }
    }
}