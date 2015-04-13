package com.stkotok.mayksongs.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.stkotok.mayksongs.R;
import com.stkotok.mayksongs.util.Song;
import com.stkotok.mayksongs.util.SongsService;

import java.util.ArrayList;
import java.util.List;

import static com.stkotok.mayksongs.util.Utils.NUMBER_OF_SONGS;

public class StartActivity extends Activity implements TextWatcher {
    String[] item;
    AutoCompleteTextView myAutoComplete;
    List<String> myList = new ArrayList<>(NUMBER_OF_SONGS);
    ArrayAdapter<String> myAutoCompleteAdapter;

    Button buttonOK;
    TextView autoList;
    OnClickListener OkOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View arg0) {
            String choosedNumber = String.valueOf(myAutoComplete.getText());
            Intent intent = new Intent(getBaseContext(), SongActivity.class);
            intent.putExtra("song", choosedNumber);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        hideActionBar();

        SongsService.getSongs();
        prepareMyList();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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

    private void hideActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}
