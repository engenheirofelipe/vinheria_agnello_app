package br.com.fiap.room_android.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_vinho")
data class Vinho(

    @PrimaryKey(autoGenerate = true)
    var id:Long = 0,

    var nome:String = "",
    var marca:String = "",
    var paisOrigem:String = "",
    var tempoEnvelhecimento:String = "",
    var tipo:String = "",
    var ocasiao:String = "",

)




