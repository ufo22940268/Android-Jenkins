package com.xinpinget.android_jenkins;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.xinpinget.android_jenkins.api.ApiService;
import com.xinpinget.android_jenkins.databinding.ItemProjectBinding;
import com.xinpinget.android_jenkins.domain.ApiJsonRoot;
import com.xinpinget.android_jenkins.domain.ApiJsonRoot.JobEntity;
import com.xinpinget.android_jenkins.util.JenkinsManager;

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
    private JenkinsManager mJenkinsManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        mJenkinsManager = new JenkinsManager(this);

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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        loadData();
    }

    private boolean isLogin() {
        return mJenkinsManager.getServerAddr() != null;
    }

    private void loadData() {
        String serverAddr = mJenkinsManager.getServerAddr();
        if (serverAddr != null) {
            ApiService.create(serverAddr).jenkins()
                    .list()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ApiJsonRoot>() {
                        @Override
                        public void onCompleted() {
                            System.out.println("ProjectActivity.onCompleted");
                            mSwipeView.setRefreshing(false);
                        }

                        @Override
                        public void onError(Throwable e) {
                            mSwipeView.setRefreshing(false);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.project, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.config) {
            startActivity(new Intent(this, ConfigActivity.class));
            return true;
        }
        return false;
    }

    private void showLoginSnack(int prompt) {
        //Show snake bar.
        Snackbar snackbar = Snackbar.make(findViewById(R.id.root), prompt, Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.config, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProjectActivity.this, ConfigActivity.class));
            }
        });
        snackbar.setActionTextColor(getResources().getColor(R.color.login));
        snackbar.show();
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
            final JobEntity jobEntity = mJobs.get(position);
            ((ItemProjectBinding) DataBindingUtil.bind(holder.itemView)).setJob(jobEntity);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(jobEntity.getUrl())));
                }
            });
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
