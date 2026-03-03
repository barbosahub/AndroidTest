package br.com.androidtest.features.myData.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import br.com.androidtest.R
import br.com.androidtest.core.design_system.theme.AppTheme
import br.com.androidtest.core.design_system.theme.Dimensions
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MyDataProfileSectionPreview() {
    AppTheme {
        Box(modifier = Modifier.padding(all = Dimensions.spacing.space16dp)) {
            MyDataProfileSection(
                profileUrl = "url",
                name = "Name",
                cpf = "000.XXX.XXX-00",
                age = "99"
            )
        }
    }
}


@Composable
fun MyDataProfileSection(
    title: String? = null,
    profileUrl: String? = null,
    name: String? = null,
    cpf: String? = null,
    age: String? = null
) {
    Column(
        modifier = Modifier
            .background(colorResource(R.color.bgPrimary))
            .padding(Dimensions.spacing.space16dp),
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacing.space16dp),
    ) {
        title?.let {
            Text(
                text = it,
                fontSize = Dimensions.fontSize.heading,
                color = colorResource(R.color.textPrimary),
                fontWeight = FontWeight.Bold
            )
        }

        profileUrl?.let { image ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    modifier = Modifier.size(Dimensions.avatarImageSize.default),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_avatar),
                    error = painterResource(R.drawable.ic_avatar),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }

        Column {
            name?.let {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Dimensions.spacing.space4dp)
                ) {
                    Text(
                        text = stringResource(R.string.name),
                        fontSize = Dimensions.fontSize.body,
                        color = colorResource(R.color.textPrimary),
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = it,
                        fontSize = Dimensions.fontSize.body,
                        color = colorResource(R.color.textPrimary),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            cpf?.let {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Dimensions.spacing.space4dp)
                ) {
                    Text(
                        text = stringResource(R.string.cpf),
                        fontSize = Dimensions.fontSize.body,
                        color = colorResource(R.color.textPrimary),
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = it,
                        fontSize = Dimensions.fontSize.body,
                        color = colorResource(R.color.textPrimary),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            age?.let {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Dimensions.spacing.space4dp)
                ) {
                    Text(
                        text = stringResource(R.string.age),
                        fontSize = Dimensions.fontSize.body,
                        color = colorResource(R.color.textPrimary),
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = it,
                        fontSize = Dimensions.fontSize.body,
                        color = colorResource(R.color.textPrimary),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}