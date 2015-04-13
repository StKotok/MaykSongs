package com.stkotok.mayksongs.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.stkotok.mayksongs.R;
import com.stkotok.mayksongs.util.SongsService;

public class SongActivity extends Activity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        hideActionBar();

        String songNumber = getIntent().getStringExtra("song") + "\n\n";

        textView = (TextView) findViewById(R.id.songText);
        textView.setText(getItalicText(songNumber));
        if (songNumber.startsWith("312")) {
            textView.append(SongsService.songTextSTUB312());
        } else {
            textView.append(SongsService.songTextSTUB());
        }
    }

    private void hideActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    public static Spannable getItalicText(String text) {
        Spannable italic = new SpannableString(text);
        italic.setSpan(new StyleSpan(Typeface.ITALIC), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return italic;
    }
}
