package me.damonkelley.fake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class FakeReader extends BufferedReader {

    private ArrayList<String> lines = new ArrayList<>();

    public FakeReader() {
        super(new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {return 0;}

            @Override
            public void close() throws IOException {}
        });
    }

    public FakeReader addLine(String line) {
        lines.add(line);
        return this;
    }

    @Override
    public String readLine() throws IOException {
        return lines.remove(0);
    }
}
