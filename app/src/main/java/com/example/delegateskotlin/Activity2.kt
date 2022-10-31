package com.example.delegateskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

// Kotlin's put of box support for delegates
class Activity2 : AppCompatActivity(), ToolbarDelegate by ToolbarDelegateImpl() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        addToolbar(
            toolbar = findViewById(R.id.toolbar2),
            title = getString(R.string.app_name),
            enableHomeAsUp = true
        )

        var button: Button = findViewById(R.id.button)
        button.setOnClickListener { finish() }

        Log.d("checkLc", "onCreate Activity2")
    }

    override fun onStart() {
        super.onStart()
        Log.d("checkLs", "onStart Activity2")
    }

    override fun onResume() {
        Log.d("checkLc", "onResume Activity2")
        super.onResume()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("checkLc", "onRestart Activity2")
    }

    override fun onPause() {
        super.onPause()
        Log.d("checkLc", "onPause Activity2")
    }

    override fun onStop() {
        super.onStop()
        Log.d("checkLc", "onStop Activity2")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("checkLc","onDestroy Activity2")
    }

}

/**
 * Lifecycle logs:
 *
opening Activity2 and pressing back button
2022-11-01 04:47:21.849 25312-25312/com.example.delegateskotlin D/checkLc: onResume Main
2022-11-01 04:47:31.400 25312-25312/com.example.delegateskotlin D/checkLc: onPause Main
2022-11-01 04:47:31.471 25312-25312/com.example.delegateskotlin D/checkLc: onCreate Activity2
2022-11-01 04:47:31.477 25312-25312/com.example.delegateskotlin D/checkLc: onResume Activity2
2022-11-01 04:47:31.972 25312-25312/com.example.delegateskotlin D/checkLc: onStop Main
2022-11-01 04:47:37.554 25312-25312/com.example.delegateskotlin D/checkLc: onPause Activity2
2022-11-01 04:47:37.569 25312-25312/com.example.delegateskotlin D/checkLc: onRestart Main
2022-11-01 04:47:37.571 25312-25312/com.example.delegateskotlin D/checkLc: onResume Main
2022-11-01 04:47:38.050 25312-25312/com.example.delegateskotlin D/checkLc: onStop Activity2
2022-11-01 04:47:38.051 25312-25312/com.example.delegateskotlin D/checkLc: onDestroy Activity2

on closing MainActivity with back button click
2022-11-01 04:48:47.606 25312-25312/com.example.delegateskotlin D/checkLc: onPause Main
2022-11-01 04:48:48.002 25312-25312/com.example.delegateskotlin D/checkLc: onStop Main

on clearing from cache
2022-11-01 04:49:56.402 25579-25579/com.example.delegateskotlin D/checkLc: onDestroy Main

 */