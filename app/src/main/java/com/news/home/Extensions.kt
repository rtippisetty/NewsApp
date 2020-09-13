package com.greenley.technical

import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Snack bar to display error messages
 *
 * @param message
 * @param length
 * @param f
 */
inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_INDEFINITE, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.show()
}

fun Snackbar.action(action: String, listener: (View) -> Unit) {
    setAction(action, listener)
}

/**
 * Extension function to convert string to date
 *
 * @param dateFormat
 * @param timeZone
 * @return
 */
fun String.toDate(dateFormat: String = "yyyy-MM-dd", timeZone: TimeZone = TimeZone
    .getTimeZone("UTC")): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date.millisPastFromCurrentTime(): Long {
    return TimeUnit.MILLISECONDS.toMillis((Date().time - this.time))
}

operator fun TextView.plusAssign(text: CharSequence) = append(text)