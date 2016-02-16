package com.xinpinget.android_jenkins;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinpinget.android_jenkins.databinding.ItemProjectBinding;
import com.xinpinget.android_jenkins.domain.ApiJsonRoot.JobEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cc on 2/15/16.
 */
public class ProjectActivity extends AppCompatActivity {

    private RecyclerView mRecycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        ProjectAdapter adapter = new ProjectAdapter();
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycler.setAdapter(adapter);

        List<JobEntity> jobs = getJobs();
        adapter.loadData(jobs);
        adapter.notifyDataSetChanged();
    }

    private List<JobEntity> getJobs() {
        List<JobEntity> entities = new ArrayList<>();

        JobEntity job = new JobEntity();
        job.setName("job_name");
        job.setColor("red");
        entities.add(job);

        job = new JobEntity();
        job.setName("job_name2");
        job.setColor("blue");
        entities.add(job);
        ;

        return entities;
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
