package com.tamplan.wicket.easywicket.components;

import java.io.Serializable;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.list.LoopItem;

import com.tamplan.wicket.easywicket.EasyWicket;
import com.tamplan.wicket.easywicket.EasyWicketUtil;
import com.tamplan.wicket.easywicket.IEasyWicket;

public class EasyLoop extends Loop implements IEasyWicket{
	
	private static final long serialVersionUID = 1L;
	
	@FunctionalInterface
	public static interface LoopItemHandler extends Serializable {
		void accept(LoopItem t);
	}

	private LoopItemHandler loopItemHandler;

	public EasyLoop(String id) {
		super(id, 0);
	}
	
	public void setLoopItemHandler(LoopItemHandler loopItemHandler) {
		this.loopItemHandler = loopItemHandler;
	}

	@Override
	protected void populateItem(LoopItem item) {
		if ( loopItemHandler == null ) {
			throw new IllegalStateException("Loop item handler is not set !!");
		}
		
		loopItemHandler.accept(item);
	}

	@Override
	public void initComponent(EasyWicket annot, MarkupContainer parentWidget) {
		EasyWicketUtil.getInstance().initComponentWithAnnot(this, annot);
	}

}
