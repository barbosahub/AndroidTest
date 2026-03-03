package br.com.androidtest.features.myPlan.presentation.components

import android.util.Log.i
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.androidtest.R
import br.com.androidtest.core.design_system.components.Shapes
import br.com.androidtest.core.design_system.theme.AppTheme
import br.com.androidtest.core.design_system.theme.Dimensions
import br.com.androidtest.features.myPlan.domain.model.ExtraPlayOption
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MyPlanAppsSectionPreview() {
    AppTheme {
        Box(modifier = Modifier.padding(all = Dimensions.spacing.space16dp)) {
            MyPlanAppsSection(listOf())
        }
    }
}

@Composable
fun MyPlanAppsSection(
    options: List<ExtraPlayOption>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = Shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.bgPrimary),
            contentColor = colorResource(R.color.bgPrimary)
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.dp),
        border = BorderStroke(
            width = Dimensions.borderWidth.s,
            color = colorResource(R.color.divider),
        ),
        content = {
            NewAppsSection(options)
        }
    )
}

@Composable
fun NewAppsSection(options: List<ExtraPlayOption>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimensions.spacing.space16dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        options.forEach {
            it.url?.let { url ->
                AsyncImage(
                    modifier = Modifier.size(Dimensions.socialsImageSize.default),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(url)
                        .crossfade(true)
                        .build(),
                    contentDescription = it.description,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

