package com.cse60333.jschudt.ndbasketball;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.setTitle("ND Athletics");
        setSupportActionBar(myToolbar);

        final ArrayList<Team> teams = new ArrayList<Team>();
        MyCsvFileReader file = new MyCsvFileReader(getApplicationContext());
        ArrayList<String[]> rawteamlist = file.readCsvFile(R.raw.schedule);

        Team floridaState = new Team(rawteamlist.get(0));
        Team bostonCollege = new Team(rawteamlist.get(1));
        Team northCarolinaState = new Team(rawteamlist.get(2));
        Team georgiaTech = new Team(rawteamlist.get(3));
        Team bostonCollege2 = new Team(rawteamlist.get(4));
        Team louisVille = new Team(rawteamlist.get(5));
        Team accTournament = new Team(rawteamlist.get(6));
        Team ncaaTournament = new Team(rawteamlist.get(7));

        teams.add(floridaState);
        teams.add(bostonCollege);
        teams.add(northCarolinaState);
        teams.add(georgiaTech);
        teams.add(bostonCollege2);
        teams.add(louisVille);
        teams.add(accTournament);
        teams.add(ncaaTournament);


        ListView ScheduleListView = (ListView) findViewById(R.id.ScheduleListView);

        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getApplicationContext(), teams);

        ScheduleListView.setAdapter(scheduleAdapter);



        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ActivityMain.this, DetailActivity.class);
                intent.putExtra("team", teams.get(position));
                startActivity(intent);
            }
        };
        ScheduleListView.setOnItemClickListener(clickListener);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == R.id.share) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra("android.content.Intent.EXTRA_SUBJECT", "BasketBall Matches");
            shareIntent.putExtra("android.content.Intent.EXTRA_TEXT", gameSchedule());
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        }
        else if (res_id == R.id.sync) {
            final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_main);
             Snackbar snackbar = Snackbar.make(coordinatorLayout, "Sync is not yet implemented", Snackbar.LENGTH_LONG);
             snackbar.setAction("Try Again", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Snackbar.make(coordinatorLayout, "Wait for the next few labs. Thank you for your patience", Snackbar.LENGTH_LONG).show();
            }
            });
             snackbar.show();
        }
        else if (res_id == R.id.settings) {
            registerForContextMenu((View) findViewById(R.id.settings));
        }
        return true;
    }

    public String gameSchedule() {
        StringBuilder schedule_string = new StringBuilder(1000);
        MyCsvFileReader file = new MyCsvFileReader(getApplicationContext());
        ArrayList<String[]> rawteamlist = file.readCsvFile(R.raw.schedule);
        for(int count = 0; count <= 6; count++ ){
            schedule_string.append(rawteamlist.get(count));
        }
        String final_string = schedule_string.toString();
        return final_string;
    }

    @Override

    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.floating_contextual_menu, menu);
    }

    @Override

    public boolean onContextItemSelected(android.view.MenuItem item) {
        int item_id = item.getItemId();
        //if (item_id == R.id.women) {

// to be implemented later
        return false;
    }

    ;


}
