package com.xinpinget.android_jenkins;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinpinget.android_jenkins.api.ApiService;
import com.xinpinget.android_jenkins.databinding.ItemProjectBinding;
import com.xinpinget.android_jenkins.domain.ApiJsonRoot;
import com.xinpinget.android_jenkins.domain.ApiJsonRoot.JobEntity;
import com.xinpinget.android_jenkins.util.JenkinManager;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cc on 2/15/16.
 */
public class ProjectActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecycler;
    private SwipeRefreshLayout mSwipeView;
    private ProjectAdapter mAdapter;
    private JenkinManager mJenkinManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        mJenkinManager = new JenkinManager(this);

        mSwipeView = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mSwipeView.setOnRefreshListener(this);

        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mAdapter = new ProjectAdapter();
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycler.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        if (isLogin()) {
            loadData();
        } else {
            startActivity(new Intent(ProjectActivity.this, ConfigActivity.class));
        }
    }


    private boolean isLogin() {
        return mJenkinManager.getServerAddr() != null;
    }

    private void loadData() {
        String serverAddr = mJenkinManager.getServerAddr();
        showLoading(true);
        if (serverAddr != null) {
            ApiService.create(serverAddr).jenkins()
                    .list()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ApiJsonRoot>() {
                        @Override
                        public void onCompleted() {
                            showLoading(false);
                        }

                        @Override
                        public void onError(Throwable e) {
                            showLoginSnack(R.string.server_error);
                        }

                        @Override
                        public void onNext(ApiJsonRoot apiJsonRoot) {
                            mAdapter.loadData(apiJsonRoot.getJobs());
                        }
                    });
        } else {
            showLoginSnack(R.string.not_configured);
        }
    }

    private void showLoginSnack(int prompt) {
        //Show snake bar.
        Snackbar snackbar = Snackbar.make(findViewById(R.id.root), prompt, Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.login, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProjectActivity.this, ConfigActivity.class));
            }
        });
        snackbar.setActionTextColor(getResources().getColor(R.color.login));
        snackbar.show();
    }

    private void showLoading(boolean show) {
        mSwipeView.setRefreshing(show);
    }

    @Override
    public void onRefresh() {
        loadData();
    }

    private static class ProjectAdapter extends RecyclerView.Adapter {

        private List<JobEntity> mJobs;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ItemProjectBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_project, parent, false);
            return new ProjectHolder(binding.getRoot());
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            JobEntity jobEntity = mJobs.get(position);
            ((ItemProjectBinding) DataBindingUtil.bind(holder.itemView)).setJob(jobEntity);
        }

        @Override
        public int getItemCount() {
            return mJobs == null ? 0 : mJobs.size();
        }

        public void loadData(List<JobEntity> jobs) {
            mJobs = jobs;
        }

        public static class ProjectHolder extends RecyclerView.ViewHolder {
            public ProjectHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
