package com.hyjt.home.mvp.presenter;

import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.home.mvp.contract.StaffMsgContract;
import com.hyjt.home.mvp.model.entity.Resp.ChildrenBean;
import com.hyjt.home.mvp.model.entity.Resp.StaffMsgResp;
import com.hyjt.home.mvp.ui.view.treelistview.Dept;
import com.hyjt.home.mvp.ui.view.treelistview.Node;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.observable.ObservableError;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


@ActivityScope
public class StaffMsgPresenter extends BasePresenter<StaffMsgContract.Model, StaffMsgContract.View> {
    private RxErrorHandler mErrorHandler;
    private int deptId;
    private List<Node> deptList = new ArrayList<>();

    @Inject
    public StaffMsgPresenter(StaffMsgContract.Model model, StaffMsgContract.View rootView
            , RxErrorHandler handler) {
        super(model, rootView);
        this.mErrorHandler = handler;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }

    public void getStaffMsg(String staffId) {

        mModel.getStaffMsg(staffId)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mRootView.startLoading())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> mRootView.endLoading())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<StaffMsgResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull StaffMsgResp staffMsgResp) {
                        mRootView.setStaffMsg(staffMsgResp);
                    }
                });
    }

    public void sendDelStaf(String id) {
        mModel.delStaffMsg(id)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull Object staffMsgResp) {
                        mRootView.showMessage("删除成功");
                        mRootView.killMyself();
                    }
                });
    }

    public void requestDept() {
        mModel.reqsDeptList()
                .map(new parseResponse<>())
                .flatMap(new Function<List<ChildrenBean>, ObservableSource<List<Node>>>() {
                    @Override
                    public ObservableSource<List<Node>> apply(@NonNull List<ChildrenBean> childrenBeanList) throws Exception {
                        deptId = 1;
                        parseDept(childrenBeanList, 0);
                        return ObservableError.just(deptList);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<List<Node>>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull List<Node> data) {
                        mRootView.showDeptTree(deptList);
                    }
                });
    }

    /**
     * 解析部门数据
     *
     * @param childrenBeanList
     * @param parent
     */
    private void parseDept(List<ChildrenBean> childrenBeanList, int parent) {
        for (ChildrenBean childrenBean : childrenBeanList) {
            deptId++;
            deptList.add(new Dept(deptId, parent, childrenBean.getText(), childrenBean.getId()));
            if (childrenBean.getChildren().size() == 0)
                continue;
            parseDept(childrenBean.getChildren(), deptId);
        }
    }

    public void sendEditStaf(StaffMsgResp staffMsgResp) {
        mModel.editStaffMsg(staffMsgResp)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull Object staffMsgResp) {
                        mRootView.showMessage("编辑成功");
                        mRootView.killMyself();
                    }
                });
    }

}