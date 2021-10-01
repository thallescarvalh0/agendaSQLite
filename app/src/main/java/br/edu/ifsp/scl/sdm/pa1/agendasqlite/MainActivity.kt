package br.edu.ifsp.scl.sdm.pa1.agendasqlite

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import br.edu.ifsp.scl.sdm.pa1.agendasqlite.Data.ContatoAdapter
import br.edu.ifsp.scl.sdm.pa1.agendasqlite.Data.DataBaseHelper
import br.edu.ifsp.scl.sdm.pa1.agendasqlite.Model.Contato

class MainActivity : AppCompatActivity() {

    val db = DataBaseHelper(this)
    var contatosLista = ArrayList<Contato>()
    var contatoAdapter: ContatoAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateUI()
    }

    fun updateUI()
    {
        val contatosLista = db.listarContato()
        contatoAdapter = ContatoAdapter(contatosLista)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = contatoAdapter

    }
}