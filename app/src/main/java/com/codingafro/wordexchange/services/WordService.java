package com.codingafro.wordexchange.services;

/**
 * Created by pkayongo on 15/03/27.
 */
public interface WordService {
    String getWord(String sourceWord, String sourceLanguageIso, String targetLanguageIso);
}
