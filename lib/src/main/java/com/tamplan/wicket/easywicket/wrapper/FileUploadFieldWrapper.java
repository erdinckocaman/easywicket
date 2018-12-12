package com.tamplan.wicket.easywicket.wrapper;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.upload.FileUploadField;

import com.tamplan.wicket.easywicket.BaseWrapper;
import com.tamplan.wicket.easywicket.EasyWicket;

public class FileUploadFieldWrapper extends BaseWrapper {

	private static final long serialVersionUID = 1L;

	@Override
	protected Component createInstance(String widgetId, Class<? extends Component> widgetClass, EasyWicket annot,
			MarkupContainer parentWidget) {
		
		return new FileUploadField(widgetId) {
		 
			private static final long serialVersionUID = 1L;

			@Override
			protected void onConfigure() {
				super.onConfigure();
				
				util.configureComponent(this, annot.visible(), annot.enabled());
			}
			
		};
	}
	
	

}
