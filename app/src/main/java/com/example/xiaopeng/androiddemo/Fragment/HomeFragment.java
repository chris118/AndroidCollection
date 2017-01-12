package com.example.xiaopeng.androiddemo.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.xiaopeng.androiddemo.Activity.AnimationActivity;
import com.example.xiaopeng.androiddemo.Activity.AnimationActivity2;
import com.example.xiaopeng.androiddemo.Activity.BroadcastActivity;
import com.example.xiaopeng.androiddemo.Activity.DaggerActivity;
import com.example.xiaopeng.androiddemo.Activity.EventBusActivity;
import com.example.xiaopeng.androiddemo.Activity.GreenDaoActivity;
import com.example.xiaopeng.androiddemo.Activity.JNIActivity;
import com.example.xiaopeng.androiddemo.Activity.MainActivity;
import com.example.xiaopeng.androiddemo.Activity.PTContentProviderActivity;
import com.example.xiaopeng.androiddemo.Activity.RecycleViewActivity;
import com.example.xiaopeng.androiddemo.Activity.RxJavaActivity;
import com.example.xiaopeng.androiddemo.Activity.ServiceActivity;
import com.example.xiaopeng.androiddemo.Adapter.MainAdapter;
import com.example.xiaopeng.androiddemo.Bean.RepoEntity;
import com.example.xiaopeng.androiddemo.HttpService.RetrofitManager;
import com.example.xiaopeng.androiddemo.R;
import com.example.xiaopeng.androiddemo.Views.DividerItemDecoration;
import com.putao.ptx.push.core.GPushClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private final String TAG = HomeFragment.class.getSimpleName();

    private ProgressDialog dialog;
    private MainAdapter adapter;
    private List<RepoEntity> repos;
    private GPushClient gPushClient;

    @BindView(R.id.id_recyclerview)
    RecyclerView recycleView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recycleView = (RecyclerView) getActivity().findViewById(R.id.id_recyclerview);
        initRecycleView();
    }

    private void initRecycleView(){
        ArrayList<String> functionList = new ArrayList<>();
        functionList.add("JNI");
        functionList.add("Service");
        functionList.add("RecycleView");
        functionList.add("httpService");
        functionList.add("ContentProvider");
        functionList.add("SharePreference");
        functionList.add("RxJava");
        functionList.add("Dagger");
        functionList.add("Animation");
        functionList.add("Animation2");
        functionList.add("EventBus");
        functionList.add("GreenDAO");
        functionList.add("Broadcast");

        adapter = new MainAdapter(getActivity().getApplicationContext(), functionList);
        adapter.setOnItemClickLitener(new MainAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(TAG, String.valueOf(position));
                excuteFunctionWithIndex(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Log.d(TAG, String.valueOf(position));
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        String.valueOf(position), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
        recycleView.setAdapter(adapter);

        // 设置item动画
        recycleView.setItemAnimator(new DefaultItemAnimator());

        //layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(layoutManager);
        recycleView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
    }

    private void excuteFunctionWithIndex(int index){

        switch (index){
            case 0:
                JNIClicked();
                break;
            case 1:
                serviceClicked();
                break;
            case 2:
                RecycleViewClicked();
                break;
            case 3:
                httpServiceClicked();
                break;
            case 4:
                ContentProviderClicked();
                break;
            case 5:
                SharePreferenceClicked();
                break;
            case 6:
                RxJavaClicked();
                break;
            case 7:
                DaggerClicked();
                break;
            case 8:
                AnimationClicked();
                break;
            case 9:
                AnimationClicked2();
            case 10:
                EventBusClicked();
                break;
            case 11:
                GreenDAOClicked();
                break;
            case 12:
                BroadcastClicked();
                break;
            default:
                break;
        }
    }

    private  void serviceClicked(){
        Intent intent = new Intent();
        intent.setClass(getActivity(), ServiceActivity.class);
        startActivity(intent);
    }

    private  void JNIClicked(){
        Intent intent = new Intent();
        intent.setClass(getActivity(), JNIActivity.class);
        startActivity(intent);
    }

    private  void httpServiceClicked(){
        Call<List<RepoEntity>> call = RetrofitManager.getInstance().getGitService().listRepos("square");
        call.enqueue(new Callback<List<RepoEntity>>() {
            @Override
            public void onResponse(Call<List<RepoEntity>> call, Response<List<RepoEntity>> response) {
                repos = response.body();
                for(RepoEntity repo : repos){
                }
            }

            @Override
            public void onFailure(Call<List<RepoEntity>> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });

    }

    private  void RecycleViewClicked(){
        dialog = ProgressDialog.show(getActivity(), null, "请稍候...", true, false);

        Call<List<RepoEntity>> call = RetrofitManager.getInstance().getGitService().listRepos("square");
        call.enqueue(new Callback<List<RepoEntity>>() {
            @Override
            public void onResponse(Call<List<RepoEntity>> call, Response<List<RepoEntity>> response) {
                repos = response.body();

                Intent intent = new Intent();
                intent.setClass(getActivity(), RecycleViewActivity.class);

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("repos", (ArrayList<? extends Parcelable>) repos);
                intent.putExtras(bundle);

                if (dialog.isShowing())
                    dialog.dismiss();
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<RepoEntity>> call, Throwable t) {
                Log.d(TAG, t.getMessage());
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });

    }

    private  void ContentProviderClicked(){
        Intent intent = new Intent();
        intent.setClass(getActivity(), PTContentProviderActivity.class);
        startActivity(intent);
    }

    private  void SharePreferenceClicked(){
        SharedPreferences sp = getActivity().getSharedPreferences("config", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("content", "测试SharePreference");
        editor.commit();

        String readString = sp.getString("content", "");
        Toast.makeText(getActivity(), readString, Toast.LENGTH_SHORT).show();
    }

    private void RxJavaClicked(){
        Intent intent = new Intent();
        intent.setClass(getActivity(), RxJavaActivity.class);
        startActivity(intent);
    }

    private void DaggerClicked(){
        startActivity(new Intent().setClass(getActivity(), DaggerActivity.class));
    }

    private void AnimationClicked(){
        startActivity(new Intent().setClass(getActivity(), AnimationActivity.class));
    }

    private void AnimationClicked2(){
        startActivity(new Intent().setClass(getActivity(), AnimationActivity2.class));
    }

    private void EventBusClicked(){
        startActivity(new Intent().setClass(getActivity(), EventBusActivity.class));
    }

    private void GreenDAOClicked(){
        startActivity(new Intent().setClass(getActivity(), GreenDaoActivity.class));
    }
    private void BroadcastClicked(){
        startActivity(new Intent().setClass(getActivity(), BroadcastActivity.class));
    }
}
