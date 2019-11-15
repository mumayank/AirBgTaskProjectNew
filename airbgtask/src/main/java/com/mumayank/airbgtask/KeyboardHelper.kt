package com.mumayank.airbgtask

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by Administrator on 02-01-2018.
 */

object KeyboardHelper {
    fun hideKeyboard(activity: Activity?) {
        if (activity == null) {
            return
        }

        val views = activity.currentFocus
        val immAny = activity.getSystemService(Context.INPUT_METHOD_SERVICE) ?: return
        val imm = immAny as InputMethodManager
        if (views != null) {
            imm.hideSoftInputFromWindow(views.windowToken, 0)
        }
    }

    fun hideKeyboard(context: Context?, view: View?) {
        if (context == null || view == null) {
            return
        }

        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
