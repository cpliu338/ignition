package ignition.tagdb.model;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.bson.Document;
import rf6.RfStatusTag;
/**
 {
 name: "PumpStatus",
 status: [
 	{property: "Run", pattern: ["DIPS[0-9]SP[0-9]{2}M1"]},
 	{property: "ControlAvail", pattern: ["DIPS[0-9]SP[0-9]{2}C1"]},
 	{property: "Trip", pattern: ["DIPS[0-9]AP[0-9]{2}M1"]},
 	{property: "Stop", pattern: ["DOPS[0-9]CP[0-9]{2}C1"]},
 	{property: "Start", pattern: ["DOPS[0-9]CP[0-9]{2}C2"]}],
 analog: [
 	{property: "Runtime", pattern: ["CIPS[0-9]PRT[0-9]{2}"]}],
 * @author cp_liu
 */
public class Udt_def {
    
    private String name;
    private String parentType;
    private Document doc;
    
    public static Udt_def newInstance() {
        Udt_def i = new Udt_def();
        i.doc = Document.parse(jsonPump);
        i.name = i.doc.getString(NAME);
        i.doc.remove(NAME);
        return i;
    }
    
    private Udt_def() {
        name = "Noname";
        parentType = "";
        doc = new Document();
    }
    
//    private Map<String, String> parameters;
    static private final String NAME = "name";
    static private final String PROPERTY = "property";
    static private final String PATTERN = "pattern";
    
        public List<RfStatusTag> match(String path) throws IOException {
            Pattern pattern = this.findPattern("Status", "Run");
            List<RfStatusTag> raw = RfStatusTag.fromCsv(path);
            return StreamSupport.stream(raw.spliterator(), false)
                .filter(tag -> pattern.matcher(tag.getName()).matches())
                .collect(Collectors.toList()); 
    /*        List<RfStatusTag> result = new ArrayList<>(); 
            for (RfStatusTag t : raw) {
    Logger.getLogger(RfStatusTag.class.getName()).log(Level.INFO, "to match:{0}, pattern: {1}", 
            new Object[]{t.getName(), pattern.toString()});
                if (pattern.matcher(t.getName()).matches())
                    result.add(t);
            }
            return result;*/
        }
    
    public Pattern findPattern(String tagType, String prop) {
        List<Document> docTagType = doc.get(tagType, List.class);
        List<String> pattern = StreamSupport.stream(docTagType.spliterator(), false).filter(
            p -> p.getString(PROPERTY).equals(prop)
            ).map(p -> p.get(PATTERN, List.class))
            .findFirst()
            .orElse(Collections.EMPTY_LIST);
        return Pattern.compile(
            String.join("|", pattern));
    }
    static private String jsonPump = " {\n" +
" name: \"PumpStatus\",\n" +
" Status: [\n" +
" 	{property: \"Run\", pattern: [\".{5}AZP02\",\".{5}AZJ01\"]},\n" +
" 	{property: \"ControlAvail\", pattern: [\"DIPS[0-9]SP[0-9]{2}C1\"]},\n" +
" 	{property: \"Trip\", pattern: [\"DIPS[0-9]AP[0-9]{2}M1\"]},\n" +
" 	{property: \"Stop\", pattern: [\"DOPS[0-9]CP[0-9]{2}C1\"]},\n" +
" 	{property: \"Start\", pattern: [\"DOPS[0-9]CP[0-9]{2}C2\"]}],\n" +
" Meter: [\n" +
" 	{property: \"Runtime\", pattern: [\"CIPS[0-9]PRT[0-9]{2}\"]}]\n" +
"}";
    
    public Tag instantiateTag(String name, String path, Map<String, RfStatusTag> argument) {
        Tag tag = new Tag();
        tag.setName(name);
        tag.setPath(path);
        tag.setType("UDT_INST");
        Parameters params = new Parameters();
        List<Document> status = doc.get("Status", List.class);
        for (Document param : status) {
            String prop = param.getString("property");
            if (argument.containsKey(prop)) {
                Property p = new Property();
                p.setName(param.getString("property"));
                p.setValue(argument.get(prop).getName());
                p.setType("String");
                params.addChild(p);
                argument.remove(prop);
            }
        }
        tag.addChild(params);
        return tag;
    }
    
    public Tag toTag() {
        Tag tag = new Tag();
        tag.setPath("_types_");
        tag.setType("UDT_DEF");
        tag.setName(name);
        Property p2 = new Property();
        p2.setName("DataType");
        p2.setValue("2");
        tag.addChild(p2);
        if (parentType.length()>0) {
            p2 = new Property();
            p2.setName("UDTParentType");
            p2.setValue(parentType);
            tag.addChild(p2);
        }
        List<Document> status = doc.get("Status", List.class);
        Parameters params = new Parameters();
        for (Document param : status) {
            Property p = new Property();
            List<String> patterns = param.get("pattern", List.class);
            p.setName(param.getString("property"));
            p.setValue(String.join("|", patterns));
            p.setType("String");
            params.addChild(p);
        }
        tag.addChild(params);
        return tag;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
}
