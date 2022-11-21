package uz.muhamadaziz.movieapp2database.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.Intents.Insert.DATA
import uz.muhamadaziz.movieapp2database.db.Content.ABOUT
import uz.muhamadaziz.movieapp2database.db.Content.AUTHOR
import uz.muhamadaziz.movieapp2database.db.Content.DATE
import uz.muhamadaziz.movieapp2database.db.Content.DB_NAME
import uz.muhamadaziz.movieapp2database.db.Content.DB_VERSION
import uz.muhamadaziz.movieapp2database.db.Content.ID
import uz.muhamadaziz.movieapp2database.db.Content.NAME
import uz.muhamadaziz.movieapp2database.db.Content.TAB_NAME
import java.net.IDN

class MyDataBase(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION),
    MyDbInterface {
    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "CREATE TABLE $TAB_NAME($ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $NAME TEXT NOT NULL,$AUTHOR TEXT NOT NULL, $ABOUT TEXT NOT NULL, $DATE TEXT NOT NULL )"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    override fun createUser(user: User) {
        val dataBase = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(NAME, user.name)
        contentValue.put(AUTHOR, user.author)
        contentValue.put(ABOUT, user.about)
        contentValue.put(DATE, user.data)
        dataBase.insert(TAB_NAME, null, contentValue)
        dataBase.close()

    }

    override fun readUser(): ArrayList<User> {

        val list = ArrayList<User>()
        val query = "SELECT * FROM $TAB_NAME"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val user = User()
                user.id = cursor.getInt(0)
                user.name = cursor.getString(1)
                user.author = cursor.getString(2)
                user.about = cursor.getString(3)
                user.data = cursor.getString(4)
                list.add(user)
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun myUpdate(user: User): Int {

        val dataBase = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(ID, user.id)
        contentValue.put(NAME, user.name)
        contentValue.put(AUTHOR, user.author)
        contentValue.put(ABOUT, user.about)
        contentValue.put(DATE, user.data)
        return dataBase.update(TAB_NAME, contentValue, "$ID = ?", arrayOf("${user.id}"))

    }

    override fun deleteUser(user: User) {
        val database = this.writableDatabase
        database.delete(TAB_NAME, "$ID = ?", arrayOf("${user.id}"))
        database.close()
    }
}