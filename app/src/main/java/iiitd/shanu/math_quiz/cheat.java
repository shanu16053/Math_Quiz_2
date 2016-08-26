package iiitd.shanu.math_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class cheat extends AppCompatActivity {
    private TextView mTextView;
    private Button mButtonCheat;
    private String mMessage = "show answer";
    private static final String SAVED_VALUE = " saved_message";
    private  int mNumber;
    private static final String RCV_MSG= "send_message";
    private static final String RESULT = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mNumber = getIntent().getIntExtra(RCV_MSG, -1);
        if (savedInstanceState != null) {
            mMessage = savedInstanceState.getString(SAVED_VALUE);
        }
        mTextView = (TextView) findViewById(R.id.txv_cheat);
        mTextView.setText(mMessage);
    }
    protected void onCheatButtonClick(View v) {
        if(mNumber==0)
            return;
       else {
            Intent intent = new Intent();

            setResult(RESULT_OK, intent);
            if (getIntent().getBooleanExtra(RESULT, false))
                mMessage = mNumber + "  is prime";
            else
                mMessage = mNumber + "  is not prime";
            TextView mTextView = (TextView) findViewById(R.id.txv_cheat);
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
