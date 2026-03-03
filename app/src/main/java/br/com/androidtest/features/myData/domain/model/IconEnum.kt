package br.com.androidtest.features.myData.domain.model

import br.com.androidtest.R

enum class IconEnum(val key: String, val drawableRes: Int) {
    DOCUMENT("ic_myplan", R.drawable.ic_document),
    DOWNLOAD("ic_document", R.drawable.ic_download),
    MESSAGE("ic_message", R.drawable.ic_message),
    BLOCK("ic_block", R.drawable.ic_block);

    companion object {
        fun from(key: String?): IconEnum {
            return entries.firstOrNull { it.key == key } ?: DOCUMENT
        }
    }
}