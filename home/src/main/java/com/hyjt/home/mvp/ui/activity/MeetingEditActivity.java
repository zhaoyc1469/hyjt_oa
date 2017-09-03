package com.hyjt.home.mvp.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.hyjt.frame.base.BaseActivity;
import com.hyjt.frame.di.component.AppComponent;
import com.hyjt.frame.utils.JsonUtils;
import com.hyjt.frame.utils.UiUtils;
import com.hyjt.home.R;
import com.hyjt.home.di.component.DaggerMeetingEditComponent;
import com.hyjt.home.di.module.MeetingEditModule;
import com.hyjt.home.mvp.contract.MeetingEditContract;
import com.hyjt.home.mvp.model.entity.Reqs.MeetingEditReqs;
import com.hyjt.home.mvp.model.entity.Reqs.MtQuestionReqs;
import com.hyjt.home.mvp.model.entity.Reqs.StaffNameIdKey;
import com.hyjt.home.mvp.model.entity.Resp.ImgUploadResp;
import com.hyjt.home.mvp.model.entity.Resp.MeetingMsgResp;
import com.hyjt.home.mvp.presenter.MeetingEditPresenter;
import com.hyjt.home.mvp.ui.adapter.AttachmentAdapter;
import com.hyjt.home.mvp.ui.view.DateTimePickDialog;
import com.hyjt.home.mvp.ui.view.DepartmentPop;
import com.hyjt.home.mvp.ui.view.addpicview.AddPicView;
import com.hyjt.home.utils.SingleSelectItem;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static com.hyjt.frame.utils.Preconditions.checkNotNull;
import static com.hyjt.home.R.id.btn_add_pic;
import static com.hyjt.home.R.id.btn_del_meeting;
import static com.hyjt.home.R.id.btn_edit_meeting;
import static com.hyjt.home.R.id.btn_new_question;
import static com.hyjt.home.R.id.iv_delmeeting_1;
import static com.hyjt.home.R.id.iv_delmeeting_2;
import static com.hyjt.home.R.id.iv_delmeeting_3;
import static com.hyjt.home.R.id.iv_delmeeting_4;
import static com.hyjt.home.R.id.iv_meeting_1;
import static com.hyjt.home.R.id.iv_meeting_2;
import static com.hyjt.home.R.id.iv_meeting_3;
import static com.hyjt.home.R.id.iv_meeting_4;
import static com.hyjt.home.R.id.rb_agree;
import static com.hyjt.home.R.id.rb_finish;
import static com.hyjt.home.R.id.rb_long_time;

@Route(path = "/home/MeetingEditActivity")
public class MeetingEditActivity extends BaseActivity<MeetingEditPresenter> implements MeetingEditContract.View, View.OnClickListener {


