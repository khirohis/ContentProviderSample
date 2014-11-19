package net.hogelab.android.contentprovidersample.provider;

import android.content.ContentProvider;
import android.content.UriMatcher;

import java.net.URI;

/**
 * Created by kobayasi on 2014/11/19.
 */
public class ItemProvider extends ContentProvider {
    private static final UriMatcher uriMatcher;
    private static final int ITEM = 1;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(Persons.AUTHORITY, "persons", PERSONS);
        uriMatcher.addURI(Persons.AUTHORITY, "persons/#", PERSON_ID);
    }

    @Override
    public boolean onCreate() {

        return true;
    }

    @Override
    public String getType(URI uri) {
        switch (uriMatcher.match(uri)) {
            case ITEM:
                return "";

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }
}
