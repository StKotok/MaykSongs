package com.stkotok.mayksongs.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.stkotok.mayksongs.R;
import com.stkotok.mayksongs.util.SongsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.stkotok.mayksongs.util.SongsService.getBoldText;
import static com.stkotok.mayksongs.util.SongsService.getItalicText;

public class SongActivity extends Activity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        hideActionBar();

        String songNumber = getIntent().getStringExtra("song") + "\n\n";

        textView = (TextView) findViewById(R.id.songText);
        textView.setText(getBoldText(songNumber));
        if (songNumber.startsWith("312")) {
            List<String> textList = new ArrayList<>(Arrays.asList(SongsService.songTextSTUB312().split("(<[ib]>)|(>)")));
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


}
