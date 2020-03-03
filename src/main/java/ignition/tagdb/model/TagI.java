package ignition.tagdb.model;

import java.util.List;

/**
 *
 * @author cp_liu
 */
public interface TagI {
    public void addChild(TagI t);
    public String getName();
    public void setName(String name);
    public String getPath();
    public void setPath(String path);
    public String getType();
    public void setType(String type);
    public String getValue();
    public void setValue(String value);
    public List<TagI> getChildren();
    public void setChildren(List<TagI> children);
}
