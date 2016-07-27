package me.damonkelley.tictactoe_app.rule;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import me.damonkelley.tictactoe.Marker;
import me.damonkelley.tictactoe_app.activity.GameActivity;

public class GameActivityTestRule extends ActivityTestRule<GameActivity> {
    public GameActivityTestRule() {
        super(GameActivity.class);
    }

    @Override
    protected Intent getActivityIntent() {
        return new Intent()
                .putExtra("preset", "human-vs-human")
                .putExtra("first-marker", Marker.X);
    }
}
