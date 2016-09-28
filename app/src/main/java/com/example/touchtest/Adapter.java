package com.example.touchtest;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 肖荣 on 2016/9/27.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements View.OnClickListener, SlidingPaneLayout.PanelSlideListener {
    private List<String> list;
    private Context context;
    private RecyclerView recyclerView;

    public Adapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent,false);
//        view.setOnTouchListener(this);
        ViewHolder holder = new ViewHolder(view);
        //删除按钮删除这个item,还要考虑复用问题
        holder.delete.setOnClickListener(this);
        holder.cancel.setOnClickListener(this);
        //给整个item设置滑动监听
        ((SlidingPaneLayout) holder.itemView).setPanelSlideListener(this);
        return holder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView=recyclerView;
    }

    /**
     * 当item滑出屏幕的时候也把抽屉关掉,这样就不会产生复用造成的下方item也有着按钮的情况了
     * @param holder
     */
    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        ((SlidingPaneLayout) holder.itemView).closePane();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        SlidingPaneLayout parent = (SlidingPaneLayout) view.getParent().getParent();
        switch (view.getId()) {
            case R.id.item_delete:
                /**
                 * 点击了删除按钮,关闭这个滑出框(删除按钮)的是父控件,父控件是SlidingPaneLayout
                 * ,再获得点击的这个view 的位置,将这个位置的item删除
                 */

                parent.closePane();
                int position = recyclerView.getChildAdapterPosition(parent);
                list.remove(position);
                notifyItemRemoved(position);
                break;
            case R.id.item_cancel:
                parent.closePane();
                break;
        }

    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {
        //当这个item被滑动的时候,将删除按钮显示出来,
        //先找到这个删除按钮控件
        View delete = ((View) panel.getParent()).findViewById(R.id.item_delete);
        /**
         * 原本,一个CardView和一个Button都在SlidingPaneLayout里面,SlidingPaneLayout又是一个直接
         * 继承于ViewGroup的,是基础的帧布局,所以CardView讲Button覆盖了,要理解的话要采用极限的思想
         * 在点击的瞬间,要开始移动的瞬间,这个button是在屏幕外的,在cardView左侧,这时候偏移量sildeOffset为0
         * 这时候这个button就跑到cardview左边去了,之后慢慢向右移动
         */
        ViewCompat.setTranslationX(delete,delete.getWidth()*(slideOffset-1));
    }

    @Override
    public void onPanelOpened(View panel) {

    }

    @Override
    public void onPanelClosed(View panel) {

    }

//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
////        CardView cardView = (CardView) view;
//
//        return false;
//    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView text;
        private final View delete;
        private final View cancel;

        public ViewHolder(View itemView) {
            super(itemView);
            text = ((TextView) itemView.findViewById(R.id.item_text));
            delete = itemView.findViewById(R.id.item_delete);
            cancel = itemView.findViewById(R.id.item_cancel);
        }
    }

}

