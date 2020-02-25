package dk.itu.moapd.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.Objects;

public class MainFragment extends Fragment {

    private static final String STATE = "state";
    private static final String CHECK = "check";

    private TextView mTextView;
    private CheckBox mCheckBox;
    private SharedPreferences mPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mTextView = view.findViewById(R.id.text_view);

        Button trueButton = view.findViewById(R.id.true_button);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your code here.
                mTextView.setText(getString(R.string.selected_true));
                savePreferences();
            }
        });

        Button falseButton = view.findViewById(R.id.false_button);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your code here.
                mTextView.setText(getString(R.string.selected_false));
                savePreferences();
            }
        });

        mCheckBox = view.findViewById(R.id.check_box);
        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = mCheckBox.isChecked() ? "checked" : "unchecked";
                mTextView.setText(String.format(getString(R.string.selected_bool), status));
                savePreferences();
            }
        });

        mPreferences = Objects.requireNonNull(getActivity())
                .getPreferences(Context.MODE_PRIVATE);
        retrievePreferences();

        return view;
    }

    private void retrievePreferences() {
        boolean status = mPreferences.getBoolean(CHECK, false);
        mCheckBox.setChecked(status);

        String text = mPreferences.getString(STATE, "Hello World!");
        mTextView.setText(text);
    }

    private void savePreferences() {
        String text = mTextView.getText().toString();
        boolean status = mCheckBox.isChecked();

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(STATE, text)
              .putBoolean(CHECK, status)
              .apply();
    }

}
