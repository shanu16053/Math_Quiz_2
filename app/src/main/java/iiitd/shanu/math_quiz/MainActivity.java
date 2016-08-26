package iiitd.shanu.math_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE1 = 1;
    public static final int REQUEST_CODE2 = 2;
    private int mNumber=0;
    private TextView mTextView;
    private Button mButtonTrue;
    private Button mButtonFalse;
    private  String mMessage="Are you ready for Quiz press next";
    private final static String SAVED_VALUE="save message ";
    private final static String SEND_MSG="send_message";
    private final static String NUMBER="save number";
    private boolean mResult;
    private final static String RESULT="result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null) {
            mMessage=savedInstanceState.getString(SAVED_VALUE);
            mNumber=savedInstanceState.getInt(NUMBER);
        }
        mTextView=(TextView) findViewById(R.id.textView);
        mTextView.setText(mMessage);
    }
    private boolean isPrime(int n)
    {
        for(int i=2;i<n;i++)
            if(n%i==0) return false;

        return true;
    }
    protected void onNextButtonClick(View v)
    {
        Random r=new Random();
        mNumber =r.nextInt(999)+1;
        mMessage="is "+ mNumber +" prime number?";
        TextView mTextView=(TextView) findViewById(R.id.textView);
        mTextView.setText(mMessage);


    }
    protected void onTrueOrFalseButtonClick(View v)
    {
        if(mNumber!=0) {
            boolean actual_result = isPrime(mNumber);
            boolean user_result = false;
            mButtonTrue = (Button) findViewById((R.id.button));
            mButtonFalse = (Button) findViewById((R.id.button2));

            switch (v.getId()) {
                case R.id.button:
                    user_result = true;
                    break;
                case R.id.button2:
                    user_result = false;
            }
            if (actual_result == user_result)
                Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "InCorrect", Toast.LENGTH_SHORT).show();

        }
    }
    protected void onHintButtonClick(View v)
    {
        if(mNumber!=0){
        Intent intent = new Intent(getApplicationContext(), hint.class);
        intent.putExtra(SEND_MSG,mNumber);
        mResult = isPrime(mNumber);
        intent.putExtra(RESULT , mResult);
        startActivityForResult(intent,  REQUEST_CODE1);}
    }
    protected void onCheatButtonClick(View v)
    {
        if(mNumber!=0){
        Intent intent = new Intent(getApplicationContext(), cheat.class);
        intent.putExtra(SEND_MSG,mNumber);
        mResult = isPrime(mNumber);
        intent.putExtra(RESULT , mResult);
        startActivityForResult(intent,   REQUEST_CODE2);}

    }
    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent data )
    {
         if(requestcode==REQUEST_CODE1)
             if(resultcode==RESULT_OK)
                 Toast.makeText(MainActivity.this, "Hint taken", Toast.LENGTH_SHORT).show();
        if(requestcode==REQUEST_CODE2)
            if(resultcode==RESULT_OK)
                Toast.makeText(MainActivity.this, "cheated", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState (Bundle saveState) {
        super.onSaveInstanceState(saveState);
        saveState.putString(SAVED_VALUE, mMessage);
        saveState.putInt(NUMBER, mNumber);
    }

}
