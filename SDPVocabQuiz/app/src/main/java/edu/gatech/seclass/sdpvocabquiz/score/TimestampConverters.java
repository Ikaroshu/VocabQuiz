package edu.gatech.seclass.sdpvocabquiz.score;

import android.arch.persistence.room.TypeConverter;

import java.sql.Timestamp;

public class TimestampConverters {
    @TypeConverter
    public static Timestamp fromTimestamp(Long value) {
        return value == null ? null : new Timestamp(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.getTime();
    }
}
