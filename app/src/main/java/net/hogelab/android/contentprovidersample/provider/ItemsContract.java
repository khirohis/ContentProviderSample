package net.hogelab.android.contentprovidersample.provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by hirohisa on 2014/11/19.
 */
public final class ItemsContract {
    public static final String AUTHORITY = "net.hogelab.android.contentprovidersample.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final class Items implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(
                ItemsContract.CONTENT_URI, "items");

        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/items";

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/items";

//        public static final String[] PROJECTION_ALL = {_ID, TITLE, DESCRIPTION};
//        public static final String SORT_ORDER_DEFAULT = TITLE + " ASC";

        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
    }
}
