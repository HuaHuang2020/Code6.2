package com.example.code62;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//本项目代码为最新版新闻列表，包含项目2.4以及扩展实验8.6的改动
public class MainActivity extends AppCompatActivity {

    private List<News> newsList = new ArrayList<>();
    private NewsAdapter newsAdapter = null;
    private ListView lvNewsList;

    private String[] titles = null;
    private String[] authors = null;
    TypedArray images;

    private void initData(){
        int length;
        titles = getResources().getStringArray(R.array.titles);
        authors = getResources().getStringArray(R.array.authors);
        images = getResources().obtainTypedArray(R.array.images);

        if(titles.length> authors.length){
            length = authors.length;
        }else{
            length = titles.length;
        }

        for (int i = 0; i < length; i++) {
            News news = new News();
            news.setTitle(titles[i]);
            news.setAuthor(authors[i]);
            news.setImageId(images.getResourceId(i,0));

            newsList.add(news);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

//        titles = getResources().getStringArray(R.array.titles);
//        authors = getResources().getStringArray(R.array.authors);？

        lvNewsList = findViewById(R.id.lv_news_list);

        newsAdapter = new NewsAdapter(MainActivity.this,R.layout.list_item,newsList);
        newsAdapter.setOnItemDeleteListener(
                new NewsAdapter.OnItemDeleteListener(){
            public void onDelete(int id){
                removeData(id);
            }
        });


//        ListView lvNewsList = findViewById(R.id.lv_news_list);
        lvNewsList.setAdapter(newsAdapter);

    }

    private void removeData(int id){
        newsList.remove(id);
        newsAdapter.notifyDataSetChanged();
    }
}