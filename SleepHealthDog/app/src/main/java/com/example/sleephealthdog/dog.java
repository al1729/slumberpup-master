package com.example.sleephealthdog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class dog extends AppCompatActivity {
    Button signOut;
    GoogleSignInClient mGoogleSignInClient;
    private int numPic;
    //Andrew's stuff
    private int theDogScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);
        //google stuff
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        //signOut button being clickable
        signOut = findViewById(R.id.signoutButton);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    // ...
                    case R.id.signoutButton:
                        signOut();
                        break;
                    // ...
                }
            }
        });
        //retreive google info
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

        }

        //Andrew's setup code
        //ImageView sun2 = (ImageView) findViewById(R.id.sun);
        //doArc(sun2);
        configureNextButton();
        //configureHistory();
        theDogScore = (int) (1.0 + Math.random() * 7); //generates a random integer between 1 and 7
        TextView score = (TextView) findViewById(R.id.textView3);
        score.setText("Sleep Score: " + theDogScore + "");
        showDog();
    }

    /*private void doArc(ImageView sun1){
       // arcTo() and PathInterpolator only available on API 21+
       Path path = new Path();
       path.arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true);
       PathInterpolator pathInterpolator = new PathInterpolator(path);

       ObjectAnimator animation = ObjectAnimator.ofFloat(sun1, "translationX", 100f);
       animation.setInterpolator(pathInterpolator);
       animation.start();
   }*/
    private void showDog(){
        ImageView doggo = (ImageView) findViewById(R.id.dogMood);
        TextView mood = (TextView) findViewById(R.id.mood);
        switch (theDogScore) {
            case 1:
                //activity with dog picture
                doggo.setImageResource(R.drawable.puppysleep);
                mood.setText("Dizzy");
                break;
            case 2:
                doggo.setImageResource(R.drawable.puppyyawn);
                mood.setText("Under-Rested");
                break;
            case 3:
                doggo.setImageResource((R.drawable.puppydownstraight));
                mood.setText("Neutral");
                break;
            case 4:
                doggo.setImageResource(R.drawable.puppyfood);
                mood.setText("Mellow");
                break;
            case 5:
                doggo.setImageResource(R.drawable.puppystraight);
                mood.setText("Happy");
                break;
            case 6:
                doggo.setImageResource(R.drawable.puppysurprised);
                mood.setText("Cheerful");
                break;
            case 7:
                doggo.setImageResource(R.drawable.puppytoy);
                mood.setText("Excited");
                break;
            default:
                //activty with another dog picture
                doggo.setImageResource(R.drawable.puppytoy);
        }
    }

    private void configureNextButton(){

        Button graphButton = findViewById(R.id.progress);
        //get data and put in intent
        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dog.this, GraphActivity.class);
                //get data!!!
                startActivity(i);
            }
        });
    }
    //to implement
    /*private void configureHistory(){
        Button backButton = (Button) findViewById(R.id.history);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainRealActivity.this, [New_Class]);
                i.putExtra(somethin);
                startActivity(i);
            }
        });
    }*/
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(dog.this,"Signed out successfully!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

    }


