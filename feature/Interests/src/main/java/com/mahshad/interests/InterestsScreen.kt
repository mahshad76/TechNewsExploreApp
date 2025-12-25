package com.mahshad.interests

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.mahshad.ui.components.ModifiedToggleButton
import com.mahshad.ui.icons.TneIcons.Add
import com.mahshad.ui.icons.TneIcons.Apple
import com.mahshad.ui.icons.TneIcons.CNN
import com.mahshad.ui.icons.TneIcons.Check
import com.mahshad.ui.icons.TneIcons.TechCrunch
import com.mahshad.ui.icons.TneIcons.Tesla
import com.mahshad.ui.icons.TneIcons.WSJ
import kotlinx.serialization.Serializable

@Serializable
data object InterestsScreenRoute

@Composable
fun InterestsScreen(viewModel: InterestsScreenViewModel = hiltViewModel()) {
    LazyColumn(modifier = Modifier) {
        items(items.size) {
            InterestCard(items[it].first, items[it].second)
        }
    }
}

@Composable
fun InterestCard(imageId: Int, title: String, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(7.dp)
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
            Box(
                modifier = modifier
                    .background(Color.White)
                    .weight(1.0F)
                    .padding(end = 3.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                ModifiedToggleButton(
                    icon = {
                        Icon(
                            Add,
                            contentDescription = "add",
                            tint = Color.Black,
                            modifier = Modifier
                                .background(Color.White)
                                .padding(4.dp)
                        )
                    },
                    checkedIcon = {
                        Icon(
                            Check,
                            contentDescription = "check",
                            tint = Color.Black,
                            modifier = Modifier
                                .background(Color.White)
                                .padding(4.dp)
                        )
                    },
                    checked = true,
                    onClick = {},
                    modifier = modifier
                )
            }
        }
    }
}

val items = listOf<Pair<Int, String>>(
    Apple to "Apple articles",
    CNN to "US headlines",
    TechCrunch to "Tech crunch",
    WSJ to "Wall Street Journal",
    Tesla to "Tesla articles"
)

@Composable
@Preview
fun Preview() {
    InterestsScreen()
}