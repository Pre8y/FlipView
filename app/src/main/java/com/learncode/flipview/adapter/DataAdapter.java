package com.learncode.flipview.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
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
import com.learncode.flipview.util.ImageLoader;
import com.learncode.flipview.util.PropertyUtil;

/**
 * Created by Preeti on 21/07/16.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    String imageUrl = "https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_q.jpg";

    ArrayList<FlickrPhoto> mDataList;
    LayoutInflater mInflater;
    Context mContext;
    ImageLoader imageLoader;
    int selectedPosition = -1;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    OnItemClickListener mOnItemClickListener;

    public DataAdapter(Context context, ArrayList<FlickrPhoto> dataList) {
        this.mContext = context;
        this.mDataList = dataList;
        this.mInflater = LayoutInflater.from(context);
        imageLoader=new ImageLoader(context);

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

        public void bind(int position) {
            FlickrPhoto data = mDataList.get(position);
            //https://farm9.staticflickr.com/8809/27899942484_99b62448ae_q.jpg
            //https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
            String internetUrl = imageUrl.replace("{farm-id}", data.getFarm()).replace("{server-id}", data.getServer())
                    .replace("{id}", data.getId()).replace("{secret}", data.getSecret());

            if(PropertyUtil.isGlideEnabled()) {
                Glide
                        .with(mContext)
                        .load(internetUrl)
                        .placeholder(R.drawable.img)
                        .into(image);
            }else{
                imageLoader.displayImage(internetUrl, image);
            }
            name.setText(data.getTitle());

            if (position != selectedPosition && cardFace.getVisibility()==View.GONE) {
//                flipCard(cardFace, cardBack);
                cardFace.setVisibility(View.VISIBLE);
                cardBack.setVisibility(View.GONE);
            }
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyItemChanged(selectedPosition);
                    selectedPosition = getLayoutPosition();
//                    notifyItemChanged(selectedPosition);
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(selectedPosition);
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
            ((View)cardFace.getParent()).startAnimation(flipAnimation);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        // Handle key up and key down and attempt to move selection
        recyclerView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();

                // Return false if scrolled to the bounds and allow focus to move off the list
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                        return tryMoveSelection(lm, 1);
                    } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                        return tryMoveSelection(lm, -1);
                    }
                }

                return false;
            }
        });
    }

    private boolean tryMoveSelection(RecyclerView.LayoutManager lm, int direction) {
        int nextSelectItem = selectedPosition + direction;

        // If still within valid bounds, move the selection, notify to redraw, and scroll
        if (nextSelectItem >= 0 && nextSelectItem < getItemCount()) {
            notifyItemChanged(selectedPosition);
            selectedPosition = nextSelectItem;
            notifyItemChanged(selectedPosition);
            lm.scrollToPosition(selectedPosition);
            return true;
        }

        return false;
    }
}
