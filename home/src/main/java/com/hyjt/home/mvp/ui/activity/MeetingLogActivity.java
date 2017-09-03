package com.hyjt.home.mvp.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.JsonUtils;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerMeetingLogComponent;
import com.hyjt.home.di.module.MeetingLogModule;
import com.hyjt.home.mvp.contract.MeetingLogContract;
import com.hyjt.home.mvp.model.entity.Reqs.MeetingMsgReqs;
import com.hyjt.home.mvp.model.entity.Reqs.MtQuestionReqs;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.presenter.MeetingLogPresenter;
import com.hyjt.home.mvp.ui.view.DateTimePickDialog;
import com.hyjt.home.mvp.ui.view.DepartmentPop;
import com.hyjt.home.mvp.ui.view.addpicview.AddPicView;
import com.hyjt.home.utils.SingleSelectItem;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;


@Route(path = "/home/MeetingLogActivity")
public class MeetingLogActivity extends BaseActivity<MeetingLogPresenter> implements MeetingLogContract.View {


    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.Button mBtnNewMeeting;
    private com.hyjt.home.mvp.ui.view.addpicview.AddPicView mAddPicView;
    private Button mBtnNewQs;
    private android.widget.LinearLayout mLlQuestion;
    private ScrollView mSlvMeeting;
    private TextView mTvRecorder;
    private MeetingLogActivity mContext;
    private EditText mEdtMeetingPerson;
    private StaffNameIdKey meetingPerson;
    private EditText mEdtMeetingCompere;
    private StaffNameIdKey meetingCompere;
    private List<MtQuestionReqs> meetingQuestionList = new ArrayList<>();
    private EditText mEdtMeetingTime;
    private RxPermissions mRxPermissions;
    private ProgressDialog progressDialog;
    private EditText mEdtMeetingIssue;
    private EditText mEdtMeetingSite;
    private EditText mEdtMeetingContent;
    private EditText mEdtDepartment;
    private MeetingMsgReqs meetingMsgReqs;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button mBtnPersonClear;


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMeetingLogComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .meetingLogModule(new MeetingLogModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_meeting_log; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mContext = this;
        this.mRxPermissions = new RxPermissions(this);
        SharedPreferences sharedPre = getSharedPreferences("config", MODE_PRIVATE);
        String username = sharedPre.getString("username", "");
        String part = sharedPre.getString("part", "");

        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mSlvMeeting = (ScrollView) findViewById(R.id.slv_meeting);
        mBtnNewMeeting = (Button) findViewById(R.id.btn_new_meeting);
        mAddPicView = (AddPicView) findViewById(R.id.add_pic_view);
        mBtnNewQs = (Button) findViewById(R.id.btn_new_question);
        mTvRecorder = (TextView) findViewById(R.id.tv_recorder);
        mEdtMeetingIssue = (EditText) findViewById(R.id.edt_home_meeting_issue);
        mEdtMeetingSite = (EditText) findViewById(R.id.edt_home_meeting_site);
        mEdtMeetingContent = (EditText) findViewById(R.id.edt_home_meeting_content);
        //部门选择
        mEdtDepartment = (EditText) findViewById(R.id.edt_home_meeting_department);
        mEdtDepartment.setOnTouchListener(new SingleSelectItem(mContext, mEdtDepartment, "选择部门",new String[]{"集团","矿业","工程","集美"}, true));

        mBtnPersonClear = (Button) findViewById(R.id.btn_person_clear);
        mBtnPersonClear.setOnClickListener(v -> {
            mEdtMeetingPerson.setText("");
            meetingPerson.setSendKey("");
        });

        mEdtMeetingPerson = (EditText) findViewById(R.id.edt_home_meeting_person);
        meetingPerson = new StaffNameIdKey();

        mEdtMeetingCompere = (EditText) findViewById(R.id.edt_home_meeting_compere);
        meetingCompere = new StaffNameIdKey();


        mEdtMeetingTime = (EditText) findViewById(R.id.edt_meeting_time);
        setSelTime(mEdtMeetingTime);


        mTvRecorder.setText(username);
        mTvTitle.setText(part);

        mBtnNewMeeting.setOnClickListener(v -> upLoadMeetingMsg());
        mBtnNewQs.setOnClickListener(v -> addQuestion());


        mLlQuestion = (LinearLayout) findViewById(R.id.ll_question);
        meetingQuestionList.clear();


        mEdtMeetingPerson.setOnClickListener(v -> selStaff(mContext, mEdtMeetingPerson, meetingPerson, true));
        mEdtMeetingCompere.setOnClickListener(v -> selStaff(mContext, mEdtMeetingCompere, meetingCompere, false));

    }

