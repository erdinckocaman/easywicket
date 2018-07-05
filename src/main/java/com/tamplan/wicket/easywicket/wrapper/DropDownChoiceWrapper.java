package com.tamplan.wicket.easywicket.wrapper;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

import com.tamplan.wicket.easywicket.BaseWrapper;
import com.tamplan.wicket.easywicket.EasyWicket;
import com.tamplan.wicket.easywicket.EasyWicketModel;
import com.tamplan.wicket.easywicket.IEasyWicketContainer;
import com.tamplan.wicket.easywicket.WidgetContext;

public class DropDownChoiceWrapper extends BaseWrapper {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected Component createInstance(String widgetId,
			Class<? extends Component> widgetClass, final EasyWicket annot,
			MarkupContainer parentWidget) {
		
		final DropDownChoice dd = (DropDownChoice) super.util.createInstance(widgetClass, widgetId);
		
		dd.setChoiceRenderer(new IChoiceRenderer() {

			public Object getDisplayValue(Object object) {
				if ( annot.displayProperty() == null || annot.displayProperty().length() == 0 ) {
					return object;
				}
				
				if ( util.hasGetterProperty(object, annot.displayProperty())) {
					return util.getValue(object, annot.displayProperty());
				}else {
					IEasyWicketContainer container = util.findContainer(dd);
					
					WidgetContext widgetContext = new WidgetContext();
					widgetContext.setObject(object);
					container.setCurrentWidgetContext(widgetContext);
					
					container.setCurrentWidgetContext(widgetContext);
					return util.getValue(container, annot.displayProperty());
				}
			}

			public String getIdValue(Object object, int index) {
				if ( annot.idProperty() == null || annot.idProperty().length() == 0 ) {
					return String.valueOf(index);
				}
				
				if ( util.hasGetterProperty(object, annot.idProperty())) {
					Object val = util.getValue(object, annot.idProperty());
					if ( val == null ) {
						return null;
					}else {
						return val.toString();
					}
				}else {
					IEasyWicketContainer container = util.findContainer(dd);
					
					WidgetContext widgetContext = new WidgetContext();
					widgetContext.setObject(object);
					widgetContext.setIndex(index);
					container.setCurrentWidgetContext(widgetContext);
					
					return util.getValue(container, annot.idProperty());
				}
				
			}
			
		});
		
		dd.setChoices(new EasyWicketModel(dd, annot.list()));
		
		return dd;
	}
	
	

}
