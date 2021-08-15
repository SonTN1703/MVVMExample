package com.example.projecttraining.homescreen;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projecttraining.HomeActivity;
import com.example.projecttraining.R;
import com.example.projecttraining.common.FragmentNavigate;
import com.example.projecttraining.common.GetDataManager;
import com.example.projecttraining.common.database.DatabaseTableSwitchManager;
import com.example.projecttraining.homescreen.webdetail.FragmentDetail;
import com.example.projecttraining.models.SwitchModel;

import java.util.ArrayList;
import java.util.List;


public class SwitchAdapter extends RecyclerView.Adapter<SwitchAdapter.SwitchViewHolder> {
    private List<SwitchModel> switchModelList;
    private Context context;
    HomeActivity homeActivity;
    private SharedViewModel sharedViewModel;
    private DatabaseTableSwitchManager dbManager;

    public SwitchAdapter(Activity activity, SharedViewModel sharedViewModel) {
        switchModelList = new ArrayList<>();
        this.homeActivity = (HomeActivity) activity;
        this.sharedViewModel = sharedViewModel;
        dbManager = new DatabaseTableSwitchManager(homeActivity.getApplicationContext());
        dbManager.open();
    }

    public void setData(List<SwitchModel> switchModelList) {
        this.switchModelList = switchModelList;
    }

    @NonNull
    @Override
    public SwitchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_webview, parent, false);
        context = parent.getContext();
        return new SwitchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SwitchViewHolder holder, int position) {
        SwitchModel switchModel = switchModelList.get(position);
        if (switchModel == null) {
            return;
        }
        holder.tvName.setText(switchModel.getName());
        holder.tvLink.setText(switchModel.getLink());
        holder.tvCategory.setText(switchModel.getCategory());
        Glide.with(context).load(GetDataManager.URI + switchModel.getIcon() + ".png").into(holder.imgIcon);
        holder.cbFavourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchModel.setChecked(true);
                    dbManager.updateSwitch(switchModel);
                    switchModelList.get(position).setChecked(true);
                    sharedViewModel.getListFavour(switchModelList);
                } else {
                    switchModel.setChecked(false);
                    dbManager.updateSwitch(switchModel);
                    switchModelList.get(position).setChecked(false);
                    sharedViewModel.getListFavour(switchModelList);
                }
            }
        });
        holder.cbFavourite.setChecked(switchModel.isChecked());
    }

    @Override
    public int getItemCount() {
        if (switchModelList != null) {
            return switchModelList.size();
        }
        return 0;
    }

    public class SwitchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvName;
        TextView tvLink;
        TextView tvCategory;
        ImageView imgIcon;
        CheckBox cbFavourite;


        public SwitchViewHolder(@NonNull View itemView) {
            super(itemView);
            initUI();
            itemView.setOnClickListener(this);
        }

        private void initUI() {
            tvName = itemView.findViewById(R.id.tvName);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvLink = itemView.findViewById(R.id.tvLink);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            cbFavourite = itemView.findViewById(R.id.cb_favourite);
        }

        @Override
        public void onClick(View v) {
            SwitchModel switchModel = switchModelList.get(getAdapterPosition());
            sharedViewModel.setUrl(switchModel.getLink());
            FragmentDetail fragmentDetail = FragmentDetail.newInstance();
            FragmentNavigate.addNewFragment(homeActivity, fragmentDetail);
        }
    }
}
