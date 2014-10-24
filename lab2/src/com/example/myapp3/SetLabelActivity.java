package com.example.myapp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SetLabelActivity  extends Activity{
/*
    EditText myEditText;
    String inputText;
    myEditText.addTextChangedListener(this);
    myEditText = (EditText) findViewById(R.id.name_editText);
    @Override
*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.set_label_activity);

        findViewById(R.id.ok_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputText = ((EditText) findViewById(R.id.name_editText)).getText().toString();
                Intent resultIntent = new Intent();
                if (inputText.isEmpty()) {
                    setResult(Activity.RESULT_CANCELED);
                } else {
                    resultIntent.putExtra("inputText", inputText);
                    setResult(RESULT_OK, resultIntent);
                }
                finish();
            }

        });
    }
}
/*
    Intent resultIntent = new Intent();

    @Override
    public void afterTextChanged(Editable s) {
        if (inputText.length() < s.toString().length()) {
            Toast.makeText(this, ("Text Added: " + s.toString().substring(inputText.length(),
                    s.toString().length())), Toast.LENGTH_SHORT).show();
            resultIntent.putExtra("inputText", inputText);
            setResult(RESULT_OK, resultIntent);

        }
        else{
            Toast.makeText(this, ("Text Removed: " + inputText.substring(s.toString().length(),
                    inputText.length())), Toast.LENGTH_SHORT).show();
            setResult(Activity.RESULT_CANCELED);

        }
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {
        inputText = s.toString();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
*/