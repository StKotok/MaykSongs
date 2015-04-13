package com.stkotok.mayksongs.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.stkotok.mayksongs.R;
import com.stkotok.mayksongs.util.SongsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.stkotok.mayksongs.util.SongsService.getBoldText;
import static com.stkotok.mayksongs.util.SongsService.getItalicText;
import static com.stkotok.mayksongs.util.Utils.PREFERENCES_FILE;
import static com.stkotok.mayksongs.util.Utils.TEXT_SIZE;

public class SongActivity extends Activity {
    private TextView textView;
    private float originalTextSize;
    private float textSize;
    private float textSizeStep = 1.0f;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        hideActionBar();

        if (preferences == null) {
            preferences = this.getSharedPreferences(PREFERENCES_FILE, MODE_PRIVATE);
        }
        textSize = preferences.getFloat(TEXT_SIZE, 16.0f);

        String songNumber = getIntent().getStringExtra("song") + "\n\n";

        textView = (TextView) findViewById(R.id.songText);
        textSize = textView.getTextSize();
        originalTextSize = textSize;
        textView.setTextColor(Color.BLACK);
        String songText;
        if (songNumber.startsWith("312")) {
            songText = SongsService.songTextSTUB312();

        } else {
            songText = SongsService.songTextSTUB();
        }
        final List<String> textList = new ArrayList<>(Arrays.asList(songText.split("(<[ib]>)|(>)")));
        for (String s : textList) {
            SongsService.Formatting formatting = SongsService.Formatting.Default;
            if (s.endsWith("</i")) {
                formatting = SongsService.Formatting.Italic;
            }
            if (s.endsWith("</b")) {
                formatting = SongsService.Formatting.Bold;
            }
            switch (formatting) {
                case Italic:
                    s = s.substring(0, s.length() - 3);
                    textView.append(getItalicText(s));
                    break;
                case Bold:
                    s = s.substring(0, (s.length() - 3));
                    textView.append(getBoldText(s));
                    break;
                case Default:
                    if (s.length() != 0) {
                        textView.append(s);
                    }
                    break;
                default:
                    // Something went wrong
            }
        }

        Button btnPlus = (Button) findViewById(R.id.btnFontSizePlus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnFontSizePlus:
                        textSize = textSize + textSizeStep;
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
//                        Toast.makeText(getBaseContext(), "+", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getBaseContext(), Float.toString(textSize), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        Button btnMinus = (Button) findViewById(R.id.btnFontSizeMinus);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnFontSizeMinus:
                        if (textSize > textSizeStep) {
                            textSize = textSize - textSizeStep;
                            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
//                            Toast.makeText(getBaseContext(), "-", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getBaseContext(), Float.toString(textSize), Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
    }

    private void hideActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        textSizeToPreferences();
    }

    @Override
    protected void onStop() {
        super.onStop();
        textSizeToPreferences();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textSizeToPreferences();
    }

    private void textSizeToPreferences() {
        if (originalTextSize != textSize) {
            SharedPreferences.Editor prefEditor = preferences.edit();
            prefEditor.putFloat(TEXT_SIZE, textSize);
            prefEditor.commit();
        }
    }
}
