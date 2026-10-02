package com.example.sendmessage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.sendmessage.model.Message
import com.example.sendmessage.model.Person
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Launcher screen where the user types a message and sends it.
 *
 * Pressing the send button opens [ViewMessageActivity], passing the entered text as a
 * string extra under the key [ViewMessageActivity.KEY_MESSAGE].
 */

/**
 * Esta es la primera actividad de la aplicacion que realiza las operaciones:
 * <ol>
 *     <li>Crear un componente <code>EditText</code> y <code>Button</code> en XML</li>
 *     <li>Lanza un evento en un componente visual</li>
 *     <li>Crea el <code>Intent</code> para lanzar la segunda actividad</li>
 *     <li>El ciclo de vida de la <code>Activity</code></li>
 *     <li>Ver la pila de actividades</li>
 *
 * </ol>
 *
 * @author Jorge Peralta
 * @version 1.0.0
 * @see android.widget.Button
 * @see android.widget.EditText
 * @see Intent
 * @see android.os.Bundle
 */

class SendMessageActivity : AppCompatActivity() {
    lateinit var etMessageText: EditText
    lateinit var btSend: Button
    companion object {
        const val TAG: String = "LogSendMessageActivity"
    }

    /** Inflates `activity_send_message` and wires the send button to launch [ViewMessageActivity]. */

    /**
     * Metodo de creacion de una actividad
     * @param android.os.Bundle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)

        etMessageText = findViewById<EditText>(R.id.etMessageText)
        btSend = findViewById<Button>(R.id.btSend)

        btSend.setOnClickListener {
            /* 1. Pasar dato a dato en un bundle
            val intent = Intent(this, ViewMessageActivity::class.java)
            intent.putExtra(ViewMessageActivity.KEY_MESSAGE, etMessageText.text.toString())
            startActivity(intent)
             */

            sendMessage()
        }
        //Se escribe mensajes de depuración en la consola LogCat
        Log.d(TAG, "SendMessageActivity -> onCreate()")
    }

    /**
     * Funcion que crea un mensaje con la informacion de la persona que envia y de la persona que recoge el mensaje
     */
    private fun sendMessage(){
        //1. Crear el intent
        val intent = Intent(this, ViewMessageActivity::class.java)
        //2. Crear el bundle
        val bundle = Bundle()
        //3. La información del mensaje
        val sender = Person("12345678A", "Jorge", "Peralta")
        val receiver = Person("87654321B", "Juan", "Perez")
        val message = Message(1, etMessageText.text.toString(), sender, receiver)

        bundle.putSerializable(ViewMessageActivity.KEY_MESSAGE, message)
        bundle.putString(ViewMessageActivity.KEY_SENDER, "${sender.name} ${sender.surname}")
        intent.putExtras(bundle)
        startActivity(intent)
    }

    //region Ciclo de vida de una Actividad
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "SendMessageActivity -> onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "SendMessageActivity -> onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "SendMessageActivity -> onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "SendMessageActivity -> onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "SendMessageActivity -> onDestroy()")
    }

//endregion
}