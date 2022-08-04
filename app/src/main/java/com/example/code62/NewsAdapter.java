package com.example.code62;

    import android.annotation.SuppressLint;
    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.ImageView;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;

    import  java.util.List;

    public class NewsAdapter extends ArrayAdapter<News> {
            private List<News> mNewsData;
            private Context mContext;
            private int resourceId;

            private OnItemDeleteListener listener =null;

        public void setOnItemDeleteListener(OnItemDeleteListener listener){
              this.listener = listener;
        }

            public interface OnItemDeleteListener {
                 void onDelete(int id);
            }

        public NewsAdapter(Context context , int resourceId , List<News> data) {
                    super(context,resourceId,data);
                    this.mContext = context;
                    this.mNewsData = data;
                    this.resourceId= resourceId;
            }

            @SuppressLint("CutPasteId")
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    News news = getItem(position);
                    View view;

                    ViewHolder viewHolder;

                    if(convertView == null){
                        view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
                        viewHolder = new ViewHolder();
                        viewHolder.tvTitle = view.findViewById(R.id.tvTitle);
                        viewHolder.tvAuthor = view.findViewById(R.id.tvAuthor);
                        viewHolder.ivImage = view.findViewById(R.id.ivImage);
                        viewHolder.ivDelete = view.findViewById(R.id.iv_delete);
                        view.setTag(viewHolder);
                    }else {
                        view = convertView;
                        viewHolder = (ViewHolder) view.getTag();
                    }


                     viewHolder.ivDelete.setTag(position);

                     viewHolder.ivDelete.setOnClickListener(
                             new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {
                             if(listener != null){
                                 int id = (int) view.getTag();
                                 listener.onDelete(id);
                             }
                         }
                     });
                viewHolder.tvTitle.setText(news.getTitle());
                viewHolder.tvAuthor.setText(news.getAuthor());
                viewHolder.ivImage.setImageResource(news.getImageId());
                     return view;
            }

            class ViewHolder{
                //用于保存每一个Item布局中控件的实例
                TextView tvTitle;
                TextView tvAuthor;
                ImageView ivImage;
                ImageView ivDelete;
            }

    }
