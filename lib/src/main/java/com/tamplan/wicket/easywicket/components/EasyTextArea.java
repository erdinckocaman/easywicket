package com.tamplan.wicket.easywicket.components;

import org.apache.wicket.markup.html.form.TextArea;

public class EasyTextArea<T> extends TextArea<T>{

    private boolean inputTrimmed;

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
}
