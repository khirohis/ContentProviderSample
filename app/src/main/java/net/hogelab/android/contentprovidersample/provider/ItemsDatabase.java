package net.hogelab.android.contentprovidersample.provider;

import net.hogelab.android.contentprovidersample.provider.ItemsContract.Items;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by hirohisa on 2014/11/23.
 */
public class ItemsDatabase extends SQLiteOpenHelper {
    private static final String TAG = ItemsDatabase.class.getSimpleName();

    private static final String DATABASE_NAME = "items.db";

    private static final int VERSION1 = 1;
    private static final int DATABASE_VERSION = VERSION1;

    interface Tables {
        String ITEMS = "items";
    }

    public ItemsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Tables.ITEMS + " ("
                + Items._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Items.TITLE + " TEXT NOT NULL,"
                + Items.DESCRIPTION + " TEXT NOT NULL"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade() from " + oldVersion + " to " + newVersion);
    }

    public static void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }
}
