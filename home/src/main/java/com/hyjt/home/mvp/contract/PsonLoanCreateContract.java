package com.hyjt.home.mvp.contract;


import android.widget.EditText;

import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.frame.mvp.IView;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.ChildrenBean;
import com.hyjt.home.mvp.model.entity.Resp.LinkManResp;
import com.hyjt.home.mvp.ui.view.treelistview.Node;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import io.reactivex.Observable;


public interface PsonLoanCreateContract {

    interface View extends IView {

        RxPermissions getRxPermissions();

        void showDeptTree(List<Node> deptList);

        void getLinkmanOk(String[] nameAry, String[] nameSendAry, EditText selEdit, StaffNameIdKey staffNameId, boolean moreCheck);
    }

    interface Model extends IModel {

        Observable<BaseJson<List<ChildrenBean>>> reqsDeptList();

        Observable<BaseJson<LinkManResp>> getLinkman(String Page, String RP, String SysDepartment);
    }
}
