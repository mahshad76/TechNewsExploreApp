package com.mahshad.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.mahshad.ui.components.SwipeableTabScreen
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreenRoute

@Composable
fun HomeScreen(
    navigateFromHomeToDetail: (String) -> Unit,
    homeScreenViewModel: HomeScreenViewModel
) {

//    val newsFeed = homeScreenViewModel.feedState.collectAsStateWithLifecycle()
//    val searchQuery = homeScreenViewModel._searchQueryStateFlow.collectAsStateWithLifecycle()
//    val searchSuggestion = homeScreenViewModel.searchSuggestions.collectAsStateWithLifecycle()
//
//    Column(modifier = Modifier.padding(10.dp)) {
//        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
//        HomeSearchBar(
//            modifier = Modifier.padding(bottom = 4.dp),
//            searchQuery.value,
//            { homeScreenViewModel.updateSearchQueryFlow(it) },
//            { navigateFromHomeToDetail(searchQuery.value) })
//        if (!searchQuery.value.isEmpty()) {
//            SearchSuggestionsBox(searchSuggestion.value.map { it.title }) {
//                homeScreenViewModel.updateSearchQueryFlow(
//                    it
//                )
//            }
//        }
//        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
//    }
    SwipeableTabScreen(
        listOf(
            "News" to painterResource(com.mahshad.ui.R.drawable.news_paper_svgrepo_com),
            "Favorite" to painterResource(com.mahshad.ui.R.drawable.favorite_svgrepo_com)
        ), {})
}

//@Composable
//@Preview
//fun Previeww() {
//    HomeScreen({})
//}