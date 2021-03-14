import java.util.Iterator;
import java.util.Map;

public class JSONFormat {

    private final String JSONContent;

    private JSONFormat(String JSONContent){
        this.JSONContent = JSONContent;
    }

    public String getJSONContent() {
        return JSONContent;
    }

    public static JSONFormat JSONConvert(Map<String, String> map) {
        String result = "{\n";

        Iterator<Map.Entry<String, String>> itr = map.entrySet().iterator();

        if (map.size() == 0)
            return new JSONFormat("");

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
