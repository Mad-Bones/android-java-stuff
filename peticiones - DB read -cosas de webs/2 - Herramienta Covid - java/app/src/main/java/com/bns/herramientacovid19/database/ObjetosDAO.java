package com.bns.herramientacovid19.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ObjetosDAO {

    @Query("SELECT * FROM Objeto ")
    List<Objeto> fetchAllObjeto();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertObjeto(Objeto objeto);

    @Query("DELETE FROM Objeto")
    void delete();

}
