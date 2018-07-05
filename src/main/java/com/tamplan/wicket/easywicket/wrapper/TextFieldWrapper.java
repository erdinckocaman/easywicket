package com.tamplan.wicket.easywicket.wrapper;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.string.Strings;

import com.tamplan.wicket.easywicket.BaseWrapper;
import com.tamplan.wicket.easywicket.EasyWicket;
import com.tamplan.wicket.easywicket.IEasyWicketContainer;

public class TextFieldWrapper extends BaseWrapper {

	private static final long serialVersionUID = 1L;

	@Override
	protected Component createInstance(String widgetId,
			Class<? extends Component> widgetClass, final EasyWicket annot, MarkupContainer parentWidget) {		
		
		TextField<Void> txt = new TextField<Void>(widgetId) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isEnabled() {
				
				String enabled = annot.enabled();
				
				if ( !Strings.isEmpty(enabled)) {
					IEasyWicketContainer container = util.findContainer(this);
					return (Boolean) util.getValue(container, enabled);
				}
				else {
					return super.isEnabled();
				}
			}
			
//			@Override
//			public void validate() {
//				String validated = annot.validated();
//				boolean val = true;
//				
//				if ( !Strings.isEmpty(validated) ) {
//					IEasyWicketContainer container = util.findContainer(this);
//					val = (Boolean) util.getValue(container, validated);
//				}
//				if ( val ) {
//					super.validate();
//				}
//			}
		};
		
		return txt;
	}

}
