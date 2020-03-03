package ignition.tagdb.model;

import com.thoughtworks.xstream.annotations.*;
import java.util.*;

/**
 * <Tag name="SingleBitBinaryInput:103" path="Tai Wo Tsuen SWPS/Digital" type="OPC">
      <Property name="Value">true</Property>
      <Property name="DataType">6</Property>
      <Property name="OPCServer">Ignition OPC-UA Server</Property>
      <Property name="OPCItemPath">ns=1;s=[TaiWoTsuenSWPS]SingleBitBinaryInput:103:g1v2i103</Property>
   </Tag>
 * @author cp_liu
 */
@XStreamAlias("Tag")
//@XStreamConverter(value=ToAttributedValueConverter.class, strings={"properties"})
public class Tag implements TagI {
    @XStreamAsAttribute
    private String name;
    @XStreamAsAttribute
    private String path;
    @XStreamAsAttribute
    private String type;
/*
    @XStreamAlias("Parameters")
    private List<Property> parameters;
*/
    @XStreamImplicit
    private List<TagI> children;

    
    public Tag() {
        children = new ArrayList<>();
        //parameters = null;
    } 
    
    @Override
    public void addChild(TagI p) {
        children.add(p);
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the path
     */
    @Override
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    @Override
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the children
     */
    @Override
    public List<TagI> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    @Override
    public void setChildren(List<TagI> children) {
        this.children = children;
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
