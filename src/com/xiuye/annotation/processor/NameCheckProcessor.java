package com.xiuye.annotation.processor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;


@SupportedAnnotationTypes("*")//all annotations
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class NameCheckProcessor extends AbstractProcessor {

	private NameChecker nameChecker;

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		this.nameChecker = new NameChecker(processingEnv);
	}

	//对语法树 每个节点进行检查
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

		if(!roundEnv.processingOver()){
			for(Element e : roundEnv.getRootElements()){
				log(e);
			}
		}

		return false;
	}

	public static<T> void log(T t){
		System.out.println(t);
	}
}
