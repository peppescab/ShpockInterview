package it.to.peppsca.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import it.to.peppsca.R

/**
 * [setPicassoSrc] set a source for an imageView with Picasso
 */
@BindingAdapter("android:picassoSrc")
fun setPicassoSrc(view: ImageView, url: String?) {
    url?.let {
        Picasso.get().load(it).placeholder(R.drawable.ship_placeholder).error(R.drawable.default_ship).into(view)
    }
}
