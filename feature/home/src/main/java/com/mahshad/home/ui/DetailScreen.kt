
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mahshad.model.Article
import com.mahshad.ui.R
import kotlinx.serialization.Serializable

@Serializable
data class DetailScreenRoute(val article: Article)

@Composable
fun DetailScreen(article: Article) {
    val context = LocalContext.current
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
        Text(
            text = article.url, color = Color.Blue, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.55.dp)
                .clickable(true) {
                    openInCustomTab(context, article.url)
                })
    }
}

fun openExternalLink(context: Context, articleUrl: String) {
    //val encodedUrl = Uri.encode(articleUrl)
    val deepLinkUrl = articleUrl

    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(deepLinkUrl)
    )

    context.startActivity(intent)
}

fun openInCustomTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    builder.setShowTitle(true)
    //builder.setToolbarColor(ContextCompat.getColor(context, R.color))
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}

