package com.codingafro.wordexchange;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.codingafro.wordexchange.models.Language;
import com.codingafro.wordexchange.repositories.LanguageRepository;
import com.codingafro.wordexchange.repositories.staticRepositories.StaticLanguageRepository;
import com.codingafro.wordexchange.services.StaticWordService;
import com.codingafro.wordexchange.services.WordService;
import com.codingafro.wordexchange.utility.sortcomparer.LanguageSortComparer;

import java.util.ArrayList;
import java.util.Collections;


public class HomeActivity extends ActionBarActivity {

    private ArrayList<Language> languages;
    private WordService wordService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.wordService = new StaticWordService();

        this.populateLanguageSpinners();
        this.configureSearchButton();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateLanguageSpinners() {
        LanguageRepository languageRepository = new StaticLanguageRepository();

        this.languages =  languageRepository.getAllLanguages();
        Collections.sort(this.languages, new LanguageSortComparer());

        ArrayList<String> languageNames = new ArrayList<>();
        for (Language language : this.languages) {
            languageNames.add(language.getName());
        }

        languageNames.add("");

        Spinner sourceLanguageSpinner = (Spinner)this.findViewById(R.id.sourceLanguageSpinner);
        Spinner targetLanguageSpinner = (Spinner)this.findViewById(R.id.targetLanguageSpinner);

        ArrayAdapter<String> sourceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languageNames);
        sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sourceLanguageSpinner.setAdapter(sourceAdapter);
        targetLanguageSpinner.setAdapter(sourceAdapter);

        int indexOfEmptyItem = languageNames.size() - 1;
        sourceLanguageSpinner.setSelection(indexOfEmptyItem);
        targetLanguageSpinner.setSelection(indexOfEmptyItem);
    }

    private void configureSearchButton() {
        Button searchButton = (Button) this.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                searchForWord();

            }
        });
    }

    private void searchForWord()
    {
        if (this.isSearchValid()) {
            String targetWord = this.findTranslationForCurrentWord();
            if (targetWord != null) {
                TextView targetWordTextView = (TextView)this.findViewById(R.id.targetWordTextView);
                targetWordTextView.setVisibility(View.VISIBLE);
                targetWordTextView.setText(targetWord);
            }
        }
    }

    private boolean isSearchValid()
    {
        EditText sourceWordTextView = (EditText)this.findViewById(R.id.sourceWordText);
        Spinner sourceLanguageSpinner = (Spinner)this.findViewById(R.id.sourceLanguageSpinner);
        Spinner targetLanguageSpinner = (Spinner)this.findViewById(R.id.targetLanguageSpinner);

        boolean sourceWordAvailable = sourceWordTextView.getText().toString().trim().length() > 0;
        boolean sourceLanguageSelected = sourceLanguageSpinner.getSelectedItemPosition() < this.languages.size();
        boolean targetLanguageSelected = targetLanguageSpinner.getSelectedItemPosition() < this.languages.size();

        return sourceWordAvailable && sourceLanguageSelected && targetLanguageSelected;
    }

    private String findTranslationForCurrentWord()
    {
        EditText sourceWordTextView = (EditText)this.findViewById(R.id.sourceWordText);
        String sourceWord = sourceWordTextView.getText().toString();

        Spinner sourceLanguageSpinner = (Spinner)this.findViewById(R.id.sourceLanguageSpinner);
        Language sourceLanguage = this.languages.get(sourceLanguageSpinner.getSelectedItemPosition());

        Spinner targetLanguageSpinner = (Spinner)this.findViewById(R.id.targetLanguageSpinner);
        Language destinationLanguage = this.languages.get(targetLanguageSpinner.getSelectedItemPosition());

        return this.wordService.getWord(sourceWord, sourceLanguage.getIsoCode(), destinationLanguage.getIsoCode());
    }
}
