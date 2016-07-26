package me.damonkelley.tictactoe_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        startActivity(new Intent(this, GameActivity.class));
    }
}