    private android.widget.ImageView mIvTopBack;
    private android.widget.TextView mTvTitle;
    private android.widget.ImageView mIvTopSelect;
    private android.widget.Button mBtnEditMeeting;
    private android.widget.Button mBtnDelMeeting;
    private android.widget.ScrollView mSlvMeeting;
    private android.widget.EditText mEdtHomeMeetingIssue;
    private android.widget.EditText mEdtHomeMeetingSite;
    private android.widget.EditText mEdtHomeMeetingMan;
    private android.widget.EditText mEdtHomeMeetingCompere;
    private android.widget.EditText mEdtHomeMeetingContent;
    private android.widget.EditText mEdtHomeMeetingTime;
    private android.widget.EditText mEdtHomeMeetingDepartment;
    private android.widget.TextView mTvHomeMeetingRecorder;
    private android.widget.LinearLayout mLlQuestion;
    private String cm_promoter;
    private MeetingEditActivity mContext;
    private List<MtQuestionReqs> meetingQuestionList;
    private String userName;
    private RelativeLayout meetingPicture;
    private ArrayList<String> imgList;
    private ImageView meetingPc1;
    private ImageView meetingPc2;
    private ImageView meetingPc3;
    private ImageView meetingPc4;
    private String fileUploader;
    private boolean canEdit;
    private String meetingId;
    private MeetingEditReqs meetingEditReqs;
    private MeetingMsgResp meetingMsgResp;
    private StaffNameIdKey meetingMan;
    private AddPicView mAddPicView;
    private ProgressDialog progressDialog;
    private Button mAddPic;
    private ImageView delmeetingPc1;
    private ImageView delmeetingPc2;
    private ImageView delmeetingPc3;
    private ImageView delmeetingPc4;
    private RecyclerView recyAttachment;
    private ArrayList<String> attachmentList;
    private Button mBtnQuestion;
    private RxPermissions mRxPermissions;
    private LinearLayoutManager linearLayoutManager;
    private AttachmentAdapter attachmentAdapter;
    private ArrayList<String> imgListSend;
    private ArrayList<String> attachmentListSend;
    private RelativeLayout rlAttachment;
    private RelativeLayout rlMeetingPc1;
    private RelativeLayout rlMeetingPc2;
    private RelativeLayout rlMeetingPc3;
    private RelativeLayout rlMeetingPc4;
    private Button mBtnPersonClear;
    private boolean ForbidEdit;
    private android.support.v7.app.AlertDialog dialog;
    private android.support.v7.app.AlertDialog.Builder builder;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMeetingEditComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .meetingEditModule(new MeetingEditModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.home_activity_meeting_edit; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mContext = this;
        String meetingId = getIntent().getStringExtra("id");

        this.mRxPermissions = new RxPermissions(this);

        canEdit = false;

        mIvTopBack = (ImageView) findViewById(R.id.iv_top_back);
        mIvTopBack.setOnClickListener(v -> finish());
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setOnClickListener(v -> finish());
        mIvTopSelect = (ImageView) findViewById(R.id.iv_top_select);
        mIvTopSelect.setVisibility(View.GONE);
        mBtnEditMeeting = (Button) findViewById(btn_edit_meeting);
        mBtnDelMeeting = (Button) findViewById(btn_del_meeting);
        mSlvMeeting = (ScrollView) findViewById(R.id.slv_meeting);
        mEdtHomeMeetingIssue = (EditText) findViewById(R.id.edt_home_meeting_issue);
        mEdtHomeMeetingSite = (EditText) findViewById(R.id.edt_home_meeting_site);
        mEdtHomeMeetingMan = (EditText) findViewById(R.id.edt_home_meeting_man);
        mEdtHomeMeetingCompere = (EditText) findViewById(R.id.edt_home_meeting_compere);
        mEdtHomeMeetingContent = (EditText) findViewById(R.id.edt_home_meeting_content);
        mEdtHomeMeetingTime = (EditText) findViewById(R.id.edt_home_meeting_time);
        mEdtHomeMeetingDepartment = (EditText) findViewById(R.id.edt_home_meeting_department);
        mTvHomeMeetingRecorder = (TextView) findViewById(R.id.tv_home_meeting_recorder);
        mLlQuestion = (LinearLayout) findViewById(R.id.ll_question);
        mBtnQuestion = (Button) findViewById(R.id.btn_new_question);
        mBtnQuestion.setOnClickListener(this);

        mBtnPersonClear = (Button) findViewById(R.id.btn_person_clear);
        mBtnPersonClear.setOnClickListener(this);

        meetingPicture = (RelativeLayout) findViewById(R.id.rl_home_meeting_picture);
        //添加图片
        mAddPicView = (AddPicView) findViewById(R.id.add_pic_view);
        mAddPic = (Button) findViewById(R.id.btn_add_pic);

        mAddPic.setOnClickListener(this);

        rlAttachment = (RelativeLayout) findViewById(R.id.rl_recy_attachment);
        recyAttachment = (RecyclerView) findViewById(R.id.recy_attachment);

        meetingPc1 = (ImageView) findViewById(iv_meeting_1);
        meetingPc2 = (ImageView) findViewById(iv_meeting_2);
        meetingPc3 = (ImageView) findViewById(iv_meeting_3);
        meetingPc4 = (ImageView) findViewById(iv_meeting_4);
        rlMeetingPc1 = (RelativeLayout) findViewById(R.id.rl_meeting_img1);
        rlMeetingPc2 = (RelativeLayout) findViewById(R.id.rl_meeting_img2);
        rlMeetingPc3 = (RelativeLayout) findViewById(R.id.rl_meeting_img3);
        rlMeetingPc4 = (RelativeLayout) findViewById(R.id.rl_meeting_img4);
        delmeetingPc1 = (ImageView) findViewById(iv_delmeeting_1);
        delmeetingPc2 = (ImageView) findViewById(iv_delmeeting_2);
        delmeetingPc3 = (ImageView) findViewById(iv_delmeeting_3);
        delmeetingPc4 = (ImageView) findViewById(iv_delmeeting_4);

        delmeetingPc1.setOnClickListener(this);
        delmeetingPc2.setOnClickListener(this);
        delmeetingPc3.setOnClickListener(this);
        delmeetingPc4.setOnClickListener(this);

        meetingPc1.setOnClickListener(this);
        meetingPc2.setOnClickListener(this);
        meetingPc3.setOnClickListener(this);
        meetingPc4.setOnClickListener(this);

        mBtnEditMeeting.setOnClickListener(this);
        mBtnDelMeeting.setOnClickListener(this);
        mPresenter.getMeetingMsg(meetingId);

        meetingEditReqs = new MeetingEditReqs();

//        setForbidEdit();
//        setAllowEdit();

    }

