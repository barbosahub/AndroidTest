package br.com.androidtest.core.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import br.com.androidtest.R
import br.com.androidtest.core.design_system.theme.AppTheme
import br.com.androidtest.core.design_system.theme.Dimensions


@Preview
@Composable
fun MenuItemPreview() {
    AppTheme {
        MenuItem(
            labelText = "Label",
            leadingIcon = painterResource(R.drawable.ic_document),
            trailingIcon = Icons.Filled.ChevronRight
        ) {}
    }
}

@Composable
fun MenuItem(
    labelText: String? = null,
    leadingIcon: Painter? = null,
    trailingIcon: ImageVector? = null,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val backgroundColorByState = when {
        isPressed -> R.color.bgPrimaryPressed
        isHovered -> R.color.bgPrimary
        else -> R.color.bgPrimary
    }

    val labelColorByState = when {
        isPressed -> R.color.textPrimary
        isHovered -> R.color.textPrimary
        else -> R.color.textPrimary
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    onClick()
                }
            )
            .background(
                color = colorResource(backgroundColorByState)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimensions.itemHeight.m)
                .padding(horizontal = Dimensions.spacing.space8dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (leadingIcon != null) {
                Icon(
                    painter = leadingIcon,
                    contentDescription = labelText,
                    tint = Color.Unspecified
                )
            }

            labelText?.let {
                Box(modifier = Modifier.weight(1f)) {
                    MenuItemOneLineContent(
                        label = it,
                        labelColor = colorResource(labelColorByState)
                    )
                }
            }

            if (trailingIcon != null) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = labelText,
                    tint = Color.Black
                )
            }
        }

        HorizontalDivider(
            color = colorResource(R.color.divider),
            borderWidth = Dimensions.borderWidth.s
        )
    }
}

@Composable
private fun MenuItemOneLineContent(
    label: String,
    labelColor: Color,
    paddingHorizontal: Dp? = null
) {
    Row() {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = paddingHorizontal ?: Dimensions.spacing.space16dp),
            text = label,
            color = labelColor,
            maxLines = 1,
        )
    }

}