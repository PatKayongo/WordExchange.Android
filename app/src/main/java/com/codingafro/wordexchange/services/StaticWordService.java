package com.codingafro.wordexchange.services;

import java.util.ArrayList;

/**
 * Created by pkayongo on 15/03/27.
 */

public class StaticWordService implements WordService {

    protected class Translation {
        private String sourceWord;
        private String sourceLanguageIso;
        private String targetWord;
        private String targetLanguageIso;

        public Translation(String sourceWord, String sourceLanguageIso, String targetWord, String targetLanguageIso) {
            this.sourceWord = sourceWord;
            this.sourceLanguageIso = sourceLanguageIso;
            this.targetWord = targetWord;
            this.targetLanguageIso = targetLanguageIso;
        }

        public String getSourceWord() {
            return sourceWord;
        }

        public String getSourceLanguageIso() {
            return sourceLanguageIso;
        }

        public String getTargetWord() {
            return targetWord;
        }

        public String getTargetLanguageIso() {
            return targetLanguageIso;
        }
    }

    private ArrayList<Translation> _translations;

    public StaticWordService()
    {
        this._translations = new ArrayList<>();
        this._translations.add(new Translation("go", "eng", "genda", "lug"));
        this._translations.add(new Translation("go", "eng", "hamba", "xho"));
    }

    @Override
    public String getWord(String sourceWord, String sourceLanguageIso, String targetLanguageIso) {

        for (Translation translation : this._translations) {
            if (translation.getSourceWord().equals(sourceWord) && translation.getSourceLanguageIso().equals(sourceLanguageIso) && translation.getTargetLanguageIso().equals(targetLanguageIso))
            {
                return translation.getTargetWord();
            }
            else if (translation.getTargetWord().equals(sourceWord) && translation.getTargetLanguageIso().equals(sourceLanguageIso) && translation.getSourceLanguageIso().equals(targetLanguageIso))
            {
                return translation.getSourceWord();
            }
        }

        return null;
    }
}
