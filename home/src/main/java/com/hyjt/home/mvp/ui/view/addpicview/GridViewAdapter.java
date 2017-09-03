package com.hyjt.home.mvp.ui.view.addpicview;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hyjt.home.R;

import java.io.IOException;


/**
 * Created by yangzhenyu on 17-3-8.
 */

public class GridViewAdapter extends BaseViewHolderAdapter<Uri, ViewHolder> {
    private AddPicCallBack mAddPicCallBack;


    public GridViewAdapter(Context ctx, AddPicCallBack pAddPicCallBack) {
        super(ctx);
        this.mAddPicCallBack = pAddPicCallBack;
    }


    @Override
    public View createItemView(LayoutInflater inflater, int position) {
        return inflater.inflate(R.layout.home_item_report, null);
    }

    @Override
    public ViewHolder createAndBindViewHolder(View view, int position) {
        ImageView iv = (ImageView) view.findViewById(R.id.report_item_iv);
        Button bt = (Button) view.findViewById(R.id.report_item_bt);
        return new ViewHolder(iv, bt);
    }

    public String uriToRealPath(Context context, Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri,
                new String[]{MediaStore.Images.Media.DATA},
                null,
                null,
                null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
        return path;
    }

    @Override
    public void bindDataToView(ViewHolder holder, Uri pUri, final int position) {
//        Glide.with(mCtx).load(pUri).into(holder.iv);
//        Glide.with(mCtx).load(pUri).error(R.mipmap.home_meeting_add).into(holder.iv);
        if (pUri.toString().equals("")) {
            holder.iv.setImageResource(R.mipmap.home_meeting_add);
        } else {
            try {
                ContentResolver resolver = mCtx.getContentResolver();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(resolver, pUri);
                holder.iv.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        holder.iv.setOnClickListener(null);
        holder.bt.setVisibility(View.VISIBLE);
        holder.bt.setOnClickListener(pView -> mAddPicCallBack.onDeleteClick(position));
        if (pUri.toString().equals("")) {
            holder.iv.setOnClickListener(pView -> mAddPicCallBack.onAddClick());
            holder.bt.setVisibility(View.INVISIBLE);
        }
    }

}


class ViewHolder {
    ImageView iv;
    Button bt;

    ViewHolder(ImageView pIv, Button pBt) {
        iv = pIv;
        bt = pBt;
    }
}
