//package ru.vasilev.piri_processor;
//
//import com.google.common.collect.ImmutableSet;
//import com.squareup.javapoet.ClassName;
//import com.squareup.javapoet.FieldSpec;
//import com.squareup.javapoet.JavaFile;
//import com.squareup.javapoet.MethodSpec;
//import com.squareup.javapoet.TypeSpec;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.annotation.processing.AbstractProcessor;
//import javax.annotation.processing.Filer;
//import javax.annotation.processing.Messager;
//import javax.annotation.processing.ProcessingEnvironment;
//import javax.annotation.processing.RoundEnvironment;
//import javax.lang.model.SourceVersion;
//import javax.lang.model.element.Element;
//import javax.lang.model.element.ElementKind;
//import javax.lang.model.element.Modifier;
//import javax.lang.model.element.TypeElement;
//import javax.lang.model.util.Elements;
//import javax.tools.Diagnostic;
//
//import ru.vasilev.labs.utils.converter.annotations.StoreUnits;
//import ru.vasilev.labs.utils.converter.annotations.UnitClass;
//
//public class NewIntentProcessor extends AbstractProcessor {
//
//    private static final String METHOD_PREFIX = "getAllUnits";
//
//    private Filer filer;
//    private Messager messager;
//    private Elements elements;
//    private Map<String, String> enumsAnnotatedByUnitClass;
//
//    @Override
//    public synchronized void init(ProcessingEnvironment processingEnvironment) {
//        super.init(processingEnvironment);
//        filer = processingEnvironment.getFiler();
//        messager = processingEnvironment.getMessager();
//        elements = processingEnvironment.getElementUtils();
//        enumsAnnotatedByUnitClass = new HashMap<>();
//    }
//
//    @Override
//    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
//
//        try {
//            /**
//             * 1- Find all annotated element
//             */
//            for (Element element : roundEnvironment.getElementsAnnotatedWith(UnitClass.class)) {
//
//                if (element.getKind() != ElementKind.ENUM) {
//                    messager.printMessage(Diagnostic.Kind.ERROR, "Can be applied to class.");
//                    return true;
//                }
//
//                TypeElement typeElement = (TypeElement) element;
//                enumsAnnotatedByUnitClass.put(
//                        typeElement.getSimpleName().toString(),
//                        elements.getPackageOf(typeElement).getQualifiedName().toString());
//            }
//
//            Set<? extends Element> storeUnits = roundEnvironment.getElementsAnnotatedWith(StoreUnits.class);
//            if (storeUnits.size() != 1) {
//                messager.printMessage(Diagnostic.Kind.ERROR, "StoreUnits should be a single class");
//            }
//
//
//            /**
//             * 2- Generate a class
//             */
//            TypeSpec.Builder unitsStorage = TypeSpec
//                    .classBuilder("UnitsStorage")
//                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL);
//
//            List<Object> units = new ArrayList<>();
//            for (Map.Entry<String, String> elementEnum : enumsAnnotatedByUnitClass.entrySet()) {
//                String activityName = elementEnum.getKey();
//                String packageName = elementEnum.getValue();
//                units.addAll(Collections.singletonList(Class.forName(packageName + activityName).getEnumConstants()));
//            }
//
//            ClassName list = ClassName.get("java.util", "List");
//            MethodSpec intentMethod = MethodSpec
//                    .methodBuilder(METHOD_PREFIX)
//                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
//                    .returns(list)
//                    .addStatement("return $L", units)
//                    .build();
//            unitsStorage.addMethod(intentMethod);
//
//            /**
//             * 3- Write generated class to a file
//             */
//            JavaFile.builder("com.annotationsample", unitsStorage.build()).build().writeTo(filer);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        return true;
//    }
//
//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        return ImmutableSet.of(UnitClass.class.getCanonicalName());
//    }
//
//    @Override
//    public SourceVersion getSupportedSourceVersion() {
//        return SourceVersion.latestSupported();
//    }
//}