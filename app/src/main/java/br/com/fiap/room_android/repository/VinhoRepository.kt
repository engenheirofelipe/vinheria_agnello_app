package br.com.fiap.room_android.repository

import android.content.Context
import br.com.fiap.room_android.database.dao.VinhoDb
import br.com.fiap.room_android.model.Vinho

class VinhoRepository(context: Context) {

    var db = VinhoDb.getDatabase(context).vinhoDao()

    fun salvar(vinho: Vinho):Long {
        return db.salvar(vinho = vinho)
    }

    fun atualizar(vinho:Vinho):Int{
        return db.atualizar(vinho = vinho)
    }

    fun deletar(vinho:Vinho):Int{
        return db.deletar(vinho = vinho)
    }

    fun listar():List<Vinho>{
        return db.listar()
    }

    fun buscarPorId(id:Long):Vinho?{
        return db.buscarPorId(id = id)
    }




}