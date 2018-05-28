package com.example.denis.a2night.Adapter;

import android.app.Activity;
import android.content.ClipData;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.denis.a2night.Inicio;
import com.example.denis.a2night.Interface.ILoadMore;
import com.example.denis.a2night.Model.Item;
import com.example.denis.a2night.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Minor on 15/04/2018.
 */

class LoadingViewHolder extends RecyclerView.ViewHolder {
    public ProgressBar progressBar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        progressBar = (ProgressBar)itemView.findViewById(R.id.progressBar);
    }
}

class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public CircleImageView circleView;
    public ImageView picture;
    public TextView horas;
    public TextView nombreD;
    public TextView descripcion;
    public ImageView MiImageView;


    public ItemViewHolder(View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.txtName);
        circleView = (CircleImageView)itemView.findViewById(R.id.circle_profile);
        picture = (ImageView)itemView.findViewById(R.id.picture);
        horas = (TextView)itemView.findViewById(R.id.tiempo);
        nombreD = (TextView)itemView.findViewById(R.id.txtName2);
        descripcion = (TextView)itemView.findViewById(R.id.descripcion);
        MiImageView = (ImageView)itemView.findViewById(R.id.imageView3);
    }
}

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final int VIEW_TYPE_ITEM=0, VIEW_TYPE_LOADING=1;
    ILoadMore loadMore;
    boolean isLoading;
    Activity activity;
    List<Item> items;
    int visibleThreshold=5;
    int lastVisibleItem, totalItemCount;
    Inicio inicio;

    public MyAdapter(RecyclerView recyclerView, Activity activity, List<Item> items) {
        this.activity = activity;
        this.items = items;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if(!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)){
                    if(loadMore != null)
                        loadMore.onLoadMore();
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(activity)
                    .inflate(R.layout.item_layout, parent, false);
            return new ItemViewHolder(view);
        }

        else if(viewType == VIEW_TYPE_LOADING){
            View view = LayoutInflater.from(activity)
                    .inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            Item item = items.get(position);
            final ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.name.setText(items.get(position).getName());
            viewHolder.horas.setText(items.get(position).getHoras());
            viewHolder.nombreD.setText(items.get(position).getNombreD());
            viewHolder.descripcion.setText(items.get(position).getDescripcion());
            viewHolder.MiImageView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    viewHolder.MiImageView.setImageResource(R.drawable.megusta);

                    // lo que desea que haga
                }
            });
            final String imgPerfil = "perfil/" + items.get(position).getCircleView() + ".png";
            FirebaseStorage.getInstance().getReference().child(imgPerfil).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(activity).load(uri).into(viewHolder.circleView);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {

                }
            });
            FirebaseStorage.getInstance().getReference().child(items.get(position).getLength()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(activity).load(uri).into(viewHolder.picture);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {

                }
            });
            //viewHolder.picture.setImageResource(R.drawable.lacali);
        }
        else if (holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setLoaded() {
        isLoading = false;
    }
}