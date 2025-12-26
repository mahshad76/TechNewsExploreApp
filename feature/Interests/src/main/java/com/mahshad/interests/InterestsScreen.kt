package com.mahshad.interests

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mahshad.ui.components.ModifiedToggleButton
import com.mahshad.ui.components.TneLoadingWheel
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
    val context = LocalContext.current
    val interestingTopicsState = viewModel.interestingTopicStateFlow.collectAsStateWithLifecycle()
    when (interestingTopicsState.value) {
        is UiState.Loading -> TneLoadingWheel()
        is UiState.Error -> Toast.makeText(
            context, "Something went wrong",
            Toast.LENGTH_SHORT
        )
            .show()

        is UiState.Success -> {
            val favoriteTopics = (interestingTopicsState.value as UiState.Success).favoriteTopics
            LazyColumn(modifier = Modifier) {
                items(items.size) {
                    InterestCard(
                        items[it].first,
                        items[it].second,
                        items[it].second !in favoriteTopics,
                        { topic: String -> viewModel.update(topic) }
                    )
                }
            }
        }
    }
}

@Composable
fun InterestCard(
    imageId: Int,
    title: String,
    checked: Boolean,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
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
                    .background(Color.Transparent)
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
                    checked = checked,
                    onClick = { onClick(title) },
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