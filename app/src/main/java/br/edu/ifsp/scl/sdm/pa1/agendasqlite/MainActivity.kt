package br.edu.ifsp.scl.sdm.pa1.agendasqlite

import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.sdm.pa1.agendasqlite.databinding.ActivityMainBinding
import android.os.Bundle
import android.util.Log

import br.edu.ifsp.scl.sdm.pa1.agendasqlite.Data.DataBaseHelper

import br.edu.ifsp.scl.sdm.pa1.agendasqlite.Model.Contato

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val db = DataBaseHelper(this)

        val c1 = Contato(1, "Thalles", "34245631", "thalles@hotmail.com")
        val c2 = Contato(2, "Teste", "3424444", "teste@hotmail.com")

        db.inserirContato(c1)
        db.inserirContato(c2)

        val cursor = db.listarContato()
        for (i in cursor){
            Log.d("Nome", i.nome)
            Log.d("Fone", i.fone)
        }



    }
}