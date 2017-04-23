package com.cse60333.jschudt.ndbasketball;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import static com.cse60333.jschudt.ndbasketball.R.layout.schedule_item;

/**
 * Created by JoeS on 2/11/2017.
 */

public class ScheduleAdapter extends ArrayAdapter<Team> {
    ScheduleAdapter(Context context, ArrayList<Team> teams){
        super(context, schedule_item, teams);
    };

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        LayoutInflater scheduleInflater = LayoutInflater.from(getContext());
        View scheduleView = scheduleInflater.inflate(schedule_item, parent, false);

        Team matchItem = getItem(position);

        ImageView TeamLogo = (ImageView) scheduleView.findViewById(R.id.AwayTeamLogo);
        String mDrawableName = matchItem.teamLogo;
        int resID = getContext().getResources().getIdentifier(mDrawableName , "mipmap", getContext().getPackageName());
        TeamLogo.setImageResource(resID);


        TextView TeamName = (TextView) scheduleView.findViewById(R.id.TeamName);
        TeamName.setText(matchItem.getTeamName());

        TextView GameDate = (TextView) scheduleView.findViewById(R.id.GameDate);
        GameDate.setText(matchItem.getGameDate());


        return scheduleView;
    }
}