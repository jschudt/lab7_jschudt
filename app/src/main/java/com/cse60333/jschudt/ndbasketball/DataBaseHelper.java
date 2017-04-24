package com.cse60333.jschudt.ndbasketball;

/**
 * Created by JoeS on 4/22/2017.
 */

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.provider.Settings;

        import java.util.ArrayList;


public class DataBaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "teams.db";
    public static int DATABASE_VERSION = 1;
    public static String TABLE_TEAM = "Team";
    public static String COL_NAME = "team_name";
    public static String COL_LOGO = "team_logo";
    public static String COL_MASCOT = "team_mascot";
    public static String COL_SCORE = "score";
    public static String COL_DATE = "game_date";
    public static String COL_ID = "_id";

    public DataBaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("onCreate SQLiteOpenHelper");
        db.execSQL("CREATE TABLE " + TABLE_TEAM + " ( " +
                COL_NAME + " TEXT, " +
                COL_LOGO + " TEXT, " +
                COL_MASCOT + " TEXT, " +
                COL_SCORE + " TEXT, " +
                COL_DATE + " TEXT, " +
                COL_ID + " INTEGER PRIMARY KEY )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("onUpgrade SQLiteOpenHelper");
        db.execSQL("DROP TABLE if exists " + TABLE_TEAM );
        onCreate(db);
    }

    public void insertData(Team team) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, team.getTeamName());
        contentValues.put(COL_LOGO, team.getTeamLogo());
        contentValues.put(COL_MASCOT, team.getTeamMascot());
        contentValues.put(COL_SCORE, team.getTeamScore());
        contentValues.put(COL_DATE, team.getGameDate());
        contentValues.put(COL_ID, team.getTeamID());
        long ret = db.insert(TABLE_TEAM, null, contentValues );

        if (ret > 0) {
            System.out.println("Successfully inserted");
        } else {
            System.out.println("Insert Unsuccessful");
        }

        db.close();
    }

    public void deleteData(int id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_TEAM, " " + COL_ID + " = ?", new String[]{Integer.toString(id)});
        db.close();
    }


    public ArrayList<Team> getAllTeams() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TEAM, new String[]{});
        ArrayList<Team> teams = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    teams.add(new Team(
                    cursor.getString(cursor.getColumnIndex(COL_NAME)),
                    cursor.getString(cursor.getColumnIndex(COL_LOGO)),
                    "not used",
                    cursor.getString(cursor.getColumnIndex(COL_MASCOT)),
                    cursor.getString(cursor.getColumnIndex(COL_SCORE)),
                    cursor.getString(cursor.getColumnIndex(COL_DATE)),
                    cursor.getInt(cursor.getColumnIndex(COL_ID))
                    ));
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return teams;
    }

    public Team getATeam(int ID) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TEAM + " WHERE "+COL_ID + " = ?", new String[]{Integer.toString(ID)});
        Team selectTeam=null;

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                do {
                    selectTeam = new Team(
                            cursor.getString(cursor.getColumnIndex(COL_NAME)),
                            cursor.getString(cursor.getColumnIndex(COL_LOGO)),
                            "not used",
                            cursor.getString(cursor.getColumnIndex(COL_MASCOT)),
                            cursor.getString(cursor.getColumnIndex(COL_SCORE)),
                            cursor.getString(cursor.getColumnIndex(COL_DATE)),
                            cursor.getInt(cursor.getColumnIndex(COL_ID))
                    );
                }while (cursor.moveToNext());
            }
        }

        cursor.close();
        return selectTeam;
    }

    public String[] getTableFields(String tblName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor dbCursor = db.query(tblName, null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();
        return columnNames;
    }

}
