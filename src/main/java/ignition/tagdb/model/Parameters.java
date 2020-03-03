package ignition.tagdb.model;

import com.thoughtworks.xstream.annotations.*;
import java.util.*;

/**
 * <Property name="Value">true</Property>
 * @author cp_liu
 */
@XStreamAlias("Parameters")
//@XStreamConverter(value=ToAttributedValueConverter.class, strings={"value"})
public class Parameters implements TagI {
    
    public Parameters() {
        properties = new ArrayList<>();
    }
    
    @Override
    public void addChild(TagI p) {
        if (p instanceof Property)
            properties.add(p);
        else
            throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the properties
     */
    public List<TagI> getChildren() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    @Override
    public void setChildren(List<TagI> properties) {
        this.properties.clear();
        for (TagI p : properties) // throw exception if not instanceof Property
            this.addChild(p);
    }
    
    @XStreamImplicit
    private List<TagI> properties;

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPath(String path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setType(String type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValue(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
