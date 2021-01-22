package co.tiagoaguiar.codelab.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SQLHelper extends SQLiteOpenHelper {

    private static final  String DB_NAME = "fitness_tracker.db";
    private static final int DB_VERSION = 1;
    private static SQLHelper INSTANCE;

    static SQLHelper getINSTANCE(Context context){
        if(INSTANCE == null)
            INSTANCE = new SQLHelper(context);
        return INSTANCE;

    }

    private SQLHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE calc(id INTEGER primary key, type_calc TEXT, res DECIMAL, created_date DATETIME)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }

    long addItem(String type, double response){
        SQLiteDatabase db = getWritableDatabase();
        long calcID = 0;

        try {
          db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put("type_calc", type);
            values.put("res", response);

            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd H:mm:ss", new Locale("pt", "BR"));
            String now = sdf.format(new Date());
            values.put("created_date", now);
            db.insertOrThrow("calc", null, values);

            db.setTransactionSuccessful();

        } catch (Exception e){
            Log.e("SQLite",e.getMessage(),e);

        } finally {
            if (db.isOpen()){
            db.endTransaction();
            }
        }
        return calcID;
    }
}
