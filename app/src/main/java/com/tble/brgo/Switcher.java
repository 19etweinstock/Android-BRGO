package com.tble.brgo;

import android.support.v4.app.Fragment;

import layout.About;
import layout.Calendar;
import layout.Handbook;
import layout.News;
import layout.Planner;
import layout.StudentID;
import layout.Websites;

/**
 * Created by ethan on 7/28/2017.
 */

public class Switcher {
    /*
       @param schoolCode OnCourse school code for this specific school
     */
    public static String intToString(int schoolCode){
        switch(schoolCode)
        {
            case 14276:
                return ("Middle School");
            case 14274:
                return ("Hillside");
            case 14271:
                return ("Eisenhower");
            case 14278:
                return ("Van Holten");
            case 14277:
                return ("Milltown");
            case 14275:
                return ("J.F.K.");
            case 14272:
                return ("Hamilton");
            case 14269:
                return ("Crim");
            case 14268:
                return ("Bradely Gardens");
            case 14264:
                return ("Adamsville");
            case 14273:
            default:
                return ("High School");
        }
    }

    //
    public static int idToInt(int id){
        switch(id){
            case R.id.S14276:
                return (14276);
            case R.id.S14274:
                return (14274);
            case R.id.S14271:
                return (14271);
            case R.id.S14278:
                return (14278);
            case R.id.S14277:
                return (14277);
            case R.id.S14275:
                return (14275);
            case R.id.S14272:
                return (14272);
            case R.id.S14269:
                return (14269);
            case R.id.S14268:
                return (14268);
            case R.id.S14264:
                return (14264);
            case R.id.S14273:
            default:
                return (14273);
        }
    }

    public static Fragment idToFragment(int id){
        switch (id) {
            case R.id.nav_News:
                return News.newInstance();
            case R.id.nav_Calendar:
                return Calendar.newInstance();
            case R.id.nav_Websites:
                return Websites.newInstance();
            case R.id.nav_About:
                return About.newInstance();
            case R.id.nav_Handbook:
                return Handbook.newInstance();
            case R.id.nav_ID:
                return StudentID.newInstance();
            case R.id.nav_Planner:
                return Planner.newInstance();
            default:
                return News.newInstance();
        }
    }
}
