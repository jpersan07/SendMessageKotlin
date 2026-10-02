package com.example.sendmessage

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sendmessage.model.Message

/**
 * "Message received" screen, started from [SendMessageActivity].
 *
 * Reads the [KEY_MESSAGE] extra sent by the caller and shows it in `tvMessage`.
 */
class ViewMessageActivity : AppCompatActivity() {

    companion object {
        /** Intent extra key holding the message text to display. */
        const val KEY_MESSAGE = "KEY_MESSAGE"
        /** Intent extra key holding the sender's full name to display. */
        const val KEY_SENDER = "KEY_SENDER"
        const val TAG: String = "LogSendMessageActivity"
    }

    /**
     * Enables edge-to-edge drawing, pads the root view (`R.id.main`) by the system bar insets
     * and displays the received message.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_message)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        @Suppress("DEPRECATION")
        val message = intent.getSerializableExtra(KEY_MESSAGE) as? Message
        val tvMessage = findViewById<TextView>(R.id.tvMessage)
        tvMessage.text = message?.content

        val tvSender = findViewById<TextView>(R.id.tvSender)
        tvSender.text = intent.getStringExtra(KEY_SENDER)
    }

    //region Ciclo de vida de una Actividad
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "ViewMessageActivity -> onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "ViewMessageActivity -> onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "ViewMessageActivity -> onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "ViewMessageActivity -> onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "ViewMessageActivity -> onDestroy()")
    }

//endregion
}