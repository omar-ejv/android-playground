package com.omaresli.gallery.views

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.util.*

class LoadingImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ImageView(context, attrs, defStyleAttr) {
    companion object {
        const val DEFAULT_ALPHA = 200
    }

    var imageUrl: String = ""
        set(value) {
            field = value
            asyncLoadPicture(value)
        }

    private fun asyncLoadPicture(imageUrl: String) {
        if (imageUrl.isNotEmpty()) {
            background = ColorDrawable(generateRandomColour())
            Glide.with(this.context)
                    .load(imageUrl)
                    .into(this)
        }
    }

    private fun generateRandomColour(): Int {
        return Random().let {
            Color.argb(DEFAULT_ALPHA,
                    it.nextInt(256),
                    it.nextInt(256),
                    it.nextInt(256))
        }
    }
}