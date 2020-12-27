package com.example.test_7

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.READ_CONTACTS
                    ) == PackageManager.PERMISSION_GRANTED
            ) {
                val cursor = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        null,
                        null,
                        null
                )
                cursor?.apply {
                    while (moveToNext()) {
                        val name =
                                getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                        val phoneNumber =
                                getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        Log.d("Person", "name = $name ,  number = $phoneNumber")
                    }
                    close()
                }
            } else {
                ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.READ_CONTACTS),
                        1
                )
            }
        }

        button2.setOnClickListener {
            val uri = Uri.parse("content://com.example.test7_contentresovler.provider/person")
            val cursor = contentResolver.query(uri, null, null, null, null)
            cursor?.apply {
                while (moveToNext()) {
                    val name = getString(getColumnIndex("name"))
                    val age = getString(getColumnIndex("age"))
                    val gender = getString(getColumnIndex("gender"))
                    Log.d("Person", "name = $name ,  age = $age, gender = $gender")
                }
                close()
            }
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val cursor = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        null,
                        null,
                        null
                )
                cursor?.apply {
                    while (moveToNext()) {
                        val name =
                                getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                        val phoneNumber =
                                getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        Log.d("Person", "name = $name ,  number = $phoneNumber")
                    }
                    close()
                }
            } else {
                Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show()
            }
        }
    }
}