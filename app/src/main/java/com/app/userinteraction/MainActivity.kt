package com.app.userinteraction

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var showToast: Button
    lateinit var showSnackbar: Button
    lateinit var myLayout: ConstraintLayout
    lateinit var showDialog: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        showToast = findViewById(R.id.buttonToastMasage)
        showSnackbar = findViewById(R.id.buttonSnackbarMassage)
        myLayout = findViewById(R.id.myLayout)
        showDialog = findViewById(R.id.buttonDialogMassage)

        showToast.setOnClickListener {
            Toast.makeText(applicationContext, "This is a toast message", Toast.LENGTH_LONG).show()
        }

        showSnackbar.setOnClickListener {
            Snackbar.make(myLayout, "This is a snackbar message", Snackbar.LENGTH_INDEFINITE)
                .setAction("close", View.OnClickListener {

                }).show()
        }

        showDialog.setOnClickListener {
           showAlertDialog()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.myLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun showAlertDialog(){
      var alertDialog = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Change")
            .setMessage("Do you want to change the text of the button?")
            .setIcon(R.drawable.round_warning_24)
            .setCancelable(false)// prevents user from cancelling with back button
            .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, which ->
                dialogInterface.cancel()
            })
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, which ->
                showDialog.text = "Changed"
            })

        alertDialog.create().show()// creates alert dialog
    }
}