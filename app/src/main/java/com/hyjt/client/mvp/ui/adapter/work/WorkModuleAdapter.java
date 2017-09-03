package com.hyjt.client.mvp.ui.adapter.work;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyjt.home.R;

import java.util.List;


/**
 * Created by hbh on 2017/4/20.
 * 适配器
 */

public class WorkModuleAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context context;
    private List<ModuleBean> ModuleBeanList;
    private LayoutInflater mInflater;
    private OnScrollListener mOnScrollListener;
    private OnPmListener mOnPmListener;
    private OnCmListener mOnCmListener;

    public WorkModuleAdapter(Context context, List<ModuleBean> ModuleBeanList) {
        this.context = context;
        this.ModuleBeanList = ModuleBeanList;
        this.mInflater = LayoutInflater.from(context);
    }

    public int getImageID(String name) {
        int id = context.getResources().getIdentifier(name, "raw",
                context.getPackageName());
        return id;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ModuleBean.PARENT_ITEM:
                view = mInflater.inflate(R.layout.home_item_work_parent, parent, false);
                return new ParentViewHolder(context, view);
            case ModuleBean.CHILD_ITEM:
                view = mInflater.inflate(R.layout.home_item_work_child, parent, false);
                return new ChildViewHolder(context, view);
            case ModuleBean.EMPTY_ITEM:
                view = mInflater.inflate(R.layout.home_item_work_empty, parent, false);
                return new BaseViewHolder(view);
            default:
                view = mInflater.inflate(R.layout.home_item_work_parent, parent, false);
                return new ParentViewHolder(context, view);
        }
    }

    /**
     * 根据不同的类型绑定View
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        ModuleBean moduleBean = ModuleBeanList.get(position);
//        moduleBean.get
        switch (getItemViewType(position)) {
            case ModuleBean.PARENT_ITEM:
                ParentViewHolder parentViewHolder = (ParentViewHolder) holder;
                parentViewHolder.bindView(position, itemClickListener);

                parentViewHolder.expendName.setText(moduleBean.getName());

                if (moduleBean.getChildOneTxt() == null){
                    parentViewHolder.expendMore.setVisibility(View.GONE);
                } else {
                    parentViewHolder.expendMore.setVisibility(View.VISIBLE);
                }
                String parentOneTxt = moduleBean.getParentOneTxt();
                if ("".equals(parentOneTxt) || parentOneTxt == null) {
                    parentViewHolder.llParent_1.setVisibility(View.INVISIBLE);
                } else {
                    parentViewHolder.ivParent_1.setImageResource(getImageID(moduleBean.getParentOneIv()));
                    parentViewHolder.tvParent_1.setText(parentOneTxt);
                    parentViewHolder.rlParent_1.setOnClickListener(v ->
                            mOnPmListener.onItemClick(moduleBean.getParentOneID()));
                }
                String parentTwoTxt = moduleBean.getParentTwoTxt();
                if ("".equals(parentTwoTxt) || parentTwoTxt == null) {
                    parentViewHolder.llParent_2.setVisibility(View.INVISIBLE);
                } else {
                    parentViewHolder.ivParent_2.setImageResource(getImageID(moduleBean.getParentTwoIv()));
                    parentViewHolder.tvParent_2.setText(parentTwoTxt);
                    parentViewHolder.rlParent_2.setOnClickListener(v ->
                            mOnPmListener.onItemClick(moduleBean.getParentTwoID()));
                }
                String parentThreeTxt = moduleBean.getParentThreeTxt();
                if ("".equals(parentThreeTxt) || parentThreeTxt == null) {
                    parentViewHolder.llParent_3.setVisibility(View.INVISIBLE);
                } else {
                    parentViewHolder.ivParent_3.setImageResource(getImageID(moduleBean.getParentThreeIv()));
                    parentViewHolder.tvParent_3.setText(parentThreeTxt);
                    parentViewHolder.rlParent_3.setOnClickListener(v ->
                            mOnPmListener.onItemClick(moduleBean.getParentThreeID()));
                }
                String parentFourTxt = moduleBean.getParentFourTxt();
                if ("".equals(parentFourTxt) || parentFourTxt == null) {
                    parentViewHolder.llParent_4.setVisibility(View.INVISIBLE);
                } else {
                    parentViewHolder.ivParent_4.setImageResource(getImageID(moduleBean.getParentFourIv()));
                    parentViewHolder.tvParent_4.setText(parentFourTxt);
                    parentViewHolder.rlParent_4.setOnClickListener(v ->
                            mOnPmListener.onItemClick(moduleBean.getParentFourID()));
                }
                String parentFiveTxt = moduleBean.getParentFiveTxt();
                if ("".equals(parentFiveTxt) || parentFiveTxt == null) {
                    parentViewHolder.llParent_5.setVisibility(View.INVISIBLE);
                } else {
                    parentViewHolder.ivParent_5.setImageResource(getImageID(moduleBean.getParentFiveIv()));
                    parentViewHolder.tvParent_5.setText(parentFiveTxt);
                    parentViewHolder.rlParent_5.setOnClickListener(v ->
                            mOnPmListener.onItemClick(moduleBean.getParentFiveID()));
                }
                break;
            case ModuleBean.CHILD_ITEM:
                ChildViewHolder childViewHolder = (ChildViewHolder) holder;
                childViewHolder.bindView(position);
                ModuleBean childModuleBean = ModuleBeanList.get(position-1);
                String childOneTxt = childModuleBean.getChildOneTxt();
                if ("".equals(childOneTxt) || childOneTxt == null) {
                    childViewHolder.llChildOne.setVisibility(View.INVISIBLE);
                } else {
                    childViewHolder.ivChild_1.setImageResource(getImageID(childModuleBean.getChildOneIv()));
                    childViewHolder.childOneText.setText(childOneTxt);
                    childViewHolder.rlChildOne.setOnClickListener(v ->
                            mOnCmListener.onItemClick(childModuleBean.getChildOneID()));
                }
                String childTwoTxt = childModuleBean.getChildTwoTxt();
                if ("".equals(childTwoTxt) || childTwoTxt == null) {
                    childViewHolder.llChildTwo.setVisibility(View.INVISIBLE);
                } else {
                    childViewHolder.ivChild_2.setImageResource(getImageID(childModuleBean.getChildTwoIv()));
                    childViewHolder.childTwoText.setText(childTwoTxt);
                    childViewHolder.rlChildTwo.setOnClickListener(v ->
                            mOnCmListener.onItemClick(childModuleBean.getChildTwoID()));
                }
                String childThreeTxt = childModuleBean.getChildThreeTxt();
                if ("".equals(childThreeTxt) || childThreeTxt == null) {
                    childViewHolder.llChildThree.setVisibility(View.INVISIBLE);
                } else {
                    childViewHolder.ivChild_3.setImageResource(getImageID(childModuleBean.getChildThreeIv()));
                    childViewHolder.childThreeText.setText(childThreeTxt);
                    childViewHolder.rlChildThree.setOnClickListener(v ->
                            mOnCmListener.onItemClick(childModuleBean.getChildThreeID()));
                }
                String childFourTxt = childModuleBean.getChildFourTxt();
                if ("".equals(childFourTxt) || childFourTxt == null) {
                    childViewHolder.llChildFour.setVisibility(View.INVISIBLE);
                } else {
                    childViewHolder.ivChild_4.setImageResource(getImageID(childModuleBean.getChildFourIv()));
                    childViewHolder.childFourText.setText(childFourTxt);
                    childViewHolder.rlChildFour.setOnClickListener(v ->
                            mOnCmListener.onItemClick(childModuleBean.getChildFourID()));
                }
                String childFiveTxt = childModuleBean.getChildFiveTxt();
                if ("".equals(childFiveTxt) || childFiveTxt == null) {
                    childViewHolder.llChildFive.setVisibility(View.INVISIBLE);
                } else {
                    childViewHolder.ivChild_5.setImageResource(getImageID(childModuleBean.getChildFiveIv()));
                    childViewHolder.childFiveText.setText(childFiveTxt);
                    childViewHolder.rlChildFour.setOnClickListener(v ->
                            mOnCmListener.onItemClick(childModuleBean.getChildFiveID()));
                }
                break;
            case ModuleBean.EMPTY_ITEM:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return ModuleBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return ModuleBeanList.get(position).getType();
    }


    /**
     * Created by hbh on 2017/4/20.
     * 父布局Item点击监听接口
     */
    interface ItemClickListener {
        /**
         * 展开子Item
         */
        void onExpandChildren(ModuleBean bean);

        /**
         * 隐藏子Item
         */
        void onHideChildren(ModuleBean bean);
    }


    private ItemClickListener itemClickListener = new ItemClickListener() {
        @Override
        public void onExpandChildren(ModuleBean bean) {
            int position = getCurrentPosition(bean.getID());//确定当前点击的item位置
            ModuleBean children = getChildModuleBean(bean);//获取要展示的子布局数据对象，注意区分onHideChildren方法中的getChildBean()。
            if (children == null) {
                return;
            }
            add(children, position + 1);//在当前的item下方插入
            if (position == ModuleBeanList.size() - 2 && mOnScrollListener != null) { //如果点击的item为最后一个
                mOnScrollListener.scrollTo(position + 1);//向下滚动，使子布局能够完全展示
            }
        }

        @Override
        public void onHideChildren(ModuleBean bean) {
            int position = getCurrentPosition(bean.getID());//确定当前点击的item位置
//            ModuleBean children = bean.getChildBean();//获取子布局对象
//            if (children == null) {
//                return;
//            }
            remove(position + 1);//删除
            if (mOnScrollListener != null) {
                mOnScrollListener.scrollTo(position);
            }
        }
    };

    /**
     * 在父布局下方插入一条数据
     */
    public void add(ModuleBean bean, int position) {
        ModuleBeanList.add(position, bean);
        notifyItemInserted(position);
    }

    /**
     * 移除子布局数据
     */
    public void remove(int position) {
        ModuleBeanList.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 确定当前点击的item位置并返回
     */
    protected int getCurrentPosition(String uuid) {
        for (int i = 0; i < ModuleBeanList.size(); i++) {
            if (uuid.equalsIgnoreCase(ModuleBeanList.get(i).getID())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 封装子布局数据对象并返回
     * 注意，此处只是重新封装一个ModuleBean对象，为了标注Type为子布局数据，进而展开，展示数据
     * 要和onHideChildren方法里的getChildBean()区分开来
     */
    private ModuleBean getChildModuleBean(ModuleBean bean) {
        ModuleBean child = new ModuleBean();
        child.setType(1);
        child.setParentOneTxt(bean.getParentOneTxt());
        child.setParentTwoTxt(bean.getParentTwoTxt());
        child.setParentThreeTxt(bean.getParentThreeTxt());
        child.setParentFourTxt(bean.getParentFourTxt());
        child.setParentFiveTxt(bean.getParentFiveTxt());
        child.setChildOneTxt(bean.getChildOneTxt());
        child.setChildTwoTxt(bean.getChildTwoTxt());
        child.setChildThreeTxt(bean.getChildThreeTxt());
        child.setChildFourTxt(bean.getChildFourTxt());
        child.setChildFiveTxt(bean.getChildFiveTxt());
        return child;
    }

    /**
     * 滚动监听接口
     */
    public interface OnScrollListener {
        void scrollTo(int pos);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }

    /**
     * 父布局监听接口
     */
    public interface OnPmListener {
        void onItemClick(int position);
    }

    public void setOnPmListener(OnPmListener OnPmListener) {
        this.mOnPmListener = OnPmListener;
    }

    /**
     * 子布局监听接口
     */
    public interface OnCmListener {
        void onItemClick(int position);
    }

    public void setOnCmListener(OnCmListener OnCmListener) {
        this.mOnCmListener = OnCmListener;
    }


    private class ParentViewHolder extends BaseViewHolder {

        private View view;
        private View parentDashedView;
        private TextView expendMore;
        private TextView expendName;
        private RelativeLayout rlParent_1;
        private RelativeLayout rlParent_2;
        private RelativeLayout rlParent_3;
        private RelativeLayout rlParent_4;
        private RelativeLayout rlParent_5;
        private ImageView ivParent_1;
        private ImageView ivParent_2;
        private ImageView ivParent_3;
        private ImageView ivParent_4;
        private ImageView ivParent_5;
        private TextView tvParent_1;
        private TextView tvParent_2;
        private TextView tvParent_3;
        private TextView tvParent_4;
        private TextView tvParent_5;
        private LinearLayout llParent_1;
        private LinearLayout llParent_2;
        private LinearLayout llParent_3;
        private LinearLayout llParent_4;
        private LinearLayout llParent_5;

        private ParentViewHolder(Context context, View itemView) {
            super(itemView);
            this.view = itemView;
        }

        private void bindView(final int pos, final ItemClickListener listener) {
            ModuleBean moduleBean = ModuleBeanList.get(pos);
            expendMore = (TextView) view.findViewById(R.id.tv_expend_more);
            expendName = (TextView) view.findViewById(R.id.tv_expend_name);
            parentDashedView = view.findViewById(R.id.parent_dashed_view);

            llParent_1 = (LinearLayout) view.findViewById(R.id.ll_parent_1);
            llParent_2 = (LinearLayout) view.findViewById(R.id.ll_parent_2);
            llParent_3 = (LinearLayout) view.findViewById(R.id.ll_parent_3);
            llParent_4 = (LinearLayout) view.findViewById(R.id.ll_parent_4);
            llParent_5 = (LinearLayout) view.findViewById(R.id.ll_parent_5);

            tvParent_1 = (TextView) view.findViewById(R.id.tv_parent_1);
            tvParent_2 = (TextView) view.findViewById(R.id.tv_parent_2);
            tvParent_3 = (TextView) view.findViewById(R.id.tv_parent_3);
            tvParent_4 = (TextView) view.findViewById(R.id.tv_parent_4);
            tvParent_5 = (TextView) view.findViewById(R.id.tv_parent_5);

            ivParent_1 = (ImageView) view.findViewById(R.id.iv_parent_1);
            ivParent_2 = (ImageView) view.findViewById(R.id.iv_parent_2);
            ivParent_3 = (ImageView) view.findViewById(R.id.iv_parent_3);
            ivParent_4 = (ImageView) view.findViewById(R.id.iv_parent_4);
            ivParent_5 = (ImageView) view.findViewById(R.id.iv_parent_5);

            rlParent_1 = (RelativeLayout) view.findViewById(R.id.rl_parent_1);
            rlParent_2 = (RelativeLayout) view.findViewById(R.id.rl_parent_2);
            rlParent_3 = (RelativeLayout) view.findViewById(R.id.rl_parent_3);
            rlParent_4 = (RelativeLayout) view.findViewById(R.id.rl_parent_4);
            rlParent_5 = (RelativeLayout) view.findViewById(R.id.rl_parent_5);

            //父布局OnClick监听
            expendMore.setOnClickListener(view1 -> {
                if (listener != null) {
                    if (moduleBean.isExpand()) {
                        listener.onHideChildren(moduleBean);
                        parentDashedView.setVisibility(View.VISIBLE);
                        expendMore.setText("展开");
                        moduleBean.setExpand(false);
                    } else {
                        listener.onExpandChildren(moduleBean);
                        parentDashedView.setVisibility(View.INVISIBLE);
                        expendMore.setText("收起");
                        moduleBean.setExpand(true);
                    }
                }
            });
        }

    }


    private class ChildViewHolder extends BaseViewHolder {

        private View view;
        private TextView childOneText;
        private TextView childTwoText;
        private TextView childThreeText;
        private TextView childFourText;
        private TextView childFiveText;

        private LinearLayout llChildFive;
        private LinearLayout llChildFour;
        private LinearLayout llChildThree;
        private LinearLayout llChildTwo;
        private LinearLayout llChildOne;

        private RelativeLayout rlChildFive;
        private RelativeLayout rlChildFour;
        private RelativeLayout rlChildThree;
        private RelativeLayout rlChildTwo;
        private RelativeLayout rlChildOne;

        private ImageView ivChild_1;
        private ImageView ivChild_2;
        private ImageView ivChild_3;
        private ImageView ivChild_4;
        private ImageView ivChild_5;

        private ChildViewHolder(Context context, View itemView) {
            super(itemView);
            this.view = itemView;
        }

        private void bindView(final int pos) {

            childOneText = (TextView) view.findViewById(R.id.tv_child_1);
            childTwoText = (TextView) view.findViewById(R.id.tv_child_2);
            childThreeText = (TextView) view.findViewById(R.id.tv_child_3);
            childFourText = (TextView) view.findViewById(R.id.tv_child_4);
            childFiveText = (TextView) view.findViewById(R.id.tv_child_5);

            ivChild_1 = (ImageView) view.findViewById(R.id.iv_child_1);
            ivChild_2 = (ImageView) view.findViewById(R.id.iv_child_2);
            ivChild_3 = (ImageView) view.findViewById(R.id.iv_child_3);
            ivChild_4 = (ImageView) view.findViewById(R.id.iv_child_4);
            ivChild_5 = (ImageView) view.findViewById(R.id.iv_child_5);

            llChildOne = (LinearLayout) view.findViewById(R.id.ll_child_1);
            llChildTwo = (LinearLayout) view.findViewById(R.id.ll_child_2);
            llChildThree = (LinearLayout) view.findViewById(R.id.ll_child_3);
            llChildFour = (LinearLayout) view.findViewById(R.id.ll_child_4);
            llChildFive = (LinearLayout) view.findViewById(R.id.ll_child_5);

            rlChildOne = (RelativeLayout) view.findViewById(R.id.rl_child_1);
            rlChildTwo = (RelativeLayout) view.findViewById(R.id.rl_child_2);
            rlChildThree = (RelativeLayout) view.findViewById(R.id.rl_child_3);
            rlChildFour = (RelativeLayout) view.findViewById(R.id.rl_child_4);
            rlChildFive = (RelativeLayout) view.findViewById(R.id.rl_child_5);
//            childOneText.setText(moduleBean.getChildOneTxt());
//            childTwoText.setText(moduleBean.getChildTwoTxt());
//            childThreeText.setText(moduleBean.getChildThreeTxt());
//            childFourText.setText(moduleBean.getChildFourTxt());
//            childFiveText.setText(moduleBean.getChildFiveTxt());

        }
    }


}
