package com.okason.prontoquotes.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Valentine on 2/17/2017.
 */

public class FileUtils {

    public static String getDatetimeSuffix(long date){
        String timeStamp = new SimpleDateFormat("yyyy_MMM_dd_HH_mm").format(new Date(date));
        return timeStamp;
    }
}