    private void setAllowEdit() {

        ForbidEdit = false;

        mEdtHomeMeetingMan.setFocusable(false);
        mEdtHomeMeetingCompere.setFocusable(false);
        mEdtHomeMeetingTime.setFocusable(false);
        mEdtHomeMeetingDepartment.setFocusable(false);

        mEdtHomeMeetingMan.setOnClickListener(v -> selStaff(mContext, mEdtHomeMeetingMan, meetingMan, true));
        mEdtHomeMeetingCompere.setOnClickListener(v -> selStaff(mContext, mEdtHomeMeetingCompere, new StaffNameIdKey(), false));
        setTimeEdit(mEdtHomeMeetingTime);
        mEdtHomeMeetingDepartment.setOnTouchListener(new SingleSelectItem(mContext, mEdtHomeMeetingDepartment, "选择部门", new String[]{"集团", "矿业", "工程", "集美"}, true));


    }

    private void setForbidEdit() {

        ForbidEdit = true;

        mEdtHomeMeetingIssue.setFocusable(false);
        mEdtHomeMeetingSite.setFocusable(false);
        mEdtHomeMeetingMan.setFocusable(false);
        mEdtHomeMeetingCompere.setFocusable(false);
        mEdtHomeMeetingContent.setFocusable(false);
        mEdtHomeMeetingTime.setFocusable(false);
        mEdtHomeMeetingDepartment.setFocusable(false);

        delmeetingPc1.setVisibility(View.GONE);
        delmeetingPc2.setVisibility(View.GONE);
        delmeetingPc3.setVisibility(View.GONE);
        delmeetingPc4.setVisibility(View.GONE);

        mBtnPersonClear.setOnClickListener(null);
        mBtnQuestion.setOnClickListener(null);
        mAddPic.setOnClickListener(null);

        mBtnPersonClear.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(@NonNull String message) {
        hideUpload();
        checkNotNull(message);
        if ("删除其他".equals(message)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("您无法删除该条会议纪要")
                    .setMessage("需要删除全部与会人员和附近才能删除该条纪要")
                    .setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
            builder.show();
            return;
        }
        UiUtils.snackbarText(message);
    }

    @Override
    public void killMyself() {
        finish();
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
//                    if (!TextUtils.isEmpty(sendKey))
//                        sendKey = sendKey.replace("&", "-");
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
    public void delMeetingMsg(String meetingId) {
        showMessage("删除完成");
        finish();
    }

    @Override
    public void hideUpload() {
        progressDialog.dismiss();
    }

    /**
     * 发送编辑结果
     *
     * @param imgUploadList
     */
    @Override
    public void imgUploadOk(List<ImgUploadResp> imgUploadList) {

        Log.e("完成", "完成");
        meetingEditReqs.setId(meetingId);
        meetingEditReqs.setCM_Num(meetingMsgResp.getCM_Num());
        meetingEditReqs.setSysPersonOld(meetingMsgResp.getSysPerson());

        progressDialog = ProgressDialog.show(this, null, "正在上传会议记录信息...");
        if (meetingQuestionList.size() > 0) {
            meetingEditReqs.setRowsCount("" + meetingQuestionList.size());
            meetingEditReqs.setRows(JsonUtils.beanToJson(meetingQuestionList));
        } else {
            meetingEditReqs.setRowsCount("0");
        }
        String FileUploader = "";
        if (attachmentListSend.size() > 0) {
            for (String attachmentStr : attachmentListSend) {
                FileUploader = FileUploader + attachmentStr;
            }
        }
        if (imgListSend.size() > 0) {
            for (String imgStr : imgListSend) {
                FileUploader = FileUploader + imgStr;
            }
        }

        if (imgUploadList.size() > 0) {
            for (ImgUploadResp imgResp : imgUploadList) {
                FileUploader = FileUploader + "^" + imgResp.getId() + "|" + imgResp.getName();
            }
        }

        meetingEditReqs.setFileUploader(FileUploader);

        mPresenter.editMeetingMsg(meetingEditReqs);

    }

    @Override
    public RxPermissions getRxPermissions() {
        return mRxPermissions;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == btn_edit_meeting) {
            if (canEdit) {
                //修改
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("保存修改")
                        .setMessage("确认提交编辑结果?")
                        .setPositiveButton("确定", (dialog, which) -> {
                            sendMeetingEdit();
                        }).setNegativeButton("返回", (dialog, which) -> {
                            dialog.dismiss();
                        });
                builder.show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("您没有编辑权限")
                        .setMessage("只有记录人和问题的执行督办人有权限编辑对应部分")
                        .setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
                builder.show();
            }
        } else if (i == btn_del_meeting) {
            if (cm_promoter.equals(getUserName())) {
                if (!TextUtils.isEmpty(mEdtHomeMeetingMan.getText().toString())
                        || attachmentListSend.size() > 0 || imgListSend.size() > 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this)
                            .setTitle("您需要清空与会人员和附件")
                            .setMessage("您需要清空与会人员和附件并保存,才能进行删除操作")
                            .setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
                    builder.show();
                    return;
                }
                //删除
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("删除纪要")
                        .setMessage("确定删除该条会议纪要?")
                        .setPositiveButton("确定", (dialog, which) -> {
                            sendDel();
                        }).setNegativeButton("返回", (dialog, which) -> {
                            dialog.dismiss();
                        });
                builder.show();
            } else {
                //不可删除
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("您没有权限删除该条纪要")
                        .setMessage("只有会议记录人有权限删除该条会议纪要")
                        .setNegativeButton("返回", (dialog, which) -> dialog.dismiss());
                builder.show();
            }
        } else if (i == btn_add_pic) {
            mAddPicView.setVisibility(View.VISIBLE);
        } else if (i == btn_new_question) {
            addQuestion();
        } else if (i == iv_meeting_1) {
            ARouter.getInstance().build("/home/ImageViewerActivity").withString("imageUrl", imgList.get(0)).navigation();
        } else if (i == iv_meeting_2) {
            ARouter.getInstance().build("/home/ImageViewerActivity").withString("imageUrl", imgList.get(1)).navigation();
        } else if (i == iv_meeting_3) {
            ARouter.getInstance().build("/home/ImageViewerActivity").withString("imageUrl", imgList.get(2)).navigation();
        } else if (i == iv_meeting_4) {
            ARouter.getInstance().build("/home/ImageViewerActivity").withString("imageUrl", imgList.get(3)).navigation();
        } else if (i == iv_delmeeting_1) {
            rlMeetingPc1.setVisibility(View.INVISIBLE);
            imgListSend.remove(0);
            if (imgListSend.size() == 0) {
                meetingPicture.setVisibility(View.GONE);
            }
        } else if (i == iv_delmeeting_2) {
            rlMeetingPc2.setVisibility(View.INVISIBLE);
            imgListSend.remove(1);
            if (imgListSend.size() == 0) {
                meetingPicture.setVisibility(View.GONE);
            }
        } else if (i == iv_delmeeting_3) {
            rlMeetingPc3.setVisibility(View.INVISIBLE);
            imgListSend.remove(2);
            if (imgListSend.size() == 0) {
                meetingPicture.setVisibility(View.GONE);
            }
        } else if (i == R.id.iv_delmeeting_4) {
            rlMeetingPc4.setVisibility(View.INVISIBLE);
            imgListSend.remove(3);
            if (imgListSend.size() == 0) {
                meetingPicture.setVisibility(View.GONE);
            }
        } else if (i == R.id.btn_person_clear) {
            mEdtHomeMeetingMan.setText("");
            meetingMan.setSendKey("");
        }
    }


