package com.example.nagato.githubnewsprovider.db;

import android.content.SearchRecentSuggestionsProvider;

public class SearchSuggestionProvider extends SearchRecentSuggestionsProvider {
    public static final String AUTHORITY = "com.example.ivor_hu.widget.SearchRecentSuggestionsProvider";
    public static final int MODE = DATABASE_MODE_QUERIES;

    public SearchSuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
