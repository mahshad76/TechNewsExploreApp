package com.mahshad.home.navigation

import DetailScreen
import DetailScreenRoute
import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.mahshad.home.ui.ExternalArticleScreen
import com.mahshad.home.ui.ExternalArticleScreenRoute
import com.mahshad.home.ui.HomeScreen
import com.mahshad.home.ui.HomeScreenRoute
import com.mahshad.model.Article
import com.mahshad.model.Article.Companion.ArticleNavType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data object HomeGraphRoute

fun NavGraphBuilder.homeNavigationGraph(navController: NavController) {
    navigation<HomeGraphRoute>(startDestination = HomeScreenRoute::class) {
        composable<HomeScreenRoute> {
            HomeScreen({ article: Article ->
                navController.navigateFromHomeToDetail(article)
            }) { navController.getBackStackEntry(HomeScreenRoute) }
        }
        composable<DetailScreenRoute>(
            typeMap = mapOf(typeOf<Article>() to ArticleNavType)
        )
        { backStackEntry ->
            val detailScreenRoute = backStackEntry.toRoute<DetailScreenRoute>()
            DetailScreen(detailScreenRoute.article)
        }
        composable<ExternalArticleScreenRoute>(
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://mahshad.app/open?url={url}"
                }
            )
        ) { backStackEntry ->

            val route = backStackEntry.toRoute<ExternalArticleScreenRoute>()
            val decodedUrl = Uri.decode(route.url)

            ExternalArticleScreen(url = decodedUrl)
        }
    }
}

//@Composable
//inline fun <reified VM : ViewModel> sharedHiltViewModel(
//    crossinline parentEntryProvider: () -> NavBackStackEntry
//): VM {
//    val parentEntry = remember { parentEntryProvider() }
//    return hiltViewModel(parentEntry)
//}