package id.ac.ui.cs.ristek.testgit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.ac.ui.cs.ristek.testgit.R;
import id.ac.ui.cs.ristek.testgit.model.Pixabay;

/**
 * Created by Ryorda on 23/03/2017.
 */
public class PixabayAdapter extends RecyclerView.Adapter<PixabayAdapter.PixabayViewHolder> {
    ArrayList<Pixabay> pixabays;
    Context context;

    public PixabayAdapter(Context context, ArrayList<Pixabay> pixabays) {
        this.context = context;
        this.pixabays = pixabays;
    }

    @Override
    public PixabayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        return new PixabayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PixabayViewHolder holder, int position) {
        Pixabay pix = pixabays.get(position);
        holder.textView.setText(pix.getName());
        Glide.with(context).load(pix.getUrl()).into(holder.getImgView());
    }

    @Override
    public int getItemCount() {
        return pixabays.size();
    }

    class PixabayViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgView;
        private TextView textView;

        public PixabayViewHolder(View itemView) {
            super(itemView);

            imgView = (ImageView) itemView.findViewById(R.id.pixabay_image);
            textView = (TextView) itemView.findViewById(R.id.pixabay_title);
        }

        public ImageView getImgView() {
            return imgView;
        }

        public void setImgView(ImageView imgView) {
            this.imgView = imgView;
        }

        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }
    }
}
