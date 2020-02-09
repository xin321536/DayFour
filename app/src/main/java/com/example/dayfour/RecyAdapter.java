package com.example.dayfour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

class RecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Bean.DataBean.DatasBean> list;
    Context context;

    public RecyAdapter(List<Bean.DataBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
                    View view = LayoutInflater.from(context).inflate(R.layout.item1, null);
                    return new ViewHodle1(view);
                } else {
                    View view = LayoutInflater.from(context).inflate(R.layout.item2, null);
                    return new ViewHodle2(view);
                }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
 int type = getItemViewType(position);

         if (type == 0) {
             ViewHodle1 v1 = (ViewHodle1) holder;
             v1.title.setText(list.get(position).getTitle());
             Glide.with(context)
                     .load(list.get(position).getEnvelopePic())
                     .apply(RequestOptions.bitmapTransform(new CircleCrop()))//圆形
                     // .apply(RequestOptions.bitmapTransform(new RoundedCorners(50))) //圆角 自定义设置
                     .into(v1.image);
         } else {
             ViewHodle2 v2 = (ViewHodle2) holder;
             v2.title1.setText(list.get(position).getTitle());
         }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemLongClickLis.onItemLongClick(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
     @Override
         public int getItemViewType(int position) {
             if (position % 2 == 0) {
                 return 0;
             } else {
                 return 1;
             }
         }

    private class ViewHodle1 extends RecyclerView.ViewHolder {

        private final TextView title;
        private final ImageView image;

        public ViewHodle1(View view) {
            super(view);
            image = view.findViewById(R.id.image);
            title = view.findViewById(R.id.title);
        }
    }

    private class ViewHodle2 extends RecyclerView.ViewHolder {

        private final TextView title1;

        public ViewHodle2(View view) {
            super(view);
            title1 = view.findViewById(R.id.title1);

        }
    }
     private OnItemLongClickLis onItemLongClickLis;

         public void setOnItemLongClickLis(OnItemLongClickLis onItemLongClickLis) {
             this.onItemLongClickLis = onItemLongClickLis;
         }

         public interface OnItemLongClickLis{
             void onItemLongClick(int position);
         }


}
