package com.codingafro.wordexchange.repositories;

import com.codingafro.wordexchange.models.Language;

import java.util.ArrayList;

/**
 * Created by pkayongo on 15/03/27.
 */
public interface LanguageRepository {
    ArrayList<Language> getAllLanguages();
}
