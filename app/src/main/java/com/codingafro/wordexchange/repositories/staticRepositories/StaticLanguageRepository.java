package com.codingafro.wordexchange.repositories.staticRepositories;

import com.codingafro.wordexchange.models.Language;
import com.codingafro.wordexchange.repositories.LanguageRepository;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by pkayongo on 15/03/27.
 */
public class StaticLanguageRepository implements LanguageRepository {

    private ArrayList<Language> languages;

    public StaticLanguageRepository()
    {
        this.languages = new ArrayList<>();
        this.languages.add(new Language(1, "lug", "Luganda"));
        this.languages.add(new Language(2, "eng", "English"));
        this.languages.add(new Language(3, "xho", "Xhosa"));
        this.languages.add(new Language(4, "zul", "Zulu"));
    }

    @Override
    public ArrayList<Language> getAllLanguages() {
        return this.languages;
    }
}
