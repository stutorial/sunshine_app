package com.example.android.sunshine_app.app.data;

import android.provider.BaseColumns;

/**
 * Define table and columns for the weather database.
 */
public class WeatherContract {

    /* Inner class that defines the table contents of the weather table */
    public static final class WeatherEntry implements BaseColumns {
        public static final String TABLE_NAME = "weather";
        public static final String COLUMN_LOC_KEY = "location_id"; // FK
        public static final String COLUMN_DATETEXT = "date"; // text with format yyyy-MM-dd
        public static final String COLUMN_WEATHER_ID = "weather_id"; // int
        public static final String COLUMN_SHORT_DESC = "short_desc"; // text
        public static final String COLUMN_MIN_TEMP = "min"; // float
        public static final String COLUMN_MAX_TEMP = "max"; // float
        public static final String COLUMN_HUMIDITY = "humidity"; // float
        public static final String COLUMN_PRESSURE = "pressure"; // float
        public static final String COLUMN_WIND_SPEED = "wind"; // float
        public static final String COLUMN_DEGREES = "degrees"; // float
    }

    /* Inner class that defines the table contents of the location table */
    public static final class LocationEntry implements BaseColumns {
        public static final String TABLE_NAME = "location";
        public static final String COLUMN_LOCATION_SETTING = "location_setting"; // int
        public static final String COLUMN_CITY_NAME = "city_name"; // text
        public static final String COLUMN_COORD_LAT = "coord_lat"; // float
        public static final String COLUMN_COORD_LONG = "coord_long"; // float
    }
}
