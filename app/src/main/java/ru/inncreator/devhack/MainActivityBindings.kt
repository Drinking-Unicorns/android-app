package ru.inncreator.devhack

import android.view.View
import androidx.databinding.BindingConversion

object MainActivityBindings {

    @BindingConversion
    @JvmStatic
    fun booleanToVisibility(visible: Boolean?): Int =
        if (visible == true) View.VISIBLE else View.GONE
}