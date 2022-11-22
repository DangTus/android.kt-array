package com.dmt.dangtus.kiemtra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dmt.dangtus.kiemtra.R;
import com.dmt.dangtus.kiemtra.model.CayXanh;

import java.util.List;

public class CayXanhAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<CayXanh> cayXanhList;

    public CayXanhAdapter(Context context, int layout, List<CayXanh> cayXanhList) {
        this.context = context;
        this.layout = layout;
        this.cayXanhList = cayXanhList;
    }

    @Override
    public int getCount() {
        return cayXanhList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);

            holder = new ViewHolder();
            //anh xa
            holder.txtTenThuongGoi = view.findViewById(R.id.tenthuonggoi_textview);
            holder.txtTenKhoaHoc = view.findViewById(R.id.tenkhoahoc_textview);
            holder.txtDacTinh = view.findViewById(R.id.dactinh_textview);
            holder.txtMauLa = view.findViewById(R.id.maula_textview);
            holder.imvHinhAnh = view.findViewById(R.id.hinhanh_imageview);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        //gan gia tri
        CayXanh cayXanh = cayXanhList.get(i);
        holder.txtTenThuongGoi.setText(cayXanh.getTenThuongGoi());
        holder.txtTenKhoaHoc.setText("Tên khoa học: " + cayXanh.getTenKhoaHoc());
        holder.txtDacTinh.setText("Đặc tính: " + cayXanh.getDacTinh());
        holder.txtMauLa.setText("Màu lá: " + cayXanh.getMauLa());
        Glide.with(context).load(cayXanh.getHinhAnh()).into(holder.imvHinhAnh);

        return view;
    }

    private class ViewHolder {
        TextView txtTenThuongGoi, txtTenKhoaHoc, txtDacTinh, txtMauLa;
        ImageView imvHinhAnh;
    }
}
