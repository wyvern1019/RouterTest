package com.troila.router_compiler;

import com.google.auto.service.AutoService;
import com.troila.router_annotation.Route;

import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

//自动注册
@AutoService(Processor.class)
//指定处理器
@SupportedAnnotationTypes("com.troila.router_annotation.Route")
public class RouterProcessor extends AbstractProcessor {
    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Route.class);
        for (Element element : elements) {
            messager.printMessage(Diagnostic.Kind.NOTE, "element:"+element.getSimpleName());
            Route route = element.getAnnotation(Route.class);
            String value = route.value();
            generateClass(value, element.getSimpleName().toString());
        }
        return false;
    }

    private void generateClass(String value, String cls) {
        StringBuffer sb = new StringBuffer();
        sb.append("package com.troila.routers;\n" +
                "\n" +
                "import android.app.Activity;\n" +
                "\n" +
                "import com.troila.beauty.MainActivity;\n" +
                "import com.troila.router_api.IRouteLoad;\n" +
                "\n" +
                "import java.util.Map;\n" +
                "\n" +
                "public class FileRegist implements IRouteLoad {\n" +
                "    @Override\n" +
                "    public void onLoad(Map<String, Class<? extends Activity>> routes) {\n" +
                "        routes.put(\""+ value +"\", "+cls+".class);\n" +
                "\n" +
                "    }\n" +
                "}");
        try {
            JavaFileObject sourceFile = filer.createSourceFile("com.troila.routers.FileRegist");
            OutputStream os = sourceFile.openOutputStream();
            os.write(sb.toString().getBytes());
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
