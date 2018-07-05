package com.tamplan.wicket.easywicket.wrapper;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.util.string.Strings;

import com.tamplan.wicket.easywicket.BaseWrapper;
import com.tamplan.wicket.easywicket.EasyWicket;
import com.tamplan.wicket.easywicket.EasyWicketUtil;
import com.tamplan.wicket.easywicket.IEasyWicketContainer;

public class DataTableWrapper extends BaseWrapper {
	
	private static final long serialVersionUID = 1L;
	private EasyWicketUtil util;
	
	public DataTableWrapper() {
		util = EasyWicketUtil.getInstance();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Component createInstance(String widgetId, Class<? extends Component> widgetClass,
			final EasyWicket annot, final MarkupContainer parentWidget) {

		
		DefaultDataTable dataTable = new DefaultDataTable(widgetId, 
				getColumnList(annot, parentWidget), 
				new SortableDataProvider() {

			public Iterator iterator(int first, int count) {
				List list = getList(annot, parentWidget);
				return list.subList(first, first + count).iterator();
			}

			public IModel model(final Object object) {
				return new LoadableDetachableModel() {

					@Override
					protected Object load() {
						return object;
					}
					
				};
			}

			public int size() {
				List list = getList(annot, parentWidget);
				if ( list == null ) {
					return 0;
				}else {
					return list.size();
				}
			}
			
		}, getRowsPerPage(annot, parentWidget)) {
			@Override
			public boolean isVisible() {
				if ( !Strings.isEmpty(annot.visible())) {
					String visibleStr = annot.visible();
					IEasyWicketContainer container = util.findContainer(this);
					return (Boolean) util.getValue(container, visibleStr);
				}else {
					return super.isVisible();
				}
			}
		};
		
		return dataTable;
	}
	


}
