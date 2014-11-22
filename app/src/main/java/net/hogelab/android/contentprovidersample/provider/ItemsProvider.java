package net.hogelab.android.contentprovidersample.provider;

import net.hogelab.android.contentprovidersample.provider.ItemsContract.Items;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.FileNotFoundException;

/**
 * Created by kobayasi on 2014/11/19.
 */
public class ItemsProvider extends ContentProvider {
    private static final String TAG = ItemsProvider.class.getSimpleName();

    private ItemsDatabase mOpenHelper;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private static final int ITEMS = 100;
    private static final int ITEMS_ID = 101;

    private static UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        String authority = ItemsContract.CONTENT_AUTHORITY;
        sUriMatcher.addURI(authority, "items", ITEMS);
        sUriMatcher.addURI(authority, "items/#", ITEMS_ID);

        return matcher;
    }

    private void deleteDatabase() {
        mOpenHelper.close();
        Context context = getContext();
        ItemsDatabase.deleteDatabase(context);

        mOpenHelper = new ItemsDatabase(context);
    }

    private void notifyChange(Uri uri) {
//        if (!ScheduleContract.hasCallerIsSyncAdapterParameter(uri)) {
//            Context context = getContext();
//            context.getContentResolver().notifyChange(uri, null);
//            context.sendBroadcast(ScheduleWidgetProvider.getRefreshBroadcastIntent(context, false));
//        }
    }


    @Override
    public boolean onCreate() {
        mOpenHelper = new ItemsDatabase(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                return Items.CONTENT_TYPE;

            case ITEMS_ID:
                return Items.CONTENT_ITEM_TYPE;

            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();

        int match = sUriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                break;

            case ITEMS_ID:
                break;

            default:
                break;
        }

        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.v(TAG, "insert(uri=" + uri + ", values=" + values.toString() + ")");

        SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        switch (match) {
            case ITEMS: {
                db.insertOrThrow(ItemsDatabase.Tables.ITEMS, null, values);
                notifyChange(uri);
                return Items.buildItemUri(values.getAsString(Items._ID));
            }

            case ITEMS_ID:
                break;

            default:
                break;
        }

        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.v(TAG, "update(uri=" + uri + ", values=" + values.toString() + ")");

        int result = 0;

        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        switch (match) {
            case ITEMS: {
                result = db.update(ItemsDatabase.Tables.ITEMS, values, selection, selectionArgs);
                notifyChange(uri);
            }

            case ITEMS_ID:
                break;

            default:
                break;
        }

        return result;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.v(TAG, "delete(uri=" + uri + ")");

        if (uri == ItemsContract.BASE_CONTENT_URI) {
            deleteDatabase();
            notifyChange(uri);

            return 1;
        }

        int result = 0;

        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        switch (match) {
            case ITEMS: {
                result = db.delete(ItemsDatabase.Tables.ITEMS, selection, selectionArgs);
                notifyChange(uri);
            }

            case ITEMS_ID:
                break;

            default:
                break;
        }

        return result;
    }

    @Override
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        int match = sUriMatcher.match(uri);
        switch (match) {
            default: {
                throw new UnsupportedOperationException("Unknown URI: " + uri);
            }
        }
    }
}
