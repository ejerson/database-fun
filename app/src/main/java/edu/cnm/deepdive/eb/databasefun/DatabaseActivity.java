package edu.cnm.deepdive.eb.databasefun;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import edu.cnm.deepdive.eb.databasefun.fragments.AddContentFragment;

public class DatabaseActivity extends AppCompatActivity {

  public DatabaseHelper dbHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_database);

    ListView databaseListView = (ListView) findViewById(R.id.database_list);

    dbHelper = new DatabaseHelper(this);

    SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

    Cursor cursor = sqLiteDatabase.query(DatabaseHelper.DATABASE_TABLE,
        new String[]{DatabaseHelper.ID_COLUMN, DatabaseHelper.CONTENT_COLUMN, DatabaseHelper.COLOR_COLUMN},
        null, null, null, null, null);

    ColorCursorAdapter cursorAdapter = new ColorCursorAdapter(this,
        cursor, true);

    databaseListView.setAdapter(cursorAdapter);
    dbHelper.close();

  }

  public void showAddDialog(View view) {
    AddContentFragment dialog = new AddContentFragment();
    dialog.show(getSupportFragmentManager(), "AddContentFragment");
  }
}