    public void setMeetingMsg(MeetingMsgResp meetingMsgResp) {
        this.meetingMsgResp = meetingMsgResp;
        meetingMan = new StaffNameIdKey();
        cm_promoter = meetingMsgResp.getCM_promoter();
        meetingId = meetingMsgResp.getId();
        userName = getUserName();
        if (!cm_promoter.equals(userName)) {
            setForbidEdit();
        } else {
            canEdit = true;
            setAllowEdit();
        }

        mTvTitle.setText(meetingMsgResp.getCM_name());
        mTvHomeMeetingRecorder.setText(meetingMsgResp.getCM_promoter());

        mEdtHomeMeetingIssue.setText(meetingMsgResp.getCM_name());
        mEdtHomeMeetingSite.setText(meetingMsgResp.getCM_room());

        //20120612164750609375054239964cb|蔡福源^2012061216480515625002337f1b3ea|饶强^201206121650155156250d6bd2a0189|崔权超^20120613095142093750075d83c0983|孙爱祥^201206130953155468750585562bc1d|常笛^2012071809424314062503ff7b3c5a9|杨梦尧^20150322133951867708607dc40a0bb|维护^
        //20120426141601375528685d3e0eb2b&李大鹏^20120706091325687500052f17fd1c4&王棣^2013072313154059375001ae8574da8&葛亚楠^201602230803024781350fbb7acaeef&黄宇杰201602230804217856711b2dba297e1&黄宇环^201608110915263121197b5e16b4725&王丽娜^20170328081716366556360b7a83bf1&孙燚龙^
        String sysPerson = meetingMsgResp.getSysPerson();
        String meetingManStr = "";
        String[] split = sysPerson.split("\\^");
        for (int i = 0; i < split.length; i++) {
            int charAt = split[i].indexOf("|");
            if (charAt > 0) {
                String substring = split[i].substring(charAt + 1, split[i].length());
                if (i == split.length - 1) {
                    meetingManStr = meetingManStr + substring;
                } else {
                    meetingManStr = meetingManStr + substring + ",";
                }
            }
        }
        this.meetingMan.setName(meetingManStr);
        this.meetingMan.setSendKey(sysPerson);

        mEdtHomeMeetingMan.setText(meetingManStr);
        mEdtHomeMeetingCompere.setText(meetingMsgResp.getCM_promoter());
        mEdtHomeMeetingContent.setText(meetingMsgResp.getCM_content());
        mEdtHomeMeetingTime.setText(meetingMsgResp.getCM_starttime());
        mEdtHomeMeetingDepartment.setText(meetingMsgResp.getCM_department());
        fileUploader = meetingMsgResp.getFileUploader();

        imgList = new ArrayList<>();
        imgListSend = new ArrayList<>();
        attachmentList = new ArrayList<>();
        attachmentListSend = new ArrayList<>();


        if (!TextUtils.isEmpty(fileUploader)) {
            String[] splitImg = fileUploader.split(",");
            for (int i = 0; i < splitImg.length; i++) {
                if ((imgList.size() < 4) && (splitImg[i].contains(".jpg") || splitImg[i].contains(".png") || splitImg[i].contains(".bmp"))) {
//                    imgListSend.add(splitImg[i].substring(3, splitImg[i].length()));
                    int charAt = splitImg[i].indexOf("^");
                    if (charAt > 0) {
                        String substring = splitImg[i].substring(0, charAt);
                        substring = getString(R.string.home_base_url) + substring;
                        //   http://test.cemtn.cn/Upload/20170328082136350.jpg
                        imgList.add(substring);
                        String nameStr = splitImg[i].substring(charAt + 1, splitImg[i].length());
                        imgListSend.add("^" + splitImg[i].substring(4, charAt - nameStr.length()) + "|" +
                                splitImg[i].substring(charAt + 1, splitImg[i].length()));
                    }
                } else {
//                    attachmentListSend.add(splitImg[i]);
                    int charAt = splitImg[i].indexOf("^");
                    if (charAt > 0) {
                        String substring = splitImg[i].substring(charAt, splitImg[i].length());
                        attachmentList.add(substring);
                        String nameStr = splitImg[i].substring(charAt + 1, splitImg[i].length());
                        attachmentListSend.add("^" + splitImg[i].substring(4, charAt - nameStr.length()) + "|" +
                                splitImg[i].substring(charAt + 1, splitImg[i].length()));
                    }
                }
            }
            if (imgList.size() > 0) {
                meetingPicture.setVisibility(View.VISIBLE);
                Collections.reverse(imgList);
                Collections.reverse(imgListSend);
            }
            switch (imgList.size() >= 4 ? 4 : imgList.size()) {
                case 4: {
                    rlMeetingPc4.setVisibility(View.VISIBLE);
                    Glide.with(this).load(imgList.get(3)).into(meetingPc4);
                }
                case 3: {
                    rlMeetingPc3.setVisibility(View.VISIBLE);
                    Glide.with(this).load(imgList.get(2)).into(meetingPc3);
                }
                case 2: {
                    rlMeetingPc2.setVisibility(View.VISIBLE);
                    Glide.with(this).load(imgList.get(1)).into(meetingPc2);
                }
                case 1: {
                    rlMeetingPc1.setVisibility(View.VISIBLE);
                    Glide.with(this).load(imgList.get(0)).error(R.mipmap.home_meeting_add).into(meetingPc1);
                }
                case 0:
                    break;
            }
        }

        //展示列表
        if (attachmentList.size() > 0) {
            linearLayoutManager = new LinearLayoutManager(this);
            recyAttachment.setLayoutManager(linearLayoutManager);
            attachmentAdapter = new AttachmentAdapter(attachmentList, ForbidEdit);
            recyAttachment.setAdapter(attachmentAdapter);
            attachmentAdapter.setDelFileListenerr(position -> {
                attachmentList.remove(position);
                attachmentListSend.remove(position);
                attachmentAdapter.notifyDataSetChanged();
            });
        } else {
            rlAttachment.setVisibility(View.GONE);
        }

        meetingQuestionList = meetingMsgResp.getNeedDeal();
        for (int i = 0; i < meetingQuestionList.size(); i++) {
            addQuestion(i);
        }
    }


