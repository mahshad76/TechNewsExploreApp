package com.mahshad.recipeexploreapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mahshad.technewsexploreapp.R

@Composable
fun Logo(title: String, @DrawableRes icon: Int, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "title",
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(title, modifier = Modifier)
    }
}

@Preview
@Composable
fun Preview(
    title: String = "hello",
    @DrawableRes icon: Int = R.drawable.meal_application_tracking_organic_food_analysis_healthcare_nutrition_svgrepo_com
) {
    Logo(title, icon, Modifier)
}