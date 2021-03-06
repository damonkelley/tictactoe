package me.damonkelley.tictactoe_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import me.damonkelley.io.converters.SpaceIDConverter;
import me.damonkelley.tictactoe.Board;

public class BoardAdapter extends BaseAdapter {
    private Context context;
    private Board board;

    public BoardAdapter(Context context, Board board) {
        this.context = context;
        this.board = board;
    }

    @Override
    public int getCount() {
        return board.getSize() * board.getSize();
    }

    @Override
    public Object getItem(int i) {
        int size = board.getSize();
        SpaceIDConverter converter = new SpaceIDConverter(size, size);
        return this.board.get(converter.convert(i+1));
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        TextView spaceView;
        if (view == null) {
            spaceView = (TextView) inflater.inflate(R.layout.view_space, null);
        } else {
            spaceView = (TextView) view;
        }

        Object marker = getItem(i);
        if (marker != null) spaceView.setText(marker.toString());

        return spaceView;
    }
}