    private void addQuestion() {
        MtQuestionReqs meetingQuestion = new MtQuestionReqs();
        StaffNameIdKey StaffNameIdKey = new StaffNameIdKey();
        int size = meetingQuestionList.size();
        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_met_question, null);
        //选择部门
        EditText edtDepartment = (EditText) inflate.findViewById(R.id.edt_question_department);
//        edtDepartment.setOnTouchListener(new SingleSelectItem(mContext, edtDepartment, "选择部门",new String[]{"集团","矿业","工程","集美"}, true));
        //人员选择
        EditText edtExecutor = (EditText) inflate.findViewById(R.id.edt_question_executor);
        edtExecutor.setOnClickListener(v -> selStaff(mContext, edtExecutor, StaffNameIdKey, true));
        EditText edtSupervisor = (EditText) inflate.findViewById(R.id.edt_question_supervisor);
        edtSupervisor.setOnClickListener(v -> selStaff(mContext, edtSupervisor, StaffNameIdKey, false));
        //删除布局
        Button delQuestion = (Button) inflate.findViewById(R.id.btn_del_question);
        delQuestion.setOnClickListener(v -> {
            if (meetingQuestionList.size()>size){
                mLlQuestion.removeView(inflate);
                meetingQuestionList.remove(size);
            }
        });

        EditText predictTime = (EditText) inflate.findViewById(R.id.edt_question_predict_time);
        setSelTime(predictTime);

        EditText superviseTime = (EditText) inflate.findViewById(R.id.edt_supervise_time);
        setSelTime(superviseTime);
        EditText finishTime = (EditText) inflate.findViewById(R.id.edt_question_finish_time);
        setSelTime(finishTime);


