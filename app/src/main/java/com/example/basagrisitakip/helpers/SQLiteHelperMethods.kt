package com.example.basagrisitakip.helpers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
class SQLiteHelperMethods(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int,
    errorHandler: DatabaseErrorHandler?
) : SQLiteOpenHelper(context, name, factory, version, errorHandler) {

    private val DATABASE_NAME = "suggestion"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE suggestion (id INTEGER PRIMARY KEY AUTOINCREMENT, pills TEXT, triggers TEXT, symptoms TEXT, startTime TEXT, endTime TEXT, pain INTEGER)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addRecord(
        pills: ArrayList<String>,
        triggers: ArrayList<String>,
        symptoms: ArrayList<String>,
        start: String,
        end: String,
        painIntensity: Int,
    ) {
        val db: SQLiteDatabase = this.writableDatabase
        val gson = Gson()

        val pillString: kotlin.String = gson.toJson(pills)
        val triggerString: kotlin.String = gson.toJson(triggers)
        val symptomsString: kotlin.String = gson.toJson(symptoms)

        val values = ContentValues()
        values.put("pills", pillString)
        values.put("triggers", triggerString)
        values.put("symptoms", symptomsString)
        values.put("startTime", start)
        values.put("endTime", end)
        values.put("pain", painIntensity)
        db.insert(DATABASE_NAME, null, values)
        db.close()
    }

    fun findRecord(id: String): String {
        val db: SQLiteDatabase = this.writableDatabase
        val findQuery = "SELECT * FROM $DATABASE_NAME WHERE id = $id "
        db.execSQL(findQuery)
        db.close()
        return db.toString()
    }

    fun updateRecord(id: Int, newSuggestion: String) {
        val db: SQLiteDatabase = this.writableDatabase
        val updateQuery = "UPDATE $DATABASE_NAME SET suggestion = $newSuggestion WHERE id = $id"
        db.execSQL(updateQuery)
        db.close()
    }

    fun deleteRecord(id: Int) {
        val db: SQLiteDatabase = this.writableDatabase
        val deleteQuery = "DELETE FROM $DATABASE_NAME WHERE id = $id "
        db.execSQL(deleteQuery)
        db.close()
    }

    /*fun showRecord(): ArrayList<SuggestionModel> {
        val db: SQLiteDatabase = this.writableDatabase
        val showQuery = "SELECT * FROM $DATABASE_NAME"
        val cursor: Cursor = db.rawQuery(showQuery, null)
        var suggestionList = ArrayList<SuggestionModel>()
        var sugg: String
        var suggId: Int

        if (cursor.moveToFirst()) {
            do {
                suggId = cursor.getString(0).toInt()
                sugg = cursor.getString(1)
                suggestionList.add(SuggestionModel(suggId, sugg))
            } while (cursor.moveToNext())
        }

        return suggestionList
    }*/


}