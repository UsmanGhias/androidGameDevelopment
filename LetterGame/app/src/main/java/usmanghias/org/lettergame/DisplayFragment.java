import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public class DisplayFragment extends Fragment {

    private List<TestResult> testResultList;
    private TestResultAdapter testResultAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize the list and adapter
        testResultList = new ArrayList<>();
        testResultAdapter = new TestResultAdapter(requireContext(), testResultList);
        databaseHelper = new DatabaseHelper(requireContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        ListView listView = view.findViewById(R.id.listview);
        listView.setAdapter(testResultAdapter);

        // Retrieve data from the database
        retrieveData();

        return view;
    }

    private void retrieveData() {
        // Clear the list
        testResultList.clear();

        // Retrieve data from the database
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_RESULTS, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Extract the data from the cursor
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                String questions = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_QUESTIONS));
                String selections = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SELECTIONS));
                String correctAnswers = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CORRECT_ANSWERS));
                int score = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_SCORE));

                // Create a TestResult object
                TestResult testResult = new TestResult(id, questions, selections, correctAnswers, score);

                // Add the TestResult object to the list
                testResultList.add(testResult);

            } while (cursor.moveToNext());
        }

        // Close the cursor and database connection
        if (cursor != null) {
            cursor.close();
        }
        db.close();

        // Notify the adapter that the data has changed
        testResultAdapter.notifyDataSetChanged();
    }
}

