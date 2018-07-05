package com.tamplan.wicket.easywicket;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;

public abstract class BaseWrapper implements IEasyWicketWrapper, Serializable {

	private static final long serialVersionUID = 1L;
	protected EasyWicketUtil util;

	public BaseWrapper() {
		util = EasyWicketUtil.getInstance();
	}

	public final Component create(String widgetId, Class<? extends Component> widgetClass, EasyWicket annot,
			MarkupContainer parentWidget) {
		Component component = createInstance(widgetId, widgetClass, annot, parentWidget);

		util.initComponentWithAnnot(component, annot);

		return component;
	}

	protected abstract Component createInstance(String widgetId, Class<? extends Component> widgetClass,
			EasyWicket annot, MarkupContainer parentWidget);

	@SuppressWarnings("unchecked")
	protected List getList(EasyWicket annot, MarkupContainer parentWidget) {
		return (List) util.getContainerValue(parentWidget, annot.list());

	}

	protected int getRowsPerPage(EasyWicket annot, MarkupContainer parentWidget) {
		String rowsPerPage = annot.rowsPerPage();
		if (rowsPerPage == null || rowsPerPage.length() == 0) {
			rowsPerPage = "10";
		}
		if (util.isNumeric(rowsPerPage)) {
			return new Integer(rowsPerPage);
		} else {
			return (Integer) util.getContainerValue(parentWidget, rowsPerPage);
		}
	}

	@SuppressWarnings("unchecked")
	protected List<IColumn> getColumnList(EasyWicket annot, MarkupContainer parentWidget) {
		return (List<IColumn>) util.getContainerValue(parentWidget, annot.columns());
	}

}
