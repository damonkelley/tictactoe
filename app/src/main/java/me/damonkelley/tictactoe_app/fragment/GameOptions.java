package me.damonkelley.tictactoe_app.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import me.damonkelley.tictactoe_app.Loop;
import me.damonkelley.tictactoe_app.R;

public class GameOptions extends Fragment {

    private Spinner playerOneSpinner;
    private Spinner playerTwoSpinner;

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
    }

    private void setupSpinnerFor(Spinner spinner) {
        ArrayAdapter<CharSequence> otherAdapter = ArrayAdapter.createFromResource(
                this.getActivity(),
                R.array.player_type,
                android.R.layout.simple_spinner_item);

        otherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(otherAdapter);
    }

    public int getGameType() {
        String playerOne = (String) playerOneSpinner.getSelectedItem();
        String playerTwo = (String) playerTwoSpinner.getSelectedItem();

        if ("Computer".equals(playerOne) && "Human".equals(playerTwo)) {
            return Loop.LoopBuilder.COMPUTER_VS_HUMAN;
        } else if ("Human".equals(playerOne) && "Computer".equals(playerTwo)) {
            return Loop.LoopBuilder.HUMAN_VS_COMPUTER;
        }
        return Loop.LoopBuilder.HUMAN_VS_HUMAN;
    }
}
