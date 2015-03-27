package com.codingafro.wordexchange.models;

/**
 * Created by pkayongo on 15/03/27.
 */
public class Language {
    private int languageId;
    private String languageIsoCode;
    private String languageName;

    public Language(int languageId, String isoCode, String name)
    {
        this.languageId = languageId;
        this.languageIsoCode = isoCode;
        this.languageName = name;
    }

    public int getLanguageId() {
        return this.languageId;
    }

    public String getIsoCode() {
        return this.languageIsoCode;
    }

    public String getName() {
        return this.languageName;
    }
}
