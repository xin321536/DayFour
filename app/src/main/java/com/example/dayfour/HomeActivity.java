package com.example.dayfour;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dayfour.dao.GreenDaoDao;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView re;
    private ArrayList<Bean.DataBean.DatasBean> list;
    private RecyAdapter adapter;
    private GreenDaoDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dao = BaseApp.getInstance().getDaoSession().getGreenDaoDao();

        initView();
        initURL();
    }

    private void initURL() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APi.ap)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APi aPi = retrofit.create(APi.class);
        Observable<Bean> bean = aPi.getBean();
        bean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        List<Bean.DataBean.DatasBean> datas = bean.getData().getDatas();
                        list.addAll(datas);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("tag","失败"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        re = (RecyclerView) findViewById(R.id.re);
        list = new ArrayList<>();
        adapter = new RecyAdapter(list, this);
        re.setAdapter(adapter);
        re.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemLongClickLis(new RecyAdapter.OnItemLongClickLis() {
            @Override
            public void onItemLongClick(int position) {
                Bean.DataBean.DatasBean bean = list.get(position);
                String envelopePic = bean.getEnvelopePic();
                String title = bean.getTitle();
                //将获得的数据添加到集合中
                GreenDao greenDao = new GreenDao(null, title, envelopePic);
                //调用方法添加到数据库中
                dao.insertOrReplace(greenDao);
                //吐司提示
                Toast.makeText(HomeActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
