import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.Random;

public class MyFragment extends Fragment {

    private TextView letterTextView, answerTextView;
    private char[] skyLetters = {'b', 'd', 'f', 'h', 'k', 'l', 't'};
    private char[] grassLetters = {'g', 'j', 'p', 'q', 'y'};
    private char[] rootLetters = {'a', 'c', 'e', 'i', 'm', 'n', 'o', 'r', 's', 'u', 'v', 'w', 'x', 'z'};
    private String answerString = "";

    private int questionCount = 0;
    private String[] questions = new String[5];
    private String[] selections = new String[5];
    private String[] correctAnswers = new String[5];

    private int score = 0;

    private DatabaseHelper databaseHelper;

    public MyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        letterTextView = view.findViewById(R.id.letter_text_view);
        answerTextView = view.findViewById(R.id.answer_text_view);

        Button skyButton = view.findViewById(R.id.sky_button);
        skyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswer("Sky");
            }
        });

        Button grassButton = view.findViewById(R.id.grass_button);
        grassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswer("Grass");
            }
        });

        Button rootButton = view.findViewById(R.id.root_button);
        rootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswer("Root");
            }
        });

        databaseHelper = new DatabaseHelper(requireActivity());

        return view;
    }

    private void handleAnswer(String selectedButton) {
        try {
            String modifiedAnswer = answerString.replaceAll("Letter", "");

            if (selectedButton.equals(modifiedAnswer)) {
                answerTextView.setText("Awesome, your answer is correct");
                correctAnswers[questionCount] = modifiedAnswer;
                score++;
            } else {
                answerTextView.setText("Incorrect! The answer is " + answerString);
                correctAnswers[questionCount] = modifiedAnswer;
            }

            selections[questionCount] = selectedButton;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    proceedToNextQuestionWithDelay();
                }
            }, 2000);
        } catch (Exception e) {
            Toast.makeText(requireActivity(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void proceedToNextQuestionWithDelay() {
        if (questionCount < 5) {
            questions[questionCount] = letterTextView.getText().toString(); // Save the current question
            questionCount++;
            answerTextView.setText("");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    proceedToNextQuestion();
                }
            }, 2000);
        } else {
            saveTestResult();
        }
    }

    private void proceedToNextQuestion() {
        if (questionCount < 5) {
            letterTextView.setText(getRandomLetter());
        } else {
            letterTextView.setText(" ");
            saveTestResult();
        }
    }

    private void saveTestResult() {
        try {
            // Concatenate the questions array into a single string
            String questionsString = Arrays.toString(questions);

            // Remove the brackets from the string
            questionsString = questionsString.substring(1, questionsString.length() - 1);

            // Concatenate the selections array into a single string
            String selectionsString = Arrays.toString(selections);

            // Remove the brackets from the string
            selectionsString = selectionsString.substring(1, selectionsString.length() - 1);

            // Concatenate the correctAnswers array into a single string
            String correctAnswersString = Arrays.toString(correctAnswers);

            // Remove the brackets from the string
            correctAnswersString = correctAnswersString.substring(1, correctAnswersString.length() - 1);

            databaseHelper.addTestResult(questionsString, selectionsString, correctAnswersString, score);

            questionCount = 0;
            score = 0;

            Toast.makeText(requireActivity(), "Test Completed! Results saved in the database.", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    proceedToNextQuestion();
                }
            }, 2000);
        } catch (Exception e) {
            Toast.makeText(requireActivity(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private String getRandomLetter() {
        try {
            Random random = new Random();
            int category = random.nextInt(3);
            char letter;
            switch (category) {
                case 0:
                    letter = skyLetters[random.nextInt(skyLetters.length)];
                    answerString = "Sky Letter";
                    break;
                case 1:
                    letter = grassLetters[random.nextInt(grassLetters.length)];
                    answerString = "Grass Letter";
                    break;
                default:
                    letter = rootLetters[random.nextInt(rootLetters.length)];
                    answerString = "Root Letter";
                    break;
            }
            return String.valueOf(letter);
        } catch (Exception e) {
            Toast.makeText(requireActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return null;
    }
}
