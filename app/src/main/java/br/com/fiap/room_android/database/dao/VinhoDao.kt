package br.com.fiap.room_android.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.room_android.model.Vinho

@Dao
interface VinhoDao {

    @Insert
    fun salvar(vinho: Vinho): Long

    @Update
    fun atualizar(vinho: Vinho): Int

    @Delete
    fun deletar(vinho: Vinho): Int

    @Query("SELECT * FROM tbl_vinho ORDER BY nome ASC")
    fun listar(): List<Vinho>

    @Query("SELECT * FROM tbl_vinho WHERE ID = :id")
    fun buscarPorId(id: Long): Vinho?

}