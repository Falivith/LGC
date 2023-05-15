import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    BufferedReader arquivo;

    Reader(String a) throws Exception{
        this.arquivo = new BufferedReader(new FileReader(a));
    }

    String getNextToken() throws IOException {
        int eof = -1;
        String buffer = arquivo.readLine();
        return buffer;
    }
}
