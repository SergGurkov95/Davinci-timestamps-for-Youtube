import utils.DataUtil;
import utils.FileUtil;
import utils.IOUtil;

public class App {
    public static void main(String[] args) {
        FileUtil fileUtil = new FileUtil(args[0]);
        IOUtil ioUtil = new IOUtil(fileUtil.getFile());
        DataUtil dataUtil = new DataUtil(ioUtil.getData());
        ioUtil.writeData(fileUtil.getFile(), dataUtil.getMarkerList());
    }
}