package br.com.androidtest.core.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.androidtest.R
import br.com.androidtest.core.design_system.theme.AppTheme
import br.com.androidtest.core.design_system.theme.Dimensions


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ModalCardContentLogoutPreview() {
    AppTheme {
        Box(modifier = Modifier.padding(all = Dimensions.spacing.space16dp)) {
            ModalContent(
                primaryText = stringResource(R.string.logout_question),
                secondaryText = stringResource(R.string.do_you_really_want_to_log_out),
                onConfirm = {},
                onCancel = {}
            )
        }
    }
}


@Composable
fun ModalContent(
    primaryText: String,
    secondaryText: String? = null,
    content: (@Composable () -> Unit)? = null,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    Box(
        modifier =
            Modifier
                .width(Dimensions.componentWidthSize.modalDefault)
                .wrapContentHeight()
                .background(
                    colorResource(R.color.bgPrimary),
                    shape = Shapes.large
                )
                .padding(horizontal = Dimensions.spacing.space16dp)
    ) {
        Column(
            modifier = Modifier.padding(bottom = Dimensions.spacing.space16dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = Dimensions.componentHeightSize.modalHeader),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = primaryText,
                    fontSize = Dimensions.fontSize.heading,
                    color = colorResource(R.color.textPrimary),
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                secondaryText?.let {
                    Text(
                        text = secondaryText,
                        fontSize = Dimensions.fontSize.title,
                        color = colorResource(R.color.textPrimary),
                    )
                }

                if (content != null) {
                    Box(
                        modifier = Modifier.padding(vertical = Dimensions.spacing.space8dp)
                    ) {
                        content()
                    }
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimensions.spacing.space16dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimensions.spacing.space8dp)
            ) {
                ButtonPrimary(
                    modifier = Modifier.fillMaxWidth(),
                    labelText = stringResource(R.string.yes),
                    onClick = { onConfirm.invoke() },
                    size = ButtonSpecsSize.M
                )

                ButtonSecondary(
                    modifier = Modifier.fillMaxWidth(),
                    labelText = stringResource(R.string.no),
                    onClick = { onCancel.invoke() },
                    size = ButtonSpecsSize.M
                )
            }
        }
    }
}

