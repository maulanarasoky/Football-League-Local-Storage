package com.example.footballleaguelocalstorage.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.footballleaguelocalstorage.model.favorite.Favorite
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {

    companion object{
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper{
            if (instance == null){
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.ID_EVENT to TEXT + UNIQUE,
            Favorite.EVENT_NAME to TEXT,
            Favorite.HOME_PHOTO to TEXT,
            Favorite.AWAY_PHOTO to TEXT,
            Favorite.NAME_HOME_TEAM to TEXT,
            Favorite.NAME_AWAY_TEAM to TEXT,
            Favorite.SCORE_HOME_TEAM to TEXT,
            Favorite.SCORE_AWAY_TEAM to TEXT,
            Favorite.DATE_EVENT to TEXT,
            Favorite.STR_THUMB to TEXT,
            Favorite.STATUS to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)