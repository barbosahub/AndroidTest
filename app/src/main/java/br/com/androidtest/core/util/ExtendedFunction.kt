package br.com.androidtest.core.util

import android.R.attr.action
import android.content.ClipData
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import br.com.androidtest.features.myData.domain.model.IconEnum
import java.io.File
import java.io.FileOutputStream

fun <T> selectUserOrProfile(
    userValue: T?,
    profileValue: T?
): T? = userValue ?: profileValue


@Composable
fun String?.toUiIcon(): Painter {
    val iconEnum = IconEnum.from(this)
    return painterResource(id = iconEnum.drawableRes)
}

fun Context.sharePdfFromAssets(assetFileName: String = "terms.pdf") {

    val file = File(cacheDir, assetFileName)

    if (!file.exists()) {
        assets.open(assetFileName).use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }
    }

    val uri = FileProvider.getUriForFile(
        this,
        "${packageName}.provider",
        file
    )

    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "application/pdf"
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        clipData = ClipData.newUri(contentResolver, "PDF", uri)
    }

    startActivity(Intent.createChooser(shareIntent, "Compartilhar contrato"))
}