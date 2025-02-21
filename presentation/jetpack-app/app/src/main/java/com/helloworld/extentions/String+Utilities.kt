package com.helloworld.extentions

import android.R.attr.mimeType
import android.webkit.MimeTypeMap


fun mime2FileType(mime: String?): String {
    if (mime == null) {
        return "jpeg"
    }
    return when (mime) {
        "image/jpeg" -> return "jpg"
        "image/png" -> return "png"
        "image/gif" -> return "gif"
        else -> MimeTypeMap.getSingleton().getExtensionFromMimeType(mime)!!
    }
}