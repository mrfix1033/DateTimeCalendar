package ru.mrfix1033.datetimecalendar

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var editTextName: EditText
    private lateinit var editTextSurname: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var editTextBirthDay: EditText
    private lateinit var editTextBirthMonth: EditText
    private lateinit var editTextBirthYear: EditText
    private lateinit var buttonSave: Button

    private var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        imageView = findViewById(R.id.imageView)
        editTextName = findViewById(R.id.editTextName)
        editTextSurname = findViewById(R.id.editTextSurname)
        editTextPhone = findViewById(R.id.editTextPhone)
        editTextBirthDay = findViewById(R.id.editTextBirthDay)
        editTextBirthMonth = findViewById(R.id.editTextBirthMonth)
        editTextBirthYear = findViewById(R.id.editTextBirthYear)
        buttonSave = findViewById(R.id.buttonSave)

        imageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            pickPhoto.launch(intent)
        }
        buttonSave.setOnClickListener {
            val intent = Intent(this, PersonActivity::class.java)
            intent.putExtra("person", Person(
                editTextName.text.toString(),
                editTextSurname.text.toString(),
                editTextPhone.text.toString(),
                Date(
                    editTextBirthYear.text.toString().toInt() - 1900,
                    editTextBirthMonth.text.toString().toInt() - 1,
                    editTextBirthDay.text.toString().toInt()
                ).time,
                uri
            ))
            startActivity(intent)
        }
    }

    val pickPhoto = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.data == null) return@registerForActivityResult
        uri = it.data!!.data
        imageView.setImageURI(uri)
    }
}