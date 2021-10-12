package com.e.trabalho1probmobile.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.e.trabalho1probmobile.dao.EmprestimosDAO;
import com.e.trabalho1probmobile.dao.EquipamentosDAO;
import com.e.trabalho1probmobile.schemas.EmprestimoROM;
import com.e.trabalho1probmobile.schemas.EquipamentoROM;

@Database(entities = {EmprestimoROM.class, EquipamentoROM.class,}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EmprestimosDAO emprestimosDAO();

    public abstract EquipamentosDAO equipamentosDAO();

    private static AppDatabase db;

    public static AppDatabase getInstance(Context context) {
        if (db == null) {
            return Room.databaseBuilder(context,
                    AppDatabase.class, "trabalho-progmobile")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }

}
