package com.example.searchmusic.view

import android.app.Activity
import androidx.appcompat.app.AlertDialog

class WarningDialog {

    companion object {
        fun showErrorDialog(activity: Activity) {
            val dialogBuilder = AlertDialog.Builder(activity)
            dialogBuilder.setMessage("oops something went wrong please try again later")
                .setCancelable(false)
                .setPositiveButton("ok") { dialog, id ->
                    activity.finish()
                }
            val alert = dialogBuilder.create()
            alert.setTitle("Error")
            alert.show()
        }

        fun showOfflineDialog(activity: Activity) {
            val dialogBuilder = AlertDialog.Builder(activity)
            dialogBuilder.setMessage("you are offline, this is a cached data")
                .setCancelable(false)
                .setPositiveButton("ok") { dialog, id ->
                    dialog.cancel()
                }
            val alert = dialogBuilder.create()
            alert.setTitle("Offline Alert")
            alert.show()
        }
    }

}