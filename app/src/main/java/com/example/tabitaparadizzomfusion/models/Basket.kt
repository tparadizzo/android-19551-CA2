package com.example.tabitaparadizzomfusion.models
/*
Tabita - 19551
Marcelle - 19534
*/

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "baskets")
data class Basket(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "updated_at", defaultValue = "CURRENT_TIMESTAMP") var updatedAt: Long? = 0
)