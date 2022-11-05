package com.example.password_saver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.password_saver.Auth.Login
import com.example.password_saver.Dados.Senha
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        val getUserpath = intent.getStringExtra("User")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.btn_salvar)
        val btn_result = findViewById<Button>(R.id.btn_senhas)
        val btn_sair = findViewById<TextView>(R.id.txt_sair)
        Log.d("value","$getUserpath")


        btn.setOnClickListener {
            saveAll()

        }
        btn_result.setOnClickListener {
            val intent = Intent(this,senhas::class.java)
            intent.putExtra("UserS","${getUserpath}")
            startActivity(intent)
        }
        btn_sair.setOnClickListener{
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }
    }

    private fun saveAll() {

        val getUserpath = intent.getStringExtra("User")
        val nome = findViewById<EditText>(R.id.nome).text.toString().trim()
        val senha = findViewById<EditText>(R.id.senha).text.toString().trim()

        val ref = FirebaseDatabase.getInstance().getReference("${getUserpath}")

//        val db = FirebaseFirestore.getInstance()
//        db.collection("senhas")
//        val senhaid = ref.push().key

        val senhaDB = Senha(nome,senha)



        ref.child(nome).setValue(senhaDB)

        Toast.makeText(this,"Itens Salvos",Toast.LENGTH_SHORT).show()

    }


}