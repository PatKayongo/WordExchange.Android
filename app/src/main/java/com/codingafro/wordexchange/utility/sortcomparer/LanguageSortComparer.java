package com.codingafro.wordexchange.utility.sortcomparer;

import com.codingafro.wordexchange.models.Language;

import java.util.Comparator;

/**
 * Created by pkayongo on 15/03/27.
 */
public class LanguageSortComparer implements Comparator<Language> {
    @Override
    public int compare(Language lhs, Language rhs) {
        return lhs.getName().compareTo(rhs.getName());
    }
}
