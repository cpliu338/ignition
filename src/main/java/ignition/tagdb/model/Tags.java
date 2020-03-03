package ignition.tagdb.model;
import com.thoughtworks.xstream.annotations.*;
import java.util.*;
/**
 *
 * @author cp_liu
 */
@XStreamAlias("Tags")
public class Tags {
    @XStreamAsAttribute
    private String locale;
    
    @XStreamImplicit
    private List<TagI> tags;
    
    public Tags() {
        tags = new ArrayList<>();
    }
    
    public void addTag(TagI t) {
        tags.add(t);
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * @return the tags
     */
    public List<TagI> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(List<TagI> tags) {
        this.tags = tags;
    }
}
