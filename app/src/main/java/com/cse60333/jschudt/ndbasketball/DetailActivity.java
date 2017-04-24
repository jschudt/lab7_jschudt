package com.cse60333.jschudt.ndbasketball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.cse60333.jschudt.ndbasketball.R.layout.activity_detail;

/**
 * Created by JoeS on 2/16/2017.
 */

public class DetailActivity extends Activity {

    @Override
    public void onCreate (Bundle bundle) {
        super.onCreate(bundle);
        setContentView(activity_detail);
        DataBaseHelper db = new DataBaseHelper(getApplicationContext());

        int teamID = (int) getIntent().getSerializableExtra("team");
        Team awayteam = db.getATeam(teamID);
        Team hometeam = new Team("Notre Dame", "notredame", "(21-5)", "Fighting Irish", "0", "Error", 99);

        ImageView HomeTeamLogo = (ImageView) findViewById(R.id.HomeTeamLogo);
        String mDrawableName = hometeam.getTeamLogo();
        int resID = getResources().getIdentifier(mDrawableName, "mipmap", getPackageName());
        HomeTeamLogo.setImageResource(resID);

        ImageView AwayTeamLogo = (ImageView) findViewById(R.id.AwayTeamLogo);
        String mDrawableName1 = awayteam.getTeamLogo();
        int resID1 = getResources().getIdentifier(mDrawableName1, "mipmap", getPackageName());
        AwayTeamLogo.setImageResource(resID1);

        TextView Score = (TextView) findViewById(R.id.Score);
        Score.setText(awayteam.getTeamScore());

        TextView HomeTeamName = (TextView) findViewById(R.id.HomeTeamName);
        HomeTeamName.setText(hometeam.getTeamName());

        TextView AwayTeamName = (TextView) findViewById(R.id.AwayTeamName);
        AwayTeamName.setText(awayteam.getTeamName());

        TextView HomeTeamMascot = (TextView) findViewById(R.id.HomeTeamMascot);
        HomeTeamMascot.setText(hometeam.getTeamMascot());

        TextView AwayTeamMascot = (TextView) findViewById(R.id.AwayTeamMascot);
        AwayTeamMascot.setText(awayteam.getTeamMascot());

        TextView TipOff = (TextView) findViewById(R.id.GameDate);
        TipOff.setText(awayteam.getGameDate());

        Button Camera= (Button) findViewById(R.id.CameraButton);
        Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View startCamera) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(cameraIntent);
            }
        });


        }





}

