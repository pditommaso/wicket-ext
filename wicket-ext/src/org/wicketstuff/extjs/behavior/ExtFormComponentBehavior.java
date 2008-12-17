/*
 * ��ɽ����������ϵͳ CyberWorks
 * ��Ϣ����ƽ̨ϵͳ CyberExchange
 * ��Ȩ����(C)�������пƽ���Ϣ�������޹�˾
 *            ��ɽ����������
 * @����   Jackie Zhong
 * �������� 2008-12-14
 *
 */
package org.wicketstuff.extjs.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.form.FormComponent;
import org.wicketstuff.extjs.Config;

/**
 * Base behavior for Wicket form component
 * 
 * add required field behavior, suggest other ext formcomponent to extend this class
 *  
 * @author Jackie Zhong
 *
 */
public abstract class ExtFormComponentBehavior extends ExtComponentBehavior {

    public ExtFormComponentBehavior(String classname) {
        super(classname);
    }

    public ExtFormComponentBehavior(String classname, Config options) {
        super(classname, options);
    }

    public FormComponent getFormComponent() {
        Component component = getComponent();
        if (component instanceof FormComponent)
            return (FormComponent) component;
        throw new WicketRuntimeException("This Behavior can only attach to a wicket form component.");
    }

    @Override
    protected void onExtConfig(Config config) {
        super.onExtConfig(config);
        if (getFormComponent().isRequired())
            config.set("allowBlank", false);
    }
}
