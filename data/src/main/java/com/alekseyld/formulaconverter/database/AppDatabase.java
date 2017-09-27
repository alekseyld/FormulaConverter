package com.alekseyld.formulaconverter.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Alekseyld on 27.09.2017.
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {
    public static final String NAME = "FormulaConverter_AppDatabase";
    public static final int VERSION = 1;
}
