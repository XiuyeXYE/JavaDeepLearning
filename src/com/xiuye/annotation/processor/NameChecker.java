package com.xiuye.annotation.processor;


import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner6;
import javax.tools.Diagnostic.Kind;

public class NameChecker {

	private final Messager message;

	NameCheckScanner nameCheckScanner = new NameCheckScanner();

	public NameChecker(ProcessingEnvironment processingEnv) {
		this.message = processingEnv.getMessager();
	}

	/**
	 * class interface name : camel,first upper method : camel,first upper field
	 * variable :camel ,first lower const : all upper
	 *
	 * @param e
	 */
	public void checkNames(Element e) {
		this.nameCheckScanner.scan(e);
	}

	private class NameCheckScanner extends ElementScanner6<Void, Void> {

		@Override
		public Void scan(Element e, Void p) {
			return super.scan(e, p);
		}

		@Override
		public Void visitType(TypeElement e, Void p) {
			scan(e.getTypeParameters(), p);
			log("TypeElement: " + e.getSimpleName(), e);
			return super.visitType(e, p);
		}

		@Override
		public Void visitExecutable(ExecutableElement e, Void p) {

			if (e.getKind() == ElementKind.METHOD) {
				log("ExecutableElement: ", e);
				log("ElementKind: " + e.getKind(), e);
				log("SimpleName: " + e.getSimpleName(), e);
				log("EnclosingElement SimpleName: " + e.getEnclosingElement().getSimpleName(), e);

			}

			return super.visitExecutable(e, p);
		}

		@Override
		public Void visitVariable(VariableElement e, Void p) {

			if (e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null
					|| e.getEnclosingElement().getKind() == ElementKind.INTERFACE || e.getKind() == ElementKind.FIELD) {
				log("VariableElement:",e);
				log("SimpleName: "+e.getSimpleName(),e);
				log("kind: "+ e.getKind(),e);
				log("EnclosingElement SimpleName: "+ e.getEnclosingElement().getSimpleName(),e);
				log("EnclosingElement kind: "+ e.getEnclosingElement().getKind(),e);
				log("Modifiers: "+e.getModifiers(),e);
			}

			return super.visitVariable(e, p);
		}

		private <T extends CharSequence> void log(T t, Element e) {
			message.printMessage(Kind.WARNING, t, e);
		}

	}

}
