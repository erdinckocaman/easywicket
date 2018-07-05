package net.sourceforge.easywicket.web.event;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

public class ItemSelectedForDeletionEvent<T> extends AbstractItemSelectionEvent<T>{

	
	public ItemSelectedForDeletionEvent(Component source,
			AjaxRequestTarget requestTarget, T targetItem) {
		super(source, requestTarget, targetItem);
	}
	


}
