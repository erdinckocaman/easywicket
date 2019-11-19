package com.tamplan.wicket.easywicket.components;

import com.tamplan.wicket.easywicket.EasyWicket;
import com.tamplan.wicket.easywicket.EasyWicketUtil;
import com.tamplan.wicket.easywicket.IEasyWicket;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.TextArea;

public class EasyTextArea<T> extends TextArea<T> implements IEasyWicket {

    private boolean inputTrimmed;
    private EasyWicket annot;

    public EasyTextArea(String id) {
        super(id);
    }

    public void setInputTrimmed(boolean inputTrimmed) {
        this.inputTrimmed = inputTrimmed;
    }

    public boolean isInputTrimmed() {
        return inputTrimmed;
    }

    @Override
    protected boolean shouldTrimInput() {
        return inputTrimmed;
    }

    @Override
    public void initComponent(EasyWicket annot, MarkupContainer parentWidget) {
        this.annot = annot;

        EasyWicketUtil.getInstance().initComponentWithAnnot(this, annot);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        EasyWicketUtil.getInstance().configureComponent(this,
                annot.visible(), annot.enabled());
    }
}
