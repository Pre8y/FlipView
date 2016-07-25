package com.learncode.flipview.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.learncode.flipview.R;
import com.learncode.flipview.model.FlickrPhoto;
import com.learncode.flipview.util.FlipAnimation;

/**
 * Created by Preeti on 21/07/16.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    String imageUrl = "https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_q.jpg";

    ArrayList<FlickrPhoto> mDataList;
    LayoutInflater mInflater;
    Context mContext;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    OnItemClickListener mOnItemClickListener;

    public DataAdapter(Context context, ArrayList<FlickrPhoto> dataList) {
        this.mContext = context;
        this.mDataList = dataList;
        this.mInflater = LayoutInflater.from(context);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.list_item, parent, false));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.main_activity_root)
        View rootLayout;
        @BindView(R.id.main_activity_card_face)
        View cardFace;
        @BindView(R.id.main_activity_card_back)
        View cardBack;

        View view ;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }

        public void bind(final int position) {
            FlickrPhoto data = mDataList.get(position);
            //https://farm9.staticflickr.com/8809/27899942484_99b62448ae_q.jpg
            //https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
            String internetUrl = imageUrl.replace("{farm-id}", data.getFarm()).replace("{server-id}", data.getServer())
                    .replace("{id}", data.getId()).replace("{secret}", data.getSecret());
//
            Glide
                    .with(mContext)
                    .load(internetUrl)
                    .placeholder(R.drawable.img)
                    .into(image);
            name.setText(data.getTitle());
            image.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(position);
                    }
                    flipCard(cardFace, cardBack);
                }
            });
            name.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(position);
                    }
                    flipCard(cardFace, cardBack);
                }
            });

        }



        private void flipCard(View cardFace, View cardBack) {

            FlipAnimation flipAnimation = new FlipAnimation(cardFace, cardBack);

            if (cardFace.getVisibility() == View.GONE) {
                flipAnimation.reverse();
            }
            rootLayout.startAnimation(flipAnimation);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
