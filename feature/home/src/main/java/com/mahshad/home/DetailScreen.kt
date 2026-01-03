import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mahshad.model.Article
import com.mahshad.ui.R
import kotlinx.serialization.Serializable

//package com.mahshad.home
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.ColorFilter
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.mahshad.model.Article
//import kotlinx.serialization.Serializable
//
@Serializable
data class DetailScreenRoute(val article: Article)

@Composable
fun DetailScreen(article: Article) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        AsyncImage(
            article.urlToImage,
            contentDescription = "new's image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(186.64.dp)
                .clip(RoundedCornerShape(17.dp))
        )
        Text(
            article.title,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )
        Row(
            modifier = Modifier
                .padding(top = 13.15.dp, start = 16.dp, end = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(R.drawable.ellipse_60),
                contentDescription = "author image",
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(4.27.dp))
            Column(modifier = Modifier.align(Alignment.Top)) {
                Text(article.author)
                Text(article.publishedAt)
            }
            Spacer(Modifier.weight(1.0F))
            Row(modifier = Modifier.align(Alignment.CenterVertically)) {
                Image(
                    painterResource(R.drawable.ant_design_field_time_outlined),
                    contentDescription = "time",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(2.22.dp))
                Text("5 min")
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.55.dp),
            thickness = 1.dp,
            color = Color.Gray
        )
        Text(
            article.content,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.55.dp),
        )
    }
}
//
//@Composable
//fun DetailScreen(subject: String, viewModel: HomeScreenViewModel) {
////    val matchedArticles = viewModel.searchSuggestions.collectAsStateWithLifecycle()
////    val articles = matchedArticles.value
////    LazyVerticalGrid(
////        columns = Adaptive(300.dp),
////        modifier = Modifier
////            .fillMaxSize()
////    ) {
////        items(articles.size) {
////            DetailCard(articles[it], { article: Article ->
////                viewModel.bookmarkClicked(article)
////            })
////        }
////    }
//}
//
//@Composable
//fun DetailCard(article: Article, onBookMarkClicked: (Article) -> Unit) {
//    Card(
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Spacer(modifier = Modifier.height(12.dp))
//            Row {
//                Text(
//                    article.title,
//                    modifier = Modifier.fillMaxWidth((.8f)),
//                )
//                Spacer(modifier = Modifier.weight(1f))
//                Image(
//                    modifier = Modifier
//                        .weight(0.2F)
//                        .fillMaxHeight(1.0F)
//                        .clickable(true, onClick = { onBookMarkClicked(article) }),
//                    painter = painterResource(id = R.drawable.bookmark_svgrepo_com),
//                    colorFilter = ColorFilter.tint(
//                        color = if (article.isLiked) Color.Red
//                        else Color.Black
//                    ),
//                    contentDescription = null,
//                )
//            }
//            Spacer(modifier = Modifier.height(12.dp))
//            Text(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight(0.8F),
//                text = article.content,
//                textAlign = TextAlign.Center,
//                style = MaterialTheme.typography.bodyMedium,
//            )
//            Spacer(modifier = Modifier.height(12.dp))
//        }
//    }
//}
//
//@Composable
//@Preview
//fun Previeww() {
//    DetailCard(
//        article = Article.DEFAULT, {}
//    )
//}