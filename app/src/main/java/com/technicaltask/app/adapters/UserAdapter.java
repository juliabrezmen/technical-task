package com.technicaltask.app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.technicaltask.app.R;
import com.technicaltask.app.database.UserData;
import com.technicaltask.app.utils.CircleTransform;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    private List<UserData> userDataList;

    public UserAdapter(Context context) {
        this.context = context;
        userDataList = new ArrayList<UserData>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list_item, viewGroup, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserData userData = userDataList.get(position);

        holder.txtName.setText(userData.getName());
        holder.txtTitle.setText(userData.getTitle());
        holder.txtText.setText(userData.getText());

        String picture = userData.getPicture();
        Picasso.with(context).load(picture).transform(new CircleTransform()).into(holder.imgPicture);
    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }

    public void updateData(@NonNull List<UserData> newUserDataList) {
        if (userDataList != null) {
            userDataList = newUserDataList;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtTitle;
        TextView txtText;
        ImageView imgPicture;

        ViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
            txtText = (TextView) itemView.findViewById(R.id.txt_text);
            imgPicture = (ImageView) itemView.findViewById(R.id.img_picture);
        }
    }
}
