package com.example.rakesh.gallery.`interface`

import android.view.View

/**
 * Created by Rakesh on 3/29/2018.
 */
interface ClickListner {
        fun onClick(v: View, position: Int)
        fun onLongClick(v: View, position: Int)
}