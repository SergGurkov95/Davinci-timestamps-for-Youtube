package utils;

public class DataUtil {
    private StringBuilder data;
    private StringBuilder marker;
    private StringBuilder markerList = new StringBuilder();

    public DataUtil(StringBuilder data) {
        this.data = data;
        try {
            this.data.delete(0, data.indexOf("001"));
        }catch (StringIndexOutOfBoundsException e){
            System.out.println("File doesn't contain \"001\" marker or already has been formatted");
        }
        getMarkerEntry();
    }

    public StringBuilder getMarkerList() {
        return markerList;
    }

    //Если кол-во маркеров будет больше 99, здесь возможен баг с литералами
    private void formatMarker(StringBuilder localEntry) {
        localEntry.delete(0, 29);

        marker = new StringBuilder(localEntry.substring(0, 11));

        marker = TimestampUtil.getTimestamp(marker);

        String markerText = localEntry.substring(localEntry.indexOf(" |M:") + 4);
        marker.append("\t").append(markerText);
    }

    private void getMarkerEntry() {
        String markerEntry;
        String pattern = "00";
        for (int i = 1; hasNext(); i++) {
            if (i >= 100) {
                System.out.println("markers with numbers greater than \"100\" need additional implementation");
                break;
            }

            if (i >= 10) {
                pattern = "0";
            }

            markerEntry = new String(data.substring(data.indexOf(pattern + i), data.indexOf(" |D")));
            data.delete(data.indexOf(pattern + i), data.indexOf(" |D"))
                    .delete(data.indexOf(" "), data.indexOf("\n\n") + 2);
            formatMarker(new StringBuilder(markerEntry));
            markerList.append(marker).append("\n");
        }
    }

    private boolean hasNext() {
        return !(data.indexOf("|C:ResolveColor") < 0);
    }
}
