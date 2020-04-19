package fr.bar.app.ui.utils

import android.util.TypedValue
import android.view.View
import kotlin.math.roundToInt

/**
 * Extensions methods
 * Top level functions : Allow access from everywhere without class name prefix
 */
fun View.dp(number: Number): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        number.toFloat(),
        this.resources.displayMetrics
    ).roundToInt()
}