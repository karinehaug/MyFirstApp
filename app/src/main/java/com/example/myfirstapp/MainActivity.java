package com.example.myfirstapp;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int pauseCounter;
    private final static String PAUSE_COUNTER = "pauseCounter";
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            pauseCounter = savedInstanceState.getInt(PAUSE_COUNTER);
        }
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            addOldVersionWarning();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseCounter++;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (pauseCounter > 0) {
            TextView textView = (TextView) findViewById(R.id.edit_message);
            textView.setText(text(R.string.Paused) + " " + pauseCounter + " " + text(R.string.times) + ": ");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PAUSE_COUNTER, pauseCounter);
    }

    private String text(int id) {
        return getResources().getString(id);
    }

    private void addOldVersionWarning() {
        String warning = text(R.string.warning_version);
        TextView textView = new TextView(this);
        textView.setText(warning);
        textView.setLayoutParams(new LinearLayoutCompat.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main);
        linearLayout.addView(textView);
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
