import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

class FakeWriter extends BufferedWriter {

    private StringBuffer out = new StringBuffer();

    public FakeWriter() {
        super(new Writer() {
            @Override
            public void write(char[] cbuf, int off, int len) throws IOException {}

            @Override
            public void flush() throws IOException {}

            @Override
            public void close() throws IOException {}
        });
    }

    @Override
    public void write(String str) throws IOException {
        out.append(str);
    }

    public String getOutput() {
        return out.toString();
    }
}