    private void sendMeetingEdit() {

        if (checkNullMsg() && getQuestionBean()) {
            progressDialog = ProgressDialog.show(this, null, "正在上传图片...");
            // 发送图片
            mPresenter.sendPicture(mAddPicView.getListData());
        }
    }

    private void sendDel() {
        progressDialog = ProgressDialog.show(this, null, "正在删除纪要...");
        mPresenter.delMeetingMsg(meetingId);
    }


    private void addQuestion(int position) {

        MtQuestionReqs meetingQuestion = meetingQuestionList.get(position);
        StaffNameIdKey StaffNameIdKey = new StaffNameIdKey();
        int size = meetingQuestionList.size();
        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_met_question, null);
        //选择部门
        EditText edtDepartment = (EditText) inflate.findViewById(R.id.edt_question_department);
        edtDepartment.setText(meetingQuestion.getExecutiveDepartment());
//        edtDepartment.setFocusable(false);
        //人员选择
        EditText edtExecutor = (EditText) inflate.findViewById(R.id.edt_question_executor);
        edtExecutor.setText(meetingQuestion.getExecutorStr());
        EditText edtSupervisor = (EditText) inflate.findViewById(R.id.edt_question_supervisor);
        edtSupervisor.setText(meetingQuestion.getDuBanPerson());
        //删除布局
        Button delQuestion = (Button) inflate.findViewById(R.id.btn_del_question);
        //要求时间
        EditText predictTime = (EditText) inflate.findViewById(R.id.edt_question_predict_time);
        predictTime.setText(meetingQuestion.getFinishTime());
        predictTime.setFocusable(false);
        //督办时间
        EditText superviseTime = (EditText) inflate.findViewById(R.id.edt_supervise_time);
        superviseTime.setText(meetingQuestion.getDuBanTime());
        superviseTime.setFocusable(false);
        //解决时间
        EditText finishTime = (EditText) inflate.findViewById(R.id.edt_question_finish_time);
        finishTime.setText(meetingQuestion.getJiejueTime());
        finishTime.setFocusable(false);
        //问题内容
        EditText questionContent = (EditText) inflate.findViewById(R.id.edt_question_content);
        questionContent.setText(meetingQuestion.getRecord());
//        questionContent.setEnabled(false);
        //问题解决方法
        EditText questionMethod = (EditText) inflate.findViewById(R.id.edt_question_method);
        questionMethod.setText(meetingQuestion.getRecordFangfa());
//        questionContent.setEnabled(false);
//        questionMethod.setFocusable(false);
        //督办意见
        EditText superviseOpinion = (EditText) inflate.findViewById(R.id.edt_supervise_opinion);
        superviseOpinion.setText(meetingQuestion.getDubanZhishi());
//        questionContent.setEnabled(false);
//        questionMethod.setFocusable(false);

