/**
 * Just some example data, for testing the parsing.
 *
 * @author Devment
 */
public class JavaExampleClass extends JavaExampleInterface{
    private int privateAttribute;
    public String publicAttribute;
    protected byte multipleAttribute1, multipleAttribute2;

    public JavaExampleClass () {}

    public Object attribute;

    @Override
    public int getPrivateAttribute() {return privateAttribute;}

    @Override
    public void setPrivateAttribute(){}
}

public interface JavaExampleInterface {
    int getPrivateAttribute();
    void setPrivateAttribute();
}