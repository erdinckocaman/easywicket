package com.tamplan.wicket.easywicket;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ImageButton;
import org.apache.wicket.markup.html.form.ListChoice;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.link.Link;

import com.tamplan.wicket.easywicket.wrapper.AjaxButtonWrapper;
import com.tamplan.wicket.easywicket.wrapper.AjaxLinkWrapper;
import com.tamplan.wicket.easywicket.wrapper.AjaxSubmitLinkWrapper;
import com.tamplan.wicket.easywicket.wrapper.ButtonWrapper;
import com.tamplan.wicket.easywicket.wrapper.CheckBoxWrapper;
import com.tamplan.wicket.easywicket.wrapper.CheckGroupWrapper;
import com.tamplan.wicket.easywicket.wrapper.CheckWrapper;
import com.tamplan.wicket.easywicket.wrapper.DataTableWrapper;
import com.tamplan.wicket.easywicket.wrapper.DateTextFieldWrapper;
import com.tamplan.wicket.easywicket.wrapper.DropDownChoiceWrapper;
import com.tamplan.wicket.easywicket.wrapper.FileUploadFieldWrapper;
import com.tamplan.wicket.easywicket.wrapper.FormWrapper;
import com.tamplan.wicket.easywicket.wrapper.ImageButtonWrapper;
import com.tamplan.wicket.easywicket.wrapper.IndicatingAjaxButtonWrapper;
import com.tamplan.wicket.easywicket.wrapper.LabelWrapper;
import com.tamplan.wicket.easywicket.wrapper.LinkWrapper;
import com.tamplan.wicket.easywicket.wrapper.ListChoiceWrapper;
import com.tamplan.wicket.easywicket.wrapper.PasswordTextFieldWrapper;
import com.tamplan.wicket.easywicket.wrapper.RadioChoiceWrapper;
import com.tamplan.wicket.easywicket.wrapper.RadioGroupWrapper;
import com.tamplan.wicket.easywicket.wrapper.RadioWrapper;
import com.tamplan.wicket.easywicket.wrapper.TextAreaWrapper;
import com.tamplan.wicket.easywicket.wrapper.TextFieldWrapper;
import com.tamplan.wicket.easywicket.wrapper.WebMarkupContainerWrapper;

public class EasyWicketFactory {

	private static EasyWicketFactory instance;

	private Map<Class<? extends Component>, Class<? extends IEasyWicketWrapper>> wrapperMap;

	private EasyWicketUtil util;

	private EasyWicketFactory() {
		util = EasyWicketUtil.getInstance();
		wrapperMap = new HashMap<>();

		registerWrapper(Label.class, LabelWrapper.class);
		registerWrapper(Form.class, FormWrapper.class);
		registerWrapper(Button.class, ButtonWrapper.class);
		registerWrapper(TextField.class, TextFieldWrapper.class);
		registerWrapper(PasswordTextField.class, PasswordTextFieldWrapper.class);

		registerWrapper(DataTable.class, DataTableWrapper.class);
		registerWrapper(DefaultDataTable.class, DataTableWrapper.class);

		registerWrapper(DropDownChoice.class, DropDownChoiceWrapper.class);
		registerWrapper(Link.class, LinkWrapper.class);
		registerWrapper(AjaxLink.class, AjaxLinkWrapper.class);
		registerWrapper(CheckBox.class, CheckBoxWrapper.class);
		registerWrapper(RadioGroup.class, RadioGroupWrapper.class);
		registerWrapper(RadioChoice.class, RadioChoiceWrapper.class);
		registerWrapper(Radio.class, RadioWrapper.class);
		registerWrapper(TextArea.class, TextAreaWrapper.class);
		registerWrapper(AjaxButton.class, AjaxButtonWrapper.class);
		registerWrapper(CheckGroup.class, CheckGroupWrapper.class);
		registerWrapper(Check.class, CheckWrapper.class);
		registerWrapper(DateTextField.class, DateTextFieldWrapper.class);
		registerWrapper(ImageButton.class, ImageButtonWrapper.class);
		registerWrapper(ListChoice.class, ListChoiceWrapper.class);

		registerWrapper(WebMarkupContainer.class, WebMarkupContainerWrapper.class);
		registerWrapper(FileUploadField.class, FileUploadFieldWrapper.class);
		registerWrapper(AjaxSubmitLink.class, AjaxSubmitLinkWrapper.class);
		
		registerWrapper(IndicatingAjaxButton.class, IndicatingAjaxButtonWrapper.class);

	}

	public static EasyWicketFactory getInstance() {
		if (instance == null) {
			instance = new EasyWicketFactory();
		}

		return instance;
	}

	public <T extends Component> T createWidget(String widgetId, Class<T> widgetClass, EasyWicket annot,
			MarkupContainer parentWidget) {

		if (IEasyWicket.class.isAssignableFrom(widgetClass)) {
			IEasyWicket wicket = (IEasyWicket) util.createInstance(widgetClass, widgetId);
			wicket.initComponent(annot, parentWidget);

			return (T) wicket;
		} else {

			Class<? extends IEasyWicketWrapper> wrapperFound = wrapperMap.get(widgetClass);

			if (wrapperFound != null) {
				return createWidget(widgetId, widgetClass, annot, wrapperFound, parentWidget);
			} else {
				throw new IllegalStateException("no widget found for widget class=" + widgetClass + " annot=" + annot);
			}
		}
	}

	private <T extends Component> T createWidget(String widgetId, Class<T> widgetClass, EasyWicket annot,
			Class<? extends IEasyWicketWrapper> wrapperClazz, MarkupContainer parentWidget) {
		try {
			IEasyWicketWrapper wrapper = wrapperClazz.newInstance();
			return (T) wrapper.create(widgetId, widgetClass, annot, parentWidget);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void registerWrapper(Class<? extends Component> component, Class<? extends IEasyWicketWrapper> wrapper) {
		wrapperMap.put(component, wrapper);
	}
}
