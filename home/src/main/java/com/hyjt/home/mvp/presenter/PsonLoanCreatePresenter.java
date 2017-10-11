package com.hyjt.home.mvp.presenter;

import android.app.Application;
import android.widget.EditText;

import com.hyjt.frame.api.parseResponse;
import com.hyjt.frame.di.scope.ActivityScope;
import com.hyjt.frame.mvp.BasePresenter;
import com.hyjt.frame.integration.AppManager;


import com.hyjt.frame.widget.imageloader.ImageLoader;


import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.observable.ObservableError;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import javax.inject.Inject;

import com.hyjt.home.mvp.contract.PsonLoanCreateContract;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.ChildrenBean;
import com.hyjt.home.mvp.model.entity.Resp.LinkManResp;
import com.hyjt.home.mvp.ui.view.treelistview.Dept;
import com.hyjt.home.mvp.ui.view.treelistview.Node;

import java.util.ArrayList;
import java.util.List;


@ActivityScope
public class PsonLoanCreatePresenter extends BasePresenter<PsonLoanCreateContract.Model, PsonLoanCreateContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private int deptId;
    private List<Node> deptList = new ArrayList<>();

    @Inject
    public PsonLoanCreatePresenter(PsonLoanCreateContract.Model model, PsonLoanCreateContract.View rootView
            , RxErrorHandler handler, Application application) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
    }

    public void requestDept() {
        mModel.reqsDeptList()
                .map(new parseResponse<>())
                .flatMap(new Function<List<ChildrenBean>, ObservableSource<List<Node>>>() {
                    @Override
                    public ObservableSource<List<Node>> apply(@NonNull List<ChildrenBean> childrenBeanList) throws Exception {
                        deptId = 1;
                        deptList.clear();
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
     * @param childrenBeanList
     * @param parent
     */
    private void parseDept(List<ChildrenBean> childrenBeanList, int parent) {
        for (ChildrenBean childrenBean : childrenBeanList){
            deptId ++;
            deptList.add(new Dept(deptId, parent, childrenBean.getText(), childrenBean.getId()));
            if (childrenBean.getChildren().size() == 0)
                continue;
            parseDept(childrenBean.getChildren(), deptId);
        }
    }

    public void getLinkmanMsg(String SysDepartment, EditText selEdit, StaffNameIdKey staffNameId, boolean moreCheck) {
        mModel.getLinkman("1", "9999", SysDepartment)
                .map(new parseResponse<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<LinkManResp>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull LinkManResp linkManResp) {
                        LinkManResp.DataBean.RowsBean[] linkman = linkManResp.getData().getRows().toArray
                                (new LinkManResp.DataBean.RowsBean[linkManResp.getData().getRows().size()]);

                        String[] nameAry = new String[linkman.length];
                        String[] nameSendAry = new String[linkman.length];

//                        ^员工唯一码|姓名^
                        for (int i = 0; i < linkman.length; i++) {
                            LinkManResp.DataBean.RowsBean item = linkman[i];
                            List<String> cell = item.getCell();
                            nameAry[i] = cell.get(1);
                            nameSendAry[i] = cell.get(0);
                        }

                        mRootView.getLinkmanOk(nameAry, nameSendAry, selEdit, staffNameId, moreCheck);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mApplication = null;
    }

}
