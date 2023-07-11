package usmanghias.org.lettergame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TestResultAdapter extends BaseAdapter {
    private Context context;
    private List<TestResult> testResults;

    public TestResultAdapter(Context context, List<TestResult> testResults) {
        this.context = context;
        this.testResults = testResults;
    }

    @Override
    public int getCount() {
        return testResults.size();
    }

    @Override
    public Object getItem(int position) {
        return testResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_result, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.questionsTextView = convertView.findViewById(R.id.questions_text_view);
            viewHolder.selectionsTextView = convertView.findViewById(R.id.selections_text_view);
            viewHolder.correctAnswersTextView = convertView.findViewById(R.id.correct_answers_text_view);
            viewHolder.scoreTextView = convertView.findViewById(R.id.score_text_view);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Get the current TestResult object
        TestResult testResult = testResults.get(position);

        // Set the data to the views
        viewHolder.questionsTextView.setText(testResult.getQuestions());
        viewHolder.selectionsTextView.setText(testResult.getSelections());
        viewHolder.correctAnswersTextView.setText(testResult.getCorrectAnswers());
        viewHolder.scoreTextView.setText(String.valueOf(testResult.getScore()));

        return convertView;
    }

    private static class ViewHolder {
        TextView questionsTextView;
        TextView selectionsTextView;
        TextView correctAnswersTextView;
        TextView scoreTextView;
    }
}