        RadioButton rbFinish = (RadioButton) inflate.findViewById(rb_finish);
        RadioButton rbDoing = (RadioButton) inflate.findViewById(R.id.rb_doing);
        RadioButton rbLongTime = (RadioButton) inflate.findViewById(rb_long_time);
        rbLongTime.setClickable(false);
        rbDoing.setClickable(false);
        rbFinish.setClickable(false);
        if ("是".equals(meetingQuestion.getWhether())) {
            rbFinish.setChecked(true);
        } else if ("否".equals(meetingQuestion.getWhether())) {
            rbDoing.setChecked(true);
        } else if ("长期".equals(meetingQuestion.getWhether())) {
            rbLongTime.setChecked(true);
        }

        RadioButton rbAgree = (RadioButton) inflate.findViewById(rb_agree);
        RadioButton rbRefuse = (RadioButton) inflate.findViewById(R.id.rb_refuse);
        rbAgree.setClickable(false);
        rbRefuse.setClickable(false);
        if ("同意".equals(meetingQuestion.getJiejueState())) {
            rbAgree.setChecked(true);
        } else if ("不同意".equals(meetingQuestion.getJiejueState())) {
            rbRefuse.setChecked(true);
        }


        if (userName.equals(cm_promoter)) {
            canEdit = true;
            delQuestion.setOnClickListener(v -> {
                mLlQuestion.removeView(inflate);
                meetingQuestionList.remove(size - 1);
            });

//            edtDepartment.setOnTouchListener(new SingleSelectItem(mContext, edtDepartment, "选择部门", new String[]{"集团", "矿业", "工程", "集美"}, false));
            edtExecutor.setOnClickListener(v -> selStaff(mContext, edtExecutor, StaffNameIdKey, true));
            edtSupervisor.setOnClickListener(v -> selStaff(mContext, edtSupervisor, StaffNameIdKey, false));

            setTimeEdit(predictTime);

            setSupervisor(superviseTime, questionMethod, rbAgree, rbRefuse);
            setExecutorEdit(finishTime, questionContent, rbFinish, rbDoing, rbLongTime);

        } else if (userName.equals(meetingQuestion.getDuBanPerson())) {
            canEdit = true;
            setSupervisor(superviseTime, questionMethod, rbAgree, rbRefuse);
            questionContent.setFocusable(false);
            questionMethod.setFocusable(false);
            edtDepartment.setFocusable(false);
        } else if (meetingQuestion.getExecutorStr().contains(userName)) {
            canEdit = true;
            setExecutorEdit(finishTime, questionContent, rbFinish, rbDoing, rbLongTime);
            superviseOpinion.setFocusable(false);
//            questionMethod.setFocusable(false);
            edtDepartment.setFocusable(false);
        } else {
//            canEdit = false;
            questionContent.setFocusable(false);
            questionMethod.setFocusable(false);
            superviseOpinion.setFocusable(false);
            edtDepartment.setFocusable(false);
        }

