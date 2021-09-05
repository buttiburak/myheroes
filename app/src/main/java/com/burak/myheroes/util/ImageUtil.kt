package com.burak.myheroes.util

import android.net.Uri
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder


/**
 * Created by mburak on 5.09.2021.
 */
object ImageUtil {
    private fun parseUri(url: String): Uri {
        //Change http to https for fix okhttp error
        val newUrl = url.replaceFirst("http", "https")
        return if (newUrl.isEmpty()) {
            Uri.parse("")
        } else {
            Uri.parse(newUrl)
        }
    }

    fun setImageURL(draweeView: SimpleDraweeView, url: String) {
        val request = ImageRequestBuilder.newBuilderWithSource(parseUri(url))
            .build()

        draweeView.controller = Fresco.newDraweeControllerBuilder()
            .setOldController(draweeView.controller)
            .setAutoPlayAnimations(true)
            .setImageRequest(request)
            .build()
    }
}