package utils;

import java.io.File;

public class FileUtil {
    private File file;

    public FileUtil(String path) {
        file = new File(path);
        renameFile();
    }

    public File getFile() {
        return file;
    }

    private void renameFile(){
        StringBuilder newName = new StringBuilder(file.getAbsolutePath());
        newName.delete(newName.lastIndexOf(".") + 1, newName.length());
        newName.append("txt");
        file.renameTo(new File(newName.toString()));
        file = new File(newName.toString());
    }
}