        mLlQuestion.addView(inflate);
//        meetingQuestionList.r(meetingQuestion);
        mSlvMeeting.fullScroll(ScrollView.FOCUS_DOWN);
    }

    private void setExecutorEdit(EditText finishTime, EditText questionContent, RadioButton rbFinish, RadioButton rbDoing, RadioButton rbLongTime) {
//        questionContent.setEnabled(true);
//        questionContent.setFocusable(true);
        setTimeEdit(finishTime);
        rbLongTime.setClickable(true);
        rbDoing.setClickable(true);
        rbFinish.setClickable(true);
    }

    private void setSupervisor(EditText superviseTime, EditText questionMethod, RadioButton rbAgree, RadioButton rbRefuse) {
//        questionMethod.setEnabled(true);
//        questionMethod.setFocusable(true);
        setTimeEdit(superviseTime);
        rbAgree.setClickable(true);
        rbRefuse.setClickable(true);
    }

    private void setTimeEdit(EditText finishTime) {
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


    /**
     * 获取会议信息
     */
    private boolean checkNullMsg() {


        meetingEditReqs.setFileUploader(meetingMsgResp.getFileUploader());
        meetingEditReqs.setFileUploaderOld(meetingMsgResp.getFileUploaderOld());
        meetingEditReqs.setCM_State("发布");
        meetingEditReqs.setRowsCount("" + meetingQuestionList.size());

        String issue = mEdtHomeMeetingIssue.getText().toString().trim();
        if (TextUtils.isEmpty(issue)) {
            shortToast("请填写会议名称");
            return false;
        }
        meetingEditReqs.setCM_name(issue);
        String site = mEdtHomeMeetingSite.getText().toString().trim();
        meetingEditReqs.setCM_room(site);
//        String person = mEdtHomeMeetingMan.getText().toString().trim();
//        if (TextUtils.isEmpty(person)) {
//            shortToast("请选择与会人员");
//            return false;
//        }
        meetingEditReqs.setSysPerson(meetingMan.getSendKey());
        String compere = mEdtHomeMeetingCompere.getText().toString().trim();
        if (TextUtils.isEmpty(compere)) {
            shortToast("请选择会议主持人");
            return false;
        }
        meetingEditReqs.setCM_person(compere);
        String content = mEdtHomeMeetingContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            shortToast("请填写会议内容");
            return false;
        }
        meetingEditReqs.setCM_content(content);
        String meetingTime = mEdtHomeMeetingTime.getText().toString().trim();
        if (TextUtils.isEmpty(meetingTime)) {
            shortToast("请选择会议时间");
            return false;
        }
        meetingEditReqs.setCM_starttime(meetingTime);
        String department = mEdtHomeMeetingDepartment.getText().toString().trim();
        if (TextUtils.isEmpty(department)) {
            shortToast("请选择参会部门");
            return false;
        }
        meetingEditReqs.setCM_department(department);
        meetingEditReqs.setCM_promoter(mTvHomeMeetingRecorder.getText().toString());
        return true;
    }


    /**
     * 选择员工弹窗
     *
     * @param mContext           上下文
     * @param mEdtMeetingCompere edittext
     * @param meetingCompere     后台需要的数据类型
     * @param b                  是否多选
     */
    private void selStaff(Context mContext, EditText mEdtMeetingCompere, StaffNameIdKey meetingCompere, boolean b) {
        DepartmentPop departmentPop = new DepartmentPop(mContext, mEdtMeetingCompere, meetingCompere, b);
        departmentPop.setOnItemClickListener((Department, editText, meetingPerson, moreCheck) -> {
            departmentPop.dismiss();
            mPresenter.getLinkmanMsg(Department, editText, meetingPerson, moreCheck);
        });
        departmentPop.showAtLocation(this.mContext.findViewById(R.id.rl_meeting_edit),
                Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    /**
     * 获取问题内容
     */
    private boolean getQuestionBean() {
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
            //督办指示
            EditText superviseOpinion = (EditText) childAt.findViewById(R.id.edt_supervise_opinion);
            meetingQuestion.setDubanZhishi(superviseOpinion.getText().toString());
            //督办时间
            EditText superviseTime = (EditText) childAt.findViewById(R.id.edt_supervise_time);
            meetingQuestion.setDuBanTime(superviseTime.getText().toString());
            //问题状态
            RadioGroup questionState = (RadioGroup) childAt.findViewById(R.id.rg_question_state);
            int questionStateId = questionState.getCheckedRadioButtonId();
            if (questionStateId == R.id.rb_finish) {
                meetingQuestion.setWhether("是");
            } else if (questionStateId == R.id.rb_doing) {
                meetingQuestion.setWhether("否");
            } else if (questionStateId == R.id.rb_long_time) {
                meetingQuestion.setWhether("长期");
            }
            //督办状态
            RadioGroup superviseState = (RadioGroup) childAt.findViewById(R.id.rg_supervise_state);
            int superviseStateId = superviseState.getCheckedRadioButtonId();
            if (superviseStateId == R.id.rb_agree) {
                meetingQuestion.setJiejueState("同意");
            } else if (superviseStateId == R.id.rb_refuse) {
                meetingQuestion.setJiejueState("不同意");
            }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x01 && resultCode == Activity.RESULT_OK) {
            Uri vData = data.getData();
            Log.e("yzy", "onActivityResult: " + vData);
            mAddPicView.addPicture(vData);
        }
    }

    private void addQuestion() {
        MtQuestionReqs meetingQuestion = new MtQuestionReqs();
        StaffNameIdKey StaffNameIdKey = new StaffNameIdKey();
        int size = meetingQuestionList.size();
        View inflate = LayoutInflater.from(this).inflate(R.layout.home_add_met_question, null);
        //选择部门
        EditText edtDepartment = (EditText) inflate.findViewById(R.id.edt_question_department);
        edtDepartment.setOnTouchListener(new SingleSelectItem(mContext, edtDepartment, "选择部门", new String[]{"集团", "矿业", "工程", "集美"}, true));
        //人员选择
        EditText edtExecutor = (EditText) inflate.findViewById(R.id.edt_question_executor);
        edtExecutor.setOnClickListener(v -> selStaff(mContext, edtExecutor, StaffNameIdKey, true));
        EditText edtSupervisor = (EditText) inflate.findViewById(R.id.edt_question_supervisor);
        edtSupervisor.setOnClickListener(v -> selStaff(mContext, edtSupervisor, StaffNameIdKey, false));
        //删除布局
        Button delQuestion = (Button) inflate.findViewById(R.id.btn_del_question);
        delQuestion.setOnClickListener(v -> {
            mLlQuestion.removeView(inflate);
            meetingQuestionList.remove(size);
        });

        EditText predictTime = (EditText) inflate.findViewById(R.id.edt_question_predict_time);
        predictTime.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Calendar c = Calendar.getInstance();
                new DateTimePickDialog(mContext, c).setOnDateTimeSetListener((dp, tp, year, monthOfYear, dayOfMonth, hourOfDay, minute) -> {
                    predictTime.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + " "
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

        EditText superviseTime = (EditText) inflate.findViewById(R.id.edt_supervise_time);
        superviseTime.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Calendar c = Calendar.getInstance();
                new DateTimePickDialog(mContext, c).setOnDateTimeSetListener((dp, tp, year, monthOfYear, dayOfMonth, hourOfDay, minute) -> {
                    superviseTime.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + " "
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
        EditText finishTime = (EditText) inflate.findViewById(R.id.edt_question_finish_time);
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


        mLlQuestion.addView(inflate);
        meetingQuestionList.add(meetingQuestion);
        mSlvMeeting.fullScroll(ScrollView.FOCUS_DOWN);
    }
}
