package in.haeg.list;

public class GenericHTML {

    public static String header(String title) {
        StringBuilder builder = new StringBuilder();

        builder.append("<html>\n");
        builder.append("<head>\n");
        builder.append("<title>" + title + "</title>\n");
        builder.append("<link rel='stylesheet' type='text/css' href='/css/reset.css' />\n");
        builder.append("<link rel='stylesheet' type='text/css' href='/css/list.css' />\n");
        builder.append("</head>\n");
        builder.append("<body>\n");

        return builder.toString();
    }

    public static String footer() {
        StringBuilder builder = new StringBuilder();

        builder.append("</body>\n");
        builder.append("</html>\n");

        return builder.toString();
    }
}
