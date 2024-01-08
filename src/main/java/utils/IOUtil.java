package utils;

import java.io.*;

public class IOUtil {
    private StringBuilder data;

    public IOUtil(File file) {
        try {
            getData(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getData(File file) throws IOException {
        StringBuilder data = new StringBuilder();
        InputStream is = new FileInputStream(file);
        ;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            for (; (line = br.readLine()) != null; ) {
                data.append(line).append("\n");
            }
        }

        this.data = data;
    }

    public void writeData(File file, StringBuilder data) {
        this.data = data;

        try (FileOutputStream fos
                     = new FileOutputStream(file);
             BufferedWriter bw
                     = new BufferedWriter(new OutputStreamWriter(fos))) {
            bw.write(data.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public StringBuilder getData() {
        return data;
    }
}
