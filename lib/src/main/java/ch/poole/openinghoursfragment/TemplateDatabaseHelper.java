package ch.poole.openinghoursfragment;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TemplateDatabaseHelper extends SQLiteOpenHelper {
    private static final String DEBUG_TAG = "TemplateDatabase";
    private static final String DATABASE_NAME = "openinghours_templates";
    private static final int DATABASE_VERSION = 1;

    private final Context context;

    public TemplateDatabaseHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE templates (name TEXT, is_default INTEGER DEFAULT 0, template TEXT DEFAULT '')");
            TemplateDatabase.add(db, context.getString(R.string.weekdays_with_lunch), true,
                    "Mo-Fr 09:00-12:00,13:30-18:30;Sa 09:00-17:00;PH closed");
            TemplateDatabase.add(db, context.getString(R.string.weekdays), false,
                    "Mo-Fr 09:00-18:30;Sa 09:00-17:00;PH closed");
        } catch (SQLException e) {
            Log.w(DEBUG_TAG, "Problem creating database", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(DEBUG_TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);
    }
}
