package com.yoavi.materialtest1;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by Chikki on 10/6/2016.
 */
public class SearchableProvider extends SearchRecentSuggestionsProvider {

    public static final String AUTHORITY= "com.yoavi.materialtest1.SearchableProvider";
    public static final int MODE= DATABASE_MODE_QUERIES;

    public SearchableProvider() {
        setupSuggestions(AUTHORITY,MODE);
    }
}
