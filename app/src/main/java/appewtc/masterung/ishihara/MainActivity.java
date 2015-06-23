package appewtc.masterung.ishihara;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private TextView questionTextView;
    private ImageView ishiharaImageView;
    private RadioGroup choiceRadioGroup;
    private RadioButton choice1RadioButton, choice2RadioButton,
            choice3RadioButton, choice4RadioButton;
    private Button answerButton;
    private int radioAnInt, indexAnInt;
    private MyModel objMyModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial Widget
        initialWidget();

        //Button Controller
        buttonController();

        //Radio Controller
        radioController();

        //About Model
        aboutModel();

    }   // onCreate

    private void aboutModel() {

        objMyModel = new MyModel();
        objMyModel.setOnMyModelChangeListener(new MyModel.OnMyModelChangeListener() {
            @Override
            public void onMyModelChangeListener(MyModel myModel) {

                //Change View
                changeView(myModel.getModelAnInt());

            }   // event
        });

    }   // aboutModel

    private void changeView(int modelAnInt) {

        //Change Image
        int intDrawable[] = {R.drawable.ishihara_01, R.drawable.ishihara_02,
                R.drawable.ishihara_03, R.drawable.ishihara_04, R.drawable.ishihara_05,
                R.drawable.ishihara_06, R.drawable.ishihara_07, R.drawable.ishihara_08,
                R.drawable.ishihara_09, R.drawable.ishihara_10};
        ishiharaImageView.setImageResource(intDrawable[modelAnInt]);

    }   //changeView

    private void radioController() {

        choiceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                //Sound Effect
                MediaPlayer radioMediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_shut);
                radioMediaPlayer.start();

                //setup radioAnInt
                switch (i) {
                    case R.id.radioButton:
                        radioAnInt = 1;
                        break;
                    case R.id.radioButton2:
                        radioAnInt = 2;
                        break;
                    case R.id.radioButton3:
                        radioAnInt = 3;
                        break;
                    case R.id.radioButton4:
                        radioAnInt = 4;
                        break;
                    default:
                        radioAnInt = 0;
                        break;
                }   //switch

            }   // event
        });

    }   // radioController

    private void buttonController() {

        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Sound Effect
                MediaPlayer buttonMediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_long);
                buttonMediaPlayer.start();

                //Check Zero
                checkZero();

            }   //event
        });

    }   // buttonController

    private void checkZero() {

        if (radioAnInt == 0) {

            //Alert
            Toast.makeText(MainActivity.this, "กรุณาตอบคำถาม ด้วยคะ", Toast.LENGTH_SHORT).show();


        } else {

            //Check Times
            checkTimes();

        }

    }   // checkZero

    private void checkTimes() {

        if (indexAnInt == 9) {

            //Intent to ShowScore
            Intent objIntent = new Intent(MainActivity.this, ShowScoreActivity.class);
            startActivity(objIntent);
            finish();

        } else {

            //Increase indexAnInt
            indexAnInt += 1;

            //Show Controller Call View
            questionTextView.setText(Integer.toString(indexAnInt+1) + ". What is this ?" );

            //Show Controller Call Model
            objMyModel.setModelAnInt(indexAnInt);


        }   // if

    }   //checkTimes

    private void initialWidget() {

        questionTextView = (TextView) findViewById(R.id.textView2);
        ishiharaImageView = (ImageView) findViewById(R.id.imageView);
        choiceRadioGroup = (RadioGroup) findViewById(R.id.ragChoice);
        choice1RadioButton = (RadioButton) findViewById(R.id.radioButton);
        choice2RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        choice3RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        choice4RadioButton = (RadioButton) findViewById(R.id.radioButton4);
        answerButton = (Button) findViewById(R.id.button);

    }   // initialWidget

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class
