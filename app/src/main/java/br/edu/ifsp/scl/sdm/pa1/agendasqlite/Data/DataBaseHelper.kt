package br.edu.ifsp.scl.sdm.pa1.agendasqlite.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.edu.ifsp.scl.sdm.pa1.agendasqlite.Model.Contato
import java.util.ArrayList

class DataBaseHelper(context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private val DATABASE_NAME = "agenda.db"
        private val DATABASE_VERSION = 1
        private val TABLE_NAME = "contatos"
        private val ID = "id"
        private val NOME = "nome"
        private val FONE = "fone"
        private val EMAIL = "email"
//      private val FONE2 = "fone2"
//      private val ENDERECO = "endereco"
    }


    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME($ID INTINGER PRIMARY KEY, $NOME TEXT, $FONE TEXT, $EMAIL TEXT)"
        p0?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        /* if (p1 < 2) //migra da versao 1 para 2
        {
            val sql = "ALTER TABLE $TABLE_NAME ADD COLUMN $FONE2 TEXT"
            p0?.execSQL(sql)
        }
        if (p1 < 3)  //migrando de 2 para 3
        {
            val sql = "ALTER TABLE $TABLE_NAME ADD COLUMN $ENDERECO TEXT"
            p0?.execSQL(sql)
        }*/
    }


    fun inserirContato(contato: Contato): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(ID, contato.id)
        values.put(NOME, contato.nome)
        values.put(FONE, contato.fone)
        values.put(EMAIL, contato.email)
        val result = db.insert(TABLE_NAME, null, values)
        db.close()
        return result
    }

    fun atualizarContato(contato: Contato): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(ID, contato.id)
        values.put(NOME, contato.nome)
        values.put(FONE, contato.fone)
        values.put(EMAIL, contato.email)
        val result = db.update(TABLE_NAME, values, "id=?", arrayOf(contato.id.toString()))
        db.close()
        return result
    }

    fun apagarContato(contato: Contato): Int {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME, "id=?", arrayOf(contato.id.toString()))
        db.close()
        return result
    }

    fun listarContato(): ArrayList<Contato> {
        val listaContatos = ArrayList<Contato>()
        val query = "SELECT * FROM $TABLE_NAME ORDER BY $NOME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            val c = Contato(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
            )
            listaContatos.add(c)

        }
        cursor.close()
        db.close()
        return listaContatos

    }
}