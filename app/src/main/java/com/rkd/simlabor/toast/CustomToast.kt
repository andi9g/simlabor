package com.rkd.simlabor.toast

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.rkd.simlabor.R
fun showCustomToast(context: Context, message: String, iconRes: Int) {
    val inflater = LayoutInflater.from(context)
    val layout = inflater.inflate(R.layout.custom_toast, null)

    // Set text dan icon pada custom toast
    val textView = layout.findViewById<TextView>(R.id.toast_text)
    val imageView = layout.findViewById<ImageView>(R.id.toast_icon)
    textView.text = message
    imageView.setImageResource(iconRes)

    // Buat Toast
    val toast = Toast(context)
    toast.duration = Toast.LENGTH_LONG
    toast.view = layout
    toast.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 100)
    toast.show()
}