package com.tamplan.wicket.easywicket;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EasyWicket {
	
	public String id();
	public String value() default "";
	public String list() default "";
	public String action() default "";
	
	public boolean required() default false;
	
	public String enabled() default "";
	public String visible() default "";
	public boolean ajaxEnabled() default false;
	
	// Drop down
	public String displayProperty() default "";
	public String idProperty() default "";
}
