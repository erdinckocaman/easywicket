package com.tamplan.wicket.easywicket.components;

import com.tamplan.wicket.easywicket.*;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.AjaxIndicatorAppender;
import org.apache.wicket.util.string.Strings;

public class EasyIndicatingAjaxSubmitLink extends AjaxSubmitLink implements IAjaxIndicatorAware, IEasyWicket {

    private AjaxIndicatorAppender indicatorAppender =
            new AjaxIndicatorAppender();

    private EasyWicket annot;

    public EasyIndicatingAjaxSubmitLink(String id) {
        super(id);

        add(indicatorAppender);
    }

    @Override
    public String getAjaxIndicatorMarkupId() {
        return indicatorAppender.getMarkupId();
    }

    @Override
    public void initComponent(EasyWicket annot, MarkupContainer parentWidget) {
        EasyWicketUtil.getInstance().initComponentWithAnnot(this, annot);
        this.annot = annot;
    }

    @Override
    protected void onSubmit(AjaxRequestTarget target) {
        super.onSubmit(target);

        if (Strings.isEmpty(annot.action())) {
            return;
        }
        EasyWicketUtil util = EasyWicketUtil.getInstance();

        WidgetContext ctx = new WidgetContext();
        ctx.setAjaxRequestTarget(target);
        IEasyWicketContainer container = util.findContainer(this);
        container.setCurrentWidgetContext(ctx);

        util.callAction(util.findContainer(this), annot.action());
    }

}