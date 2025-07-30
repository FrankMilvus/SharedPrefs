package com.example.sharedprefs

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var editTextMessage: EditText
    private lateinit var saveButton: Button
    private lateinit var loadButton: Button
    private lateinit var textViewMessage: TextView

    private val PREF_NAME ="MyPref"

    private lateinit var myPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        saveButton= findViewById<Button>(R.id.btn_save)
        loadButton= findViewById<Button>(R.id.btn_load)
        editTextMessage= findViewById<EditText>(R.id.et_message)
        textViewMessage= findViewById<TextView>(R.id.tv_uploaded_text)

        saveButton.setOnClickListener {
            myPref=getSharedPreferences(PREF_NAME,0)
            val  editor=myPref.edit()

            editor.putString("message",editTextMessage.text.toString())
            editor.commit()

        }
        loadButton.setOnClickListener {
            val prefs=getSharedPreferences(PREF_NAME,0)
            if(prefs.contains("message")){
                val message = prefs.getString("message","not found")
                textViewMessage.text=message
            }
        }
//

    }
}