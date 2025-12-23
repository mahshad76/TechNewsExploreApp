package com.mahshad.interests

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mahshad.ui.icons.TneIcons.Apple
import com.mahshad.ui.icons.TneIcons.CNN
import com.mahshad.ui.icons.TneIcons.TechCrunch
import com.mahshad.ui.icons.TneIcons.Tesla
import com.mahshad.ui.icons.TneIcons.WSJ
import kotlinx.serialization.Serializable

val items = listOf<Pair<Int, String>>(
    Apple to "Apple articles",
    CNN to "US headlines",
    TechCrunch to "Tech crunch",
    WSJ to "Wall Street Journal",
    Tesla to "Tesla articles"
)

@Serializable
data object InterestsScreenRoute

@Composable
fun InterestsScreen() {
    LazyColumn(modifier = Modifier) {
        items(items.size) {
            InterestCard(items[it].first, items[it].second)
        }
    }
}

@Composable
fun InterestCard(imageId: Int, title: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painterResource(imageId),
                contentDescription = "title",
                modifier = modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(title)
        }
    }
}

@Composable
@Preview
fun Preview() {
    InterestsScreen()
}