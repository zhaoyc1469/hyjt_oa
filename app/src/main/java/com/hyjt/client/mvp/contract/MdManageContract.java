package com.hyjt.client.mvp.contract;


import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;


public interface MdManageContract {
    //���ھ���ʹ�õĹ���UI�ķ������Զ��嵽IView��,����ʾ���ؽ�����,����ʾ������Ϣ
    interface View extends IView {

    }

    //Model�㶨��ӿ�,�ⲿֻ�����Model���ص�����,��������ڲ�ϸ��,���Ƿ�ʹ�û���
    interface Model extends IModel {

    }
}
