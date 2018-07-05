package com.tamplan.wicket.easywicket;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.wicket.Component;
import org.apache.wicket.core.util.lang.PropertyResolver;
import org.apache.wicket.markup.html.form.FormComponent;

public class EasyWicketUtil implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static EasyWicketUtil instance;
	
	private EasyWicketUtil() {
		
	}
	
	public static EasyWicketUtil getInstance() {
		if ( instance == null ) {
			instance = new EasyWicketUtil();
		}
		
		return instance;
	}

	public  <T extends Component> T createInstance(Class<T> clazz, String id) {
		try {
			Constructor<? extends Component> constructor = clazz.getConstructor(String.class);
			return (T) constructor.newInstance(id);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean hasGetterProperty(Object object, String expression) {
		Field f = PropertyResolver.getPropertyField(expression, object);
		if ( f != null ) {
			return true;
		}
		
		Method m = PropertyResolver.getPropertyGetter(expression, object);
		if ( m != null ) {
			return true;
		}
		
		return false;
	}
	
	public <T> T getValue(Object object, String expression) {
		T value;
		
		Field f = PropertyResolver.getPropertyField(expression, object);
		if ( f != null ) {
			try {
				value =  (T)f.get(object);
				return value;
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
		}
		Method m = PropertyResolver.getPropertyGetter(expression, object);
		
		if ( m != null ) {
			try {
				value =  (T) m.invoke(object, (Object[])null);
				return value;
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			}
		}
		
		throw new IllegalStateException("no value found for object=" + object + " expression=" + expression);
	}
	
	public Object getContainerValue(Component component, String expression) {
		if ( component instanceof IEasyWicketContainer) {
			return getValue(component, expression);
		}else {
			IEasyWicketContainer parentContainer = findContainer(component);
			
			if ( parentContainer == null ) {
				throw new IllegalStateException("No parent container found for component=" + component);
			}
			
			return getValue(parentContainer, expression);
		}
	}

	
	public void callAction(Object object, String actionName) {
		try {
			
			Method m = object.getClass().getDeclaredMethod(actionName, (Class<?>[])null);
			m.setAccessible(true);
			m.invoke(object,(Object[]) null);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	
	public IEasyWicketContainer findContainer(Component component) {
		return component.findParent(IEasyWicketContainer.class);
	}
	
    public boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
    
    public void initComponentWithAnnot(Component component, EasyWicket annot) {
		if ( component instanceof FormComponent ) {
			FormComponent<?> formComponent = (FormComponent<?>) component;
			setRequired(formComponent, annot.required());
		}
		
		setModel(component, annot);
		
		setAjaxEnabled(component, annot.ajaxEnabled());
    }
    
	private void setAjaxEnabled(Component component, boolean ajaxEnabled) {
		component.setOutputMarkupId(true);
		component.setOutputMarkupPlaceholderTag(true);
	}

	private void setModel(Component component, EasyWicket annot) {
		component.setDefaultModel(new EasyWicketModel(component, annot.value()));
	}

	private void setRequired(FormComponent<?> component, boolean required) {
		if ( required ) {
			component.setRequired(required);
		}
	}
	
	public void configureComponent(Component component, String isVisibleStr, 
			String isEnabledStr) {
		
		if ( isVisibleStr != null && isVisibleStr.length() > 0 ) {
			setVisibility(component, isVisibleStr);
		}
		
		if ( isEnabledStr != null && isEnabledStr.length() > 0 ) {
			setEnable(component, isEnabledStr);
		}
	}

	private void setEnable(Component component, String isEnabledStr) {
		IEasyWicketContainer container = findContainer(component);
		Boolean enabled = (Boolean) getValue(container, isEnabledStr);
		component.setEnabled(enabled);
	}

	private void setVisibility(Component component, String isVisibleStr) {
		IEasyWicketContainer container = findContainer(component);
		Boolean visible = (Boolean) getValue(container, isVisibleStr);
		component.setVisible(visible);
	}
}
