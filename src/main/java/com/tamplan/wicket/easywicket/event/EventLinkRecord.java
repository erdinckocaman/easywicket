package com.tamplan.wicket.easywicket.event;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Source code is based on event handling code of buoy swing library(buoy.sourceforge.net) 
 * 
 *
 */
public class EventLinkRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	private Class<? extends IEvent<?>> eventClass;
	private ArrayList<Object> targetList;
	private ArrayList<String> targetMethodNameList;
	  
	  
	  /**
	   * Create an EventLinkRecord for storing links for a particular event class.
	   */
	  
	public EventLinkRecord(Class<? extends IEvent<?>> eventType){
		eventClass = eventType;
		targetList = new ArrayList<Object>();
		targetMethodNameList = new ArrayList<String>();
	}
	  
	/**
	 * Get the event class for this record.
	 */
	  
	public Class<?> getEventType(){
		return eventClass;
	}
	  
	public void addLink(Object target, String methodName) {
		targetList.add(target);
		targetMethodNameList.add(methodName);
	}
	  
	/**
	 * Remove an object from the list of targets to be notified of events of this type.
	 *
	 * @param target      the target object to remove
	 */
	  
	public void removeLink(Object target){
		int index = targetList.indexOf(target);
		  
		if (index == -1)
			return;
		  
		targetList.remove(index);
		targetMethodNameList.remove(index);
	}

	/**
	 * Send an event to every target which has been added to this record.
	 */
	  public void dispatchEvent(IEvent<?> event){
		  for (int i = 0; i < targetList.size(); i++){
			  try{				  
				  Object targetObject = targetList.get(i);
				  String targetObjectMethod = targetMethodNameList.get(i);
				  
				  callMethod(targetObject, targetObjectMethod, event);
				  
			  }catch (InvocationTargetException e){
				  throw new RuntimeException(e);
			  }catch (Exception e){
				  throw new RuntimeException(e);
			  }
	    }
	  }

	private void callMethod(Object targetObject, String targetObjectMethod, IEvent<?> event) throws SecurityException, 
																					NoSuchMethodException,
																					IllegalArgumentException,
																					IllegalAccessException,
																					InvocationTargetException {
		
	    Class<?> cls = targetObject.getClass();
	    
	    while (cls != null){
	    	Method m[] = cls.getDeclaredMethods();
	    	
	    	for (int i = 0; i < m.length; i++)
	    		if (m[i].getName().equals(targetObjectMethod)){
	    			Class<?> param[] = m[i].getParameterTypes();
	    			
	    			if (param.length == 0 || (param.length == 1 && param[0].isAssignableFrom(eventClass))){
	    				Method methodFound = m[i];
	    				methodFound.setAccessible(true);
	    				methodFound.invoke(targetObject, event);
	    				return;
	    			}
	    		}
	    	cls = cls.getSuperclass();
	    }
	    
	    
	    throw new NoSuchMethodException(targetObjectMethod);

	}
	
	
}
