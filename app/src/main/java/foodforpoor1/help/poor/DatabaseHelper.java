package foodforpoor1.help.poor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mitch on 2016-05-13.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mylist7641337.db";
    public static final String TABLE_NAME = "mylist_data7641337";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME1";
    public static final String COL3 = "YEAR1";
    public static final String COL4= "MONTH1";
    public static final String COL5= "DAY1";
    public static final String COL6= "PRICE1";
    public static final String COL7= "STYEAR1";
    public static final String COL8= "STMONTH1";
    public static final String COL9= "STDAY1";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"NAME1 TEXT, "
                +"YEAR1 INTEGER, "
                +"MONTH1 INTEGER, "
                +"DAY1 INTEGER, "
                +"PRICE1 INTEGER, "
                +"STYEAR1 INTEGER, "
                +"STMONTH1 INTEGER, "
                +"STDAY1 INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean addData(String name1, int year1, int month1, int day1, int price1, int styear1, int stmonth1, int stday1 ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name1);
        contentValues.put(COL3, year1);
        contentValues.put(COL4, month1);
        contentValues.put(COL5, day1);
        contentValues.put(COL6, price1);
        contentValues.put(COL7, styear1);
        contentValues.put(COL8, stmonth1);
        contentValues.put(COL9, stday1);





        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
public Cursor showDataonSelect(String nametext) {
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor c=db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL2+"='"+ nametext+"'", null);

    return c;
}
public Cursor updateData(String textname, String name, int year, int month, int day, int price, int styear, int stmonth, int stday ) {
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cu=db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL2+"='"+ textname+"'", null);
    if(cu.moveToFirst()) {
        db.execSQL("UPDATE "+TABLE_NAME+"  SET "+COL2+" ='"+name+"', "+COL3+"='"+ year +"', "+COL4+"='"+ month +"', "+COL5+"='"+ day +"', "+COL6+"='"+ price+"', "+COL7+"='"+ styear +"', "+COL8+"='"+ stmonth +"', "+COL9+"='"+ stday +"'  WHERE "+COL2+" ='"+textname+"'");

    }
    return cu;
}
public Cursor deleteData(String name) {
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cur=db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL2+" ='"+name+"'", null);
    if(cur.moveToFirst())
    {
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE "+COL2+" ='"+ name+"'");

    }
    return cur;
}
public Cursor searchForData (String name1) {
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor data=db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL2+"='"+name1+"'", null);
    return data;
}
public Cursor deleteIstekshiySrokGodnosy(int year, int month,int day) {

    SQLiteDatabase db = this.getWritableDatabase();
    Cursor curs=db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE ("+COL3+"<"+year+") OR ("+COL3+"=="+year+" AND "+COL4+"<"+(month+1)+") OR ("+COL3+"=="+year+" AND "+COL4+"=="+(month+1)+" AND "+COL5+"<="+day+")", null);
    if(curs.moveToFirst())
    {
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE ("+COL3+"<"+year+") OR ("+COL3+"=="+year+" AND "+COL4+"<"+(month+1)+") OR ("+COL3+"=="+year+" AND "+COL4+"=="+(month+1)+" AND "+COL5+"<="+day+")");

        do {
            db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE ("+COL3+"<"+year+") OR ("+COL3+"=="+year+" AND "+COL4+"<"+(month+1)+") OR ("+COL3+"=="+year+" AND "+COL4+"=="+(month+1)+" AND "+COL5+"<="+day+")");

        } while (curs.moveToNext());
    }
    return curs;
}
public Cursor forCount (int year, int month,int day){
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE ("+COL3+"<"+year+") OR ("+COL3+"=="+year+" AND "+COL4+"<"+(month+1)+") OR ("+COL3+"=="+year+" AND "+COL4+"=="+(month+1)+" AND "+COL5+"<="+day+")", null);
    return cursor;
}

}