        mLlQuestion.addView(inflate);
        meetingQuestionList.add(meetingQuestion);
        mSlvMeeting.fullScroll(ScrollView.FOCUS_DOWN);
    }

    private void setSelTime(EditText finishTime) {
        finishTime.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Calendar c = Calendar.getInstance();
                new DateTimePickDialog(mContext, c).setOnDateTimeSetListener((dp, tp, year, monthOfYear, dayOfMonth, hourOfDay, minute) -> {
                    finishTime.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + " "
                            + hourOfDay + ":" + minute + ":00");
                    // 保存选择后时间
                    c.set(Calendar.YEAR, year);
                    c.set(Calendar.MONTH, monthOfYear);
                    c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    c.set(Calendar.MINUTE, minute);
                });
            }
            return true;
        });
    }

    private void upLoadMeetingMsg() {
        meetingMsgReqs = new MeetingMsgReqs();

        if (checkNullMsg() && getQuestionBean()) {
            progressDialog = ProgressDialog.show(this, null, "正在上传图片...");
            // 发送图片
            mPresenter.sendPicture(mAddPicView.getListData());

        }

//        String s = JsonUtils.beanToJson(meetingQuestionList);
//        Log.e("fsdafsd", s);
    }

    /**
     * 获取会议信息
     */
    private boolean checkNullMsg() {
        String issue = mEdtMeetingIssue.getText().toString().trim();
        if (TextUtils.isEmpty(issue)) {
            shortToast("请填写会议名称");
            return false;
        }
        meetingMsgReqs.setCM_name(issue);
        String site = mEdtMeetingSite.getText().toString().trim();
//        if (TextUtils.isEmpty(site)) {
//            shortToast("请填写会议地点");
//            return false;
//        }
        meetingMsgReqs.setCM_room(site);
        String person = mEdtMeetingPerson.getText().toString().trim();
        if (TextUtils.isEmpty(person)) {
            shortToast("请选择与会人员");
            return false;
        }
        meetingMsgReqs.setSysPerson(meetingPerson.getSendKey());
        String compere = mEdtMeetingCompere.getText().toString().trim();
        if (TextUtils.isEmpty(compere)) {
            shortToast("请选择会议主持人");
            return false;
        }
        meetingMsgReqs.setCM_person(compere);
        String content = mEdtMeetingContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            shortToast("请填写会议内容");
            return false;
        }
        meetingMsgReqs.setCM_content(content);
        String meetingTime = mEdtMeetingTime.getText().toString().trim();
        if (TextUtils.isEmpty(meetingTime)) {
            shortToast("请选择会议时间");
            return false;
        }
        meetingMsgReqs.setCM_starttime(meetingTime);
        String department = mEdtDepartment.getText().toString().trim();
        if (TextUtils.isEmpty(department)) {
            shortToast("请选择参会部门");
            return false;
        }
        meetingMsgReqs.setCM_department(department);
        meetingMsgReqs.setCM_promoter(mTvRecorder.getText().toString());
        return true;
    }

    /**
     * 获取问题内容
     */
    private boolean getQuestionBean() {
//        if (meetingQuestionList.size() == 0){
//            shortToast("您至少需要添加一个会议问题");
//            return false;
//        }


        for (int i = 0; i < mLlQuestion.getChildCount(); i++) {
            LinearLayout childAt = (LinearLayout) mLlQuestion.getChildAt(i);
            MtQuestionReqs meetingQuestion = meetingQuestionList.get(i);
            //执行部门
            EditText department = (EditText) childAt.findViewById(R.id.edt_question_department);
            String departmentStr = department.getText().toString().trim();
            if (TextUtils.isEmpty(departmentStr)) {
                shortToast("请您填写第" + (i + 1) + "个待解决问题的执行部门");
                return false;
            }
            meetingQuestion.setExecutiveDepartment(departmentStr);
            //要求时间
            EditText predictTime = (EditText) childAt.findViewById(R.id.edt_question_predict_time);
            String predictTimeStr = predictTime.getText().toString().trim();
            if (TextUtils.isEmpty(departmentStr)) {
                shortToast("请您填写第" + (i + 1) + "个待解决问题的要求完成时间");
                return false;
            }
            meetingQuestion.setFinishTime(predictTimeStr);
            //待解决问题
            EditText questionContent = (EditText) childAt.findViewById(R.id.edt_question_content);
            meetingQuestion.setRecord(questionContent.getText().toString());
            //完成时间
            EditText finishTime = (EditText) childAt.findViewById(R.id.edt_question_finish_time);
            meetingQuestion.setJiejueTime(finishTime.getText().toString());
            //针对问题解决办法
            EditText questionMethod = (EditText) childAt.findViewById(R.id.edt_question_method);
            meetingQuestion.setRecordFangfa(questionMethod.getText().toString());
            //督办时间
            EditText superviseTime = (EditText) childAt.findViewById(R.id.edt_supervise_time);
            meetingQuestion.setDuBanTime(superviseTime.getText().toString());
            //问题状态
//            RadioGroup questionState = (RadioGroup) childAt.findViewById(R.id.rg_question_state);
//            int questionStateId = questionState.getCheckedRadioButtonId();
//            if (questionStateId == 0) {
//                meetingQuestion.setJiejueState("是");
//            } else if (questionStateId == 1) {
//                meetingQuestion.setJiejueState("否");
//            } else if (questionStateId == 2) {
//                meetingQuestion.setJiejueState("长期");
//            }
//            //督办状态
//            RadioGroup superviseState = (RadioGroup) childAt.findViewById(R.id.rg_supervise_state);
//            int superviseStateId = superviseState.getCheckedRadioButtonId();
//            if (superviseStateId == 0) {
//                meetingQuestion.setJiejueState("同意");
//            } else if (superviseStateId == 1) {
//                meetingQuestion.setJiejueState("不同意");
//            }
            //执行人
            EditText edtExecutor = (EditText) childAt.findViewById(R.id.edt_question_executor);
            String executorStr = edtExecutor.getText().toString().trim();
            if (TextUtils.isEmpty(executorStr)) {
                shortToast("请您填写第" + (i + 1) + "个待解决问题的执行人");
                return false;
            }
            meetingQuestion.setExecutor(executorStr);
            //督办人
            EditText edtSupervisor = (EditText) childAt.findViewById(R.id.edt_question_supervisor);
            String supervisorStr = edtSupervisor.getText().toString().trim();
            if (TextUtils.isEmpty(supervisorStr)) {
                shortToast("请您填写第" + (i + 1) + "个待解决问题的督办人");
                return false;
            }
            meetingQuestion.setDuBanPerson(supervisorStr);
        }
        return true;
    }

    @Override
    public void showMessage(@NonNull String message) {
        hideUpload();
        checkNotNull(message);
        UiUtils.snackbarText(message);
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x01 && resultCode == Activity.RESULT_OK) {
            Uri vData = data.getData();
            Log.e("yzy", "onActivityResult: " + vData);
            mAddPicView.addPicture(vData);
        }
    }

    /**
     * 上传图片完成
     *
     * @param imgUploadList
     */
    @Override
    public void imgUploadOk(List<ImgUploadResp> imgUploadList) {

        progressDialog = ProgressDialog.show(this, null, "正在上传会议记录信息...");
        if (meetingQuestionList.size() > 0) {
            meetingMsgReqs.setRowsCount("" + meetingQuestionList.size());
            meetingMsgReqs.setRows(JsonUtils.beanToJson(meetingQuestionList));
        } else {
            meetingMsgReqs.setRowsCount("0");
        }
        mPresenter.sendMeetingMsg(meetingMsgReqs, imgUploadList);


        Log.e("完成", "完成");

    }

    @Override
    public void hideUpload() {
        progressDialog.dismiss();
    }

    /**
     * 选择员工弹窗
     *
     * @param mContext           上下文
     * @param mEdtMeetingCompere edittext
     * @param meetingCompere     后台需要的数据类型
     * @param b                  是否多选
     */
    private void selStaff(MeetingLogActivity mContext, EditText mEdtMeetingCompere, StaffNameIdKey meetingCompere, boolean b) {
        DepartmentPop departmentPop = new DepartmentPop(mContext, mEdtMeetingCompere, meetingCompere, b);
        departmentPop.setOnItemClickListener((Department, editText, meetingPerson, moreCheck) -> {
            departmentPop.dismiss();
            mPresenter.getLinkmanMsg(Department, editText, meetingPerson, moreCheck);
        });
        departmentPop.showAtLocation(this.mContext.findViewById(R.id.rl_meeting_log),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void getLinkmanOk(String[] nameAry, String[] nameSendAry, EditText selEdit, StaffNameIdKey StaffNameIdKey, boolean moreCheck) {

//        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.home_dialog_sel_list, null);
//        dialog.setTitle("选择人员");
//        dialog.setView(layout)
//                .setCancelable(false)
//                .setNegativeButton("取消", (dialog1, id) -> dialog1.cancel());
//        AlertDialog accAlert = dialog.create();
//        ListView accList = (ListView) layout.findViewById(R.id.accList);
//
//        List<Map<String, String>> itemData = new ArrayList<>();
//        Map<String, String> mp;
//        for (String item : nameAry) {
//            mp = new HashMap<>();
//            mp.put("name", item);
//            itemData.add(mp);
//        }
//        SimpleAdapter czadapter = new SimpleAdapter(mContext, itemData,
//                R.layout.home_dialog_sel_item, new String[]{"name"},
//                new int[]{R.id.tv_simple_text});
//        accList.setAdapter(czadapter);
//
//        if (moreCheck) {
//            accList.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
//                Map<String, String> m = itemData.get(arg2);
//                String str = selEdit.getText().toString();
//                if ("".equals(str)) {
//                    selEdit.setText(m.get("name"));
//                    StaffNameIdKey.setSendKey(nameSendAry[arg2]);
//                } else {
//                    if (str.contains(m.get("name"))) {
//                        accAlert.dismiss();
//                        return;
//                    }
//                    selEdit.setText(str + "," + m.get("name"));
//                    String sendKey = StaffNameIdKey.getSendKey();
//                    sendKey = sendKey.replace("&", "-");
//                    StaffNameIdKey.setSendKey(sendKey + nameSendAry[arg2]);
//                }
//                Log.e("selStaff", StaffNameIdKey.getSendKey());
//                accAlert.dismiss();
//            });
//        } else {
//            accList.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
//                Map<String, String> m = itemData.get(arg2);
//                selEdit.setText(m.get("name"));
//                StaffNameIdKey.setSendKey(nameSendAry[arg2]);
//                Log.e("selStaff", StaffNameIdKey.getSendKey());
//                accAlert.dismiss();
//            });
//        }
//        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(selEdit.getWindowToken(), 0);
//        accAlert.show();


        final boolean bSelect[] = new boolean[nameAry.length];
        DialogInterface.OnMultiChoiceClickListener staffListener = (dialog1, which, isChecked) -> bSelect[which] = isChecked;
        builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMultiChoiceItems(nameAry, bSelect, staffListener);
        dialog = builder.create();
        dialog.setTitle("人员选择");
        DialogInterface.OnClickListener lis41 = (dialog12, which) -> {
            String str = selEdit.getText().toString();
            String sendKey = StaffNameIdKey.getSendKey();
            for (int i = 0; i < bSelect.length; i++) {
                if (bSelect[i]) {
                    if ("".equals(str)) {
                        str = nameAry[i];
                        StaffNameIdKey.setSendKey(nameSendAry[i]);
                    } else {
                        if (!str.contains(nameAry[i])) {
                            str = str + "," + nameAry[i];
                            if (!TextUtils.isEmpty(sendKey))
                                sendKey = sendKey.replace("&", "-");
                            StaffNameIdKey.setSendKey(sendKey + nameSendAry[i]);
                        }
                    }
                }
            }
            selEdit.setText(str);
        };
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "确定", lis41);
        dialog.show();
    }

    @Override
    public RxPermissions getRxPermissions() {
        return mRxPermissions;
    }
}
