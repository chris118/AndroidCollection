package com.example.xiaopeng.androiddemo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.xiaopeng.androiddemo.Adapter.RepoAdapter;
import com.example.xiaopeng.androiddemo.Bean.RepoEntity;
import com.example.xiaopeng.androiddemo.R;
import com.example.xiaopeng.androiddemo.Views.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleViewActivity extends AppCompatActivity {

    @BindView(R.id.id_recyclerview)
    RecyclerView recycleView;

    private final String TAG = ServiceActivity.class.getSimpleName();
    private RepoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        List<RepoEntity> repos = bundle.getParcelableArrayList("repos");
        if(repos != null){
            Log.d("RecycleViewActivity", String.valueOf(repos.size()));
            adapter = new RepoAdapter(this.getApplicationContext(), repos);
            adapter.setOnItemClickLitener(new RepoAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    Log.d(TAG, String.valueOf(position));
                    Toast toast = Toast.makeText(getApplicationContext(),
                            String.valueOf(position), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }

                @Override
                public void onItemLongClick(View view, int position) {
                    Log.d(TAG, String.valueOf(position));
                    Toast toast = Toast.makeText(getApplicationContext(),
                            String.valueOf(position), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            });
            recycleView.setAdapter(adapter);

            // 设置item动画
            recycleView.setItemAnimator(new DefaultItemAnimator());

            //layout
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recycleView.setLayoutManager(layoutManager);
            recycleView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

//            recycleView.setLayoutManager(new GridLayoutManager(this,4));
//            recycleView.addItemDecoration(new DividerGridItemDecoration(this));

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(0, MENU_ITEM_COUNTER, 0, "添加");
//        menu.add(0, MENU_ITEM_COUNTER + 1, 0, "删除");
        getMenuInflater().inflate(R.menu.recycleview_menu, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                adapter.addData(1);
                break;
            case R.id.delete:
                adapter.removeData(1);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
