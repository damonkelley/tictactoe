package me.damonkelley.tictactoe_app.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ToggleButton;
import me.damonkelley.tictactoe_app.R;

public class GameOptions extends Fragment {

    private Spinner playerOneSpinner;
    private Spinner playerTwoSpinner;
    private ToggleButton playerOneMarkerToggle;
    private ToggleButton playerTwoMarkerToggle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_options, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        playerOneSpinner = (Spinner) getView().findViewById(R.id.player_one_type);
        playerTwoSpinner = (Spinner) getView().findViewById(R.id.player_two_type);

        setupSpinnerFor(playerOneSpinner);
        setupSpinnerFor(playerTwoSpinner);

        playerOneMarkerToggle = (ToggleButton) getView().findViewById(R.id.player_one_marker);
        playerTwoMarkerToggle = (ToggleButton) getView().findViewById(R.id.player_two_marker);

        playerOneMarkerToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            playerTwoMarkerToggle.setChecked(!buttonView.isChecked());
        });

        playerTwoMarkerToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            playerOneMarkerToggle.setChecked(!buttonView.isChecked());
        });
    }

    private void setupSpinnerFor(Spinner spinner) {
        ArrayAdapter<CharSequence> otherAdapter = ArrayAdapter.createFromResource(
                this.getActivity(),
                R.array.player_type,
                android.R.layout.simple_spinner_item);

        otherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(otherAdapter);
    }

    private String getPlayerOneType() {
        return (String) playerOneSpinner.getSelectedItem();
    }

    private String getPlayerTwoType() {
        return (String) playerTwoSpinner.getSelectedItem();
    }

    public String getPreset() {
        return String.format(
                "%s-vs-%s",
                getPlayerOneType().toLowerCase(),
                getPlayerTwoType().toLowerCase());
    }

    public String getFirstMarker() {
        return (String) playerOneMarkerToggle.getText();
    }
}
