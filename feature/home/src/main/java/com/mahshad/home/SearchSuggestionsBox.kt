package com.mahshad.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mahshad.model.Article
import com.mahshad.ui.icons.TneIcons.Bookmark
import com.mahshad.ui.icons.TneIcons.BookmarkFilled
import com.mahshad.ui.icons.TneIcons.ClosedChavron
import com.mahshad.ui.icons.TneIcons.OpenedChavron

@Composable
fun SearchSuggestionsBox(
    suggestions: List<Article>,
    onSuggestionClick: (String) -> Unit,
    onIconClicked: (Article) -> Unit
) {
    val listState = rememberLazyListState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        if (suggestions.isNotEmpty()) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 11.73.dp)
            ) {
                items(suggestions.size) { index ->
                    val article =
                        ArticleCard(
                            article = suggestions[index],
                            onClick = {},
                            onIconClicked = onIconClicked
                        )
                }
            }
        }
    }
}

@Composable
fun ArticleCard(
    article: Article,
    onClick: () -> Unit,
    onIconClicked: (Article) -> Unit
) {
    var chavronState by rememberSaveable { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 11.73.dp)
            .clickable(onClick = onClick)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = article.urlToImage,
                contentDescription = "news image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(136.dp)
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStart = 2.67.dp,
                            topEnd = 2.67.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp
                        )
                    )
            )
            Row(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = article.title.split(" ")
                        .take(10)
                        .joinToString(" "),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(3.0F)
                )
                Box(
                    modifier = Modifier
                        .weight(1.0F)
                        .fillMaxHeight()
                        .padding(end = 7.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    val icon = if (article.isLiked) painterResource(BookmarkFilled) else
                        painterResource(Bookmark)
                    Icon(
                        icon,
                        contentDescription = "",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable(true) { onIconClicked(article) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxSize()) {
                val text = if (chavronState) article.description
                else article.description.split(" ")
                    .take(7)
                    .joinToString(" ") + "..."
                Text(
                    text, modifier = Modifier
                        .align(Alignment.Top)
                        .weight(2.0F)
                )
                Box(
                    modifier = Modifier
                        .weight(1.0F)
                        .padding(end = 20.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(
                        painter = if (chavronState) painterResource(ClosedChavron)
                        else painterResource(OpenedChavron),
                        contentDescription = "",
                        modifier = Modifier
                            .size(17.dp)
                            .clickable(true) { chavronState = !chavronState }
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun ArticleCardPreview() {
//    ArticleCard("news", "url", {})
//}