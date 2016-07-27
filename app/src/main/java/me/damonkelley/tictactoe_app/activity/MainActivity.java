package me.damonkelley.tictactoe_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import me.damonkelley.tictactoe_app.R;
import me.damonkelley.tictactoe_app.fragment.GameOptions;

public class MainActivity extends AppCompatActivity {

    private GameOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        options = (GameOptions) this.getFragmentManager().findFragmentById(R.id.options);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);

        intent.putExtra("preset", options.getPreset())
                .putExtra("first-marker", options.getFirstMarker());

        startActivity(intent);
    }
}
