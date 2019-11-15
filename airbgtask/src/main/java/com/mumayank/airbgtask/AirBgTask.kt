package com.mumayank.airbgtask

import android.app.Activity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.ref.WeakReference

class AirBgTask {

    /**
     * How to use
     *
     * Init as usual as an instance variable (no parameters required)
     *
     * In app onCreate(), call the instance variable's onCreate()
     *
     * In app onDestroy(), call the instance variable's onDestroy()
     *
     * Wherever convenient, define the tasks in the instance variable's define()
     *
     * Wherever you want, perform the tasks using the instance variable's execute()
     *
     * The only condition is to define() before execute()
     */

    companion object {
        const val TASK_SUCCESSFUL = true
        const val TASK_NOT_SUCCESSFUL = false
    }

    var activityWeakReference: WeakReference<Activity>? = null
    var doTaskInBackground:(()->Boolean)? = null
    var onSuccess:(()->Unit)? = null
    var onFailure:((reasonString: String)->Unit)? = null
    var onCompleted:(()->Unit)? = null

    fun define(
        doTaskInBackground:(()->Boolean)?,
        onSuccess:(()->Unit)? = null,
        onFailure:((reasonString: String)->Unit)? = null,
        onCompleted:(()->Unit)? = null
    ) {
        this.doTaskInBackground = doTaskInBackground
        this.onSuccess = onSuccess
        this.onFailure = onFailure
        this.onCompleted = onCompleted
    }

    fun onCreate(activity: Activity) {
        activityWeakReference = WeakReference(activity)

    }

    fun onDestroy() {
        activityWeakReference = null
    }

    fun execute() {
        if (activityWeakReference == null) {
            return
        }

        if (doTaskInBackground == null) {
            return
        }

        doAsync {
            try {
                val result = doTaskInBackground?.invoke()
                if (activityWeakReference == null || result == null) {
                    return@doAsync
                }
                uiThread {
                    if (result == TASK_SUCCESSFUL) {
                        onSuccess?.invoke()
                        onCompleted?.invoke()
                    } else {
                        onFailure?.invoke("AirBgTask error: doTaskInBackground() returned TASK_NOT_SUCCESSFUL")
                        onCompleted?.invoke()
                    }
                }
            } catch (e: Exception) {
                if (activityWeakReference == null) {
                    return@doAsync
                }
                uiThread {
                    onFailure?.invoke("error: ${e.message ?: ""}")
                    onCompleted?.invoke()
                }
            }

        }
    }
}