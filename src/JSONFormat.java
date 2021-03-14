import java.util.Iterator;
import java.util.Map;

public class JSONFormat {

    private final String JSONContent;

    JSONFormat(String JSONContent){
        this.JSONContent = JSONContent;
    }

    public String getJSONContent() {
        return JSONContent;
    }

    public static JSONFormat JSONConvert(Map<String, String> map) {
        String result = "{\n";

        Iterator<Map.Entry<String, String>> itr = map.entrySet().iterator();

        while (itr.hasNext()) {

            Map.Entry<String, String> entry = itr.next();
            result += "\t";
            result += "\"" + entry.getKey() + "\"" + " : " + "\"" + entry.getValue() + "\"";

            if (itr.hasNext()) {
                result += ",\n";
            }

        }
        result += "\n}";

        return new JSONFormat(result);
    }
}
