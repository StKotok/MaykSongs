package com.stkotok.mayksongs;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import static com.stkotok.mayksongs.SongsService.NUMBER_OF_SONGS;

public class FullscreenActivity extends Activity implements TextWatcher {
    String[] item;
    AutoCompleteTextView myAutoComplete;
    List<String> myList;
    ArrayAdapter<String> myAutoCompleteAdapter;

    Button buttonOK;
    TextView autoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        hideActionBar();

        SongsService.getSongs();
        prepareMyList();

        myAutoComplete = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        myAutoComplete.addTextChangedListener(this);
        myAutoComplete.setAdapter(new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, item));

        myAutoComplete.addTextChangedListener(this);

        buttonOK = (Button) findViewById(R.id.btn_ok);
        buttonOK.setOnClickListener(OkOnClickListener);

        autoList = (TextView) findViewById(R.id.tvAutolist);
    }

    private void prepareMyList() {
        // prepare your list of words for AutoComplet myList = new ArrayList<>(NUMBER_OF_SONGS);
        item = new String[NUMBER_OF_SONGS];
        int i = 0;
        for (Song song : SongsService.getSongs()) {
            item[i] = String.valueOf(++i);
            myList.add(String.valueOf(song.getNumber()));
        }
    }

    @Override
    public void afterTextChanged(Editable arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // TODO Auto-generated method stub
    }

    OnClickListener OkOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub

            String newAdd = myAutoComplete.getText().toString();

            if (!myList.contains(newAdd)) {
                myList.add(newAdd);

                // I don't know why simple notifyDataSetChanged()
                // cannot update the autocomplete words
                // myAutoCompleteAdapter.notifyDataSetChanged();

                // update the autocomplete words
                myAutoCompleteAdapter = new ArrayAdapter<String>(
                        FullscreenActivity.this,
                        android.R.layout.simple_dropdown_item_1line, myList);

                myAutoComplete.setAdapter(myAutoCompleteAdapter);
            }

            // display the words in myList for your reference
            String s = "";
            for (int i = 0; i < myList.size(); i++) {
                s += myList.get(i) + "\n";
            }
            autoList.setText(s);
        }
    };

    private void hideActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}
