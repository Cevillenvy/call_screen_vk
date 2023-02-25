package vk.call_screen_diving

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import org.w3c.dom.Text
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      Проверка длины текста
        val user2TextView = findViewById<TextView>(R.id.activity_main__person2_name)
        var user2Text = user2TextView.text
        if(user2Text.length > 50){
            user2TextView.text = user2Text.substring(0, 47) + "..."
        }


//      Открытие системного приложения для SMS
        val chatIconButton = findViewById<ImageButton>(R.id.activity_main__chat)
        chatIconButton.setOnClickListener{
            val sendIntent = Intent(Intent.ACTION_VIEW)
            sendIntent.data = Uri.parse("sms:")
            startActivity(sendIntent)
        }

//      Список контактов
        val contactsIconButton = findViewById<ImageButton>(R.id.activity_main__people)
        contactsIconButton.setOnClickListener{
            val intent = Intent(this, ContactsList::class.java)
            startActivity(intent)
            finish()
        }

//      Поменять местами плитки
        val user1 = findViewById<FrameLayout>(R.id.activity_main__person1)
        val user1card = findViewById<CardView>(R.id.activity_main__person1_card)
        val user2 = findViewById<FrameLayout>(R.id.activity_main__person2)
        val user2card = findViewById<CardView>(R.id.activity_main__person2_card)
        val tableIconButton = findViewById<ImageButton>(R.id.activity_main__table)
        var tableIconToggle = true
        tableIconButton.setOnClickListener{
            user1.removeAllViews()
            user2.removeAllViews()
            if(tableIconToggle){
                user1.addView(user2card)
                user2.addView(user1card)
                tableIconToggle = !tableIconToggle
            }
            else{
                user1.addView(user1card)
                user2.addView(user2card)
                tableIconToggle = !tableIconToggle
            }
        }

//      Переключение иконки камеры
        var videoIconToggle = false
        val videoIconButton = findViewById<ImageButton>(R.id.activity_main__videocam)
        videoIconButton.setOnClickListener{
            videoIconToggle = if(videoIconToggle){
                videoIconButton.setImageResource(R.drawable.ic_baseline_videocam_off_32)
                !videoIconToggle
            } else{
                videoIconButton.setImageResource(R.drawable.ic_baseline_videocam_24)
                !videoIconToggle
            }
        }

//      Переключение иконки микрофона
        var micIconToggle = false
        val micIconButton = findViewById<ImageButton>(R.id.activity_main__microphone)
        val userMicIconButton = findViewById<TextView>(R.id.activity_main__user1_mic)
        micIconButton.setOnClickListener{
            micIconToggle = if(micIconToggle){
                micIconButton.setImageResource(R.drawable.ic_baseline_mic_off_32)
                userMicIconButton.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_mic_off_16,0)
                !micIconToggle
            } else{
                micIconButton.setImageResource(R.drawable.ic_baseline_mic_24)
                userMicIconButton.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_mic_16,0)
                !micIconToggle
            }
        }

//      Alert Dialog "Привет"
        val  handIconButton = findViewById<ImageButton>(R.id.activity_main__hand)
        handIconButton.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Привет!")
            builder.show()
        }

//      Выход из приложения
        val exitIconButton = findViewById<ImageButton>(R.id.activity_main__exit)
        exitIconButton.setOnClickListener{
            exitProcess(-1)
        }
    }
}