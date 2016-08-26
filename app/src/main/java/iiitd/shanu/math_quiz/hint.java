package iiitd.shanu.math_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class hint extends AppCompatActivity {
    private TextView mTextView;
    private Button mButtonHint;
    private String mMessage = "show hints";
    private static final String SAVED_VALUE = " saved_message";
    private  int mNumber;
    private static final String RCV_MSG= "send_message";
    private static final String RESULT = "result";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        mNumber = getIntent().getIntExtra(RCV_MSG, -1);
        if (savedInstanceState != null) {
            mMessage = savedInstanceState.getString(SAVED_VALUE);
        }
        mTextView = (TextView) findViewById(R.id.txv_hint);
        mTextView.setText(mMessage);
    }

    protected void onHintButtonClick(View v) {
        Intent intent = new Intent();
        if (mNumber == 0)
            return;
        else {
            setResult(RESULT_OK, intent);
            if (getIntent().getBooleanExtra(RESULT, false))
                mMessage = mNumber + "  having no factor";
            else
                mMessage = mNumber + "  having factor";
            TextView mTextView = (TextView) findViewById(R.id.txv_hint);
            mTextView.setText(mMessage);

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle saveState) {
        super.onSaveInstanceState(saveState);
        saveState.putString(SAVED_VALUE, mMessage);
//     saveState.putInt(NUMBER, mNumber);
    }
}