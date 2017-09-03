package com.hyjt.home.mvp.contract;


import com.hyjt.frame.api.BaseJson;
import com.hyjt.frame.mvp.IListView;
import com.hyjt.frame.mvp.IModel;
import com.hyjt.home.mvp.model.entity.Resp.ChildrenBean;
import com.hyjt.home.mvp.model.entity.Resp.StaffListResp;
import com.hyjt.home.mvp.ui.adapter.StaffListAdapter;
import com.hyjt.home.mvp.ui.view.treelistview.Node;

import java.util.List;

import io.reactivex.Observable;

public interface AddressBookContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends IListView {

        void setAdapter(StaffListAdapter adapter);

        void startLoadMore();

        void endLoadMore();

        void endLoad();

        void showDeptTree(List<Node> deptList);

        void addressPop(List<String> cell);
    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {

        Observable<BaseJson<List<ChildrenBean>>> reqsDeptList();

        Observable<BaseJson<StaffListResp>> getAddressBook(int Page, int RP, String State, String deptId, String Name, String edu, String startTime, String endTime);
    }
}