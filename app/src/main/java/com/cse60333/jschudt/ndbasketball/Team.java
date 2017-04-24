package com.cse60333.jschudt.ndbasketball;

import java.io.Serializable;

/**
 * Created by JoeS on 3/5/2017.
 */

public class Team implements Serializable {
    String teamName;
    String teamLogo;
    String teamRecord;
    String teamMascot;
    String teamScore;
    String gameDate;
    int teamID;

    public Team(
            String team_name ,
            String team_logo ,
            String team_record ,
            String team_mascot ,
            String team_score ,
            String game_date ,
            int team_id){
        setTeamName(team_name);
        setTeamLogo(team_logo);
        setTeamRecord(team_record);
        setTeamMascot(team_mascot);
        setTeamScore(team_score);
        setGameDate(game_date);
        setTeamID(team_id);
    }

    public void setTeamName(String team_name) {
        this.teamName = team_name;
    }

    public String getTeamName() {
        return this.teamName;
    }
    //********************************************************************************
    public void setTeamLogo(String team_logo) {
        this.teamLogo = team_logo;
    }

    public String getTeamLogo() {
        return this.teamLogo;
    }
    //********************************************************************************
    public void setTeamRecord(String team_record) {
        this.teamRecord = team_record;
    }

    public String getTeamRecord() {
        return this.teamRecord;
    }
    //********************************************************************************
    public void setTeamMascot(String team_mascot) {
        this.teamMascot = team_mascot;
    }

    public String getTeamMascot() {
        return this.teamMascot;
    }
    //********************************************************************************
    public void setTeamScore(String team_score) {
        this.teamScore = team_score;
    }

    public String getTeamScore() {
        return this.teamScore;
    }
    //********************************************************************************
    public void setGameDate(String game_date) {
        this.gameDate = game_date;
    }

    public String getGameDate() {
        return this.gameDate;
    }
    //********************************************************************************
    public void setTeamID(int team_id) {this.teamID = team_id; }

    public int getTeamID() {
        return this.teamID;
    }
}