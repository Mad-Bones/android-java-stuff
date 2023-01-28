package com.bns.herramientacovid19.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = Objeto.class, version = 2, exportSchema = false)
public abstract class BaseDatos extends RoomDatabase {

    private static BaseDatos BaseDatos;

    public static synchronized BaseDatos getDatabase(Context context){

        if (BaseDatos == null) {
            BaseDatos = Room.databaseBuilder(
                    context,
                    BaseDatos.class,
                    "data_base"
            ).build();
        }
        return BaseDatos;
    }
    public abstract ObjetosDAO objetosDao();

}
