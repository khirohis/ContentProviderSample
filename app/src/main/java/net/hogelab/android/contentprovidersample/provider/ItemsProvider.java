package net.hogelab.android.contentprovidersample.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by kobayasi on 2014/11/19.
 */
public class ItemsProvider extends ContentProvider {
    private static final int ITEMS_DIR = 1;
    private static final int ITEMS_ID = 2;
    private static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(ItemsContract.AUTHORITY, "items", ITEMS_DIR);
        URI_MATCHER.addURI(ItemsContract.AUTHORITY, "items/#", ITEMS_ID);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case ITEMS_DIR:
                return ItemsContract.Items.CONTENT_DIR_TYPE;

            case ITEMS_ID:
                return ItemsContract.Items.CONTENT_ITEM_TYPE;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }
}
