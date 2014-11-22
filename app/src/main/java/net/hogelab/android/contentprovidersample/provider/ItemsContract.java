package net.hogelab.android.contentprovidersample.provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by hirohisa on 2014/11/19.
 */
public final class ItemsContract {
    public static final String CONTENT_AUTHORITY = "net.hogelab.android.contentprovidersample.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_ITEMS = "items";

    public static final class Items implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ITEMS).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/item";
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/item";

        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";

        public static final String[] ALL_FIELDS = { _ID, TITLE, DESCRIPTION };
        public static final String DEFAULT_SORT = _ID + " ASC";

        public static Uri buildItemUri(String itemId) {
            return CONTENT_URI.buildUpon().appendPath(itemId).build();
        }
        public static String getItemId(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }
}
