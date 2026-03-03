package br.com.androidtest.features.myPlan.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import br.com.androidtest.R
import br.com.androidtest.core.design_system.components.HorizontalDivider
import br.com.androidtest.core.design_system.theme.AppTheme
import br.com.androidtest.core.design_system.theme.Dimensions
import br.com.androidtest.core.design_system.theme.Dimensions.fontSize
import br.com.androidtest.features.myPlan.domain.model.AlignmentEnum
import br.com.androidtest.features.myPlan.domain.model.Content
import br.com.androidtest.features.myPlan.domain.model.ExtraPlay
import br.com.androidtest.features.myPlan.domain.model.HeaderItem
import br.com.androidtest.features.myPlan.domain.model.HeaderValue

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MyPlanSectionPreview() {
    AppTheme {
        Box(modifier = Modifier.padding(all = Dimensions.spacing.space16dp)) {
            MyPlanSection()
        }
    }
}

@Composable
fun MyPlanSection(
    name: String? = null,
    offer: String? = null,
    planValue: String? = null,
    header: List<List<HeaderItem>>? = null,
    content: Content? = null,
    extraPlay: ExtraPlay? = null,
) {
    Column {
        Plan(
            name = name,
            offer = offer,
            planValue = planValue
        )

        if (header != null) {
            NewPlatformHeader(header)
        } else {
            OldPlatformHeader(content)
        }

        ExtraPlay(extraPlay)
    }
}


@Composable
private fun Plan(
    name: String? = null,
    offer: String? = null,
    planValue: String? = null
) {
    Column(
        modifier = Modifier
            .background(colorResource(R.color.bgPrimary))
            .padding(
                top = Dimensions.spacing.space16dp,
                start = Dimensions.spacing.space16dp,
                end = Dimensions.spacing.space16dp
            ),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(
                text = name ?: stringResource(R.string.my_plan),
                fontSize = Dimensions.fontSize.heading,
                color = colorResource(R.color.textPrimary),
                fontWeight = FontWeight.Bold
            )

            offer?.let {
                Text(
                    text = offer,
                    fontSize = Dimensions.fontSize.huge,
                    color = colorResource(R.color.bgBrandSolid),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End

        ) {
            planValue?.let {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = it,
                        fontSize = Dimensions.fontSize.title,
                        color = colorResource(R.color.textPrimary),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun OldPlatformHeader(content: Content?) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.bgPrimary))
            .padding(top = Dimensions.spacing.space16dp),
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacing.spaceNone),
    ) {
        Item(
            HeaderItem(
                title = stringResource(R.string.status),
                value = HeaderValue(
                    text = content?.status,
                    alignment = AlignmentEnum.LEFT.value
                )
            )
        )

        Item(
            HeaderItem(
                title = stringResource(R.string.my_number),
                value = HeaderValue(
                    text = content?.phoneNumber,
                    alignment = AlignmentEnum.LEFT.value
                )
            )
        )

        HorizontalDivider(
            color = colorResource(R.color.divider),
            borderWidth = Dimensions.borderWidth.s
        )

        content?.plan?.let {
            Item(
                HeaderItem(
                    title = stringResource(R.string.plan),
                    value = HeaderValue(
                        text = "${it}GB",
                        alignment = AlignmentEnum.RIGHT.value
                    )
                )
            )
        }

        content?.bonus?.let {
            Item(
                HeaderItem(
                    title = stringResource(R.string.bonus),
                    value = HeaderValue(
                        text = "${it}GB",
                        alignment = AlignmentEnum.RIGHT.value
                    )
                )
            )
        }

        HorizontalDivider(
            color = colorResource(R.color.divider),
            borderWidth = Dimensions.borderWidth.s
        )
    }
}


@Composable
private fun NewPlatformHeader(header: List<List<HeaderItem>>?) {
    if (header.isNullOrEmpty()) return

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.bgPrimary))
            .padding(top = Dimensions.spacing.space16dp),
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacing.spaceNone),
    ) {
        header.forEach { items ->
            items.forEach { item ->
                Item(item)
            }

            HorizontalDivider(
                color = colorResource(R.color.divider),
                borderWidth = Dimensions.borderWidth.s
            )
        }
    }
}

@Composable
private fun Item(item: HeaderItem) {
    val alignment = item.value?.alignment
    val arrangement = if (alignment == AlignmentEnum.RIGHT.value) {
        Arrangement.SpaceBetween
    } else {
        Arrangement.Start
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimensions.spacing.space16dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = arrangement
    ) {

        item.title?.let { title ->
            Text(
                text = title,
                fontSize = Dimensions.fontSize.body,
                color = colorResource(R.color.textPrimary),
                fontWeight = FontWeight.Normal
            )
        }

        item.value?.text?.let { valueText ->
            Text(
                text = valueText,
                fontSize = Dimensions.fontSize.body,
                color = colorResource(R.color.textPrimary),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun ExtraPlay(
    extraPlay: ExtraPlay? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.bgPrimary))
            .padding(Dimensions.spacing.space16dp),
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacing.space8dp),
    ) {
        Text(
            text = extraPlay?.title ?: stringResource(R.string.included_apps),
            fontSize = fontSize.title,
            color = colorResource(R.color.textPrimary),
            fontWeight = FontWeight.Normal
        )

        extraPlay?.options?.let {
            MyPlanAppsSection(it)
        }
    }
}

