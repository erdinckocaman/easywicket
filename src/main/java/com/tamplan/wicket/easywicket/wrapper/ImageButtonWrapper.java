package com.tamplan.wicket.easywicket.wrapper;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.ImageButton;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;

import com.tamplan.wicket.easywicket.BaseWrapper;
import com.tamplan.wicket.easywicket.EasyWicket;

public class ImageButtonWrapper extends BaseWrapper {

	private static final long serialVersionUID = 1L;

	@Override
	protected Component createInstance(String widgetId,
			Class<? extends Component> widgetClass, final EasyWicket annot,
			MarkupContainer parentWidget) {
		
		ImageButton btn = new ImageButton(widgetId,  new Model<String>("")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				if ( !Strings.isEmpty(annot.action())) {
					util.callAction(util.findContainer(this), annot.action());
				}
				
			}
			
			
		};
		
		
		return btn;
		
	}

}
