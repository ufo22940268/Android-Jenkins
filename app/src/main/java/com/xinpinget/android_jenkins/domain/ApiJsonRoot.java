package com.xinpinget.android_jenkins.domain;

import java.util.List;

/**
 * Created by cc on 2/15/16.
 */
public class ApiJsonRoot {
    /**
     * assignedLabels : [{}]
     * mode : NORMAL
     * nodeDescription : the master Jenkins node
     * nodeName :
     * numExecutors : 2
     * description : null
     * jobs : [{"name":"GameCub","url":"http://jenkins.xinpinget.com/job/GameCub/","color":"blue"},{"name":"Saturn","url":"http://jenkins.xinpinget.com/job/Saturn/","color":"blue"},{"name":"Saturn backup","url":"http://jenkins.xinpinget.com/job/Saturn%20backup/","color":"blue"},{"name":"Saturn crawlTest","url":"http://jenkins.xinpinget.com/job/Saturn%20crawlTest/","color":"blue"},{"name":"Saturn launchGoodTask","url":"http://jenkins.xinpinget.com/job/Saturn%20launchGoodTask/","color":"blue"},{"name":"Saturn updateAllReviews","url":"http://jenkins.xinpinget.com/job/Saturn%20updateAllReviews/","color":"red"},{"name":"Saturn updateXiaohongshuSegment","url":"http://jenkins.xinpinget.com/job/Saturn%20updateXiaohongshuSegment/","color":"blue"}]
     * overallLoad : {}
     * primaryView : {"name":"All","url":"http://jenkins.xinpinget.com/"}
     * quietingDown : false
     * slaveAgentPort : 0
     * unlabeledLoad : {}
     * useCrumbs : false
     * useSecurity : true
     * views : [{"name":"All","url":"http://jenkins.xinpinget.com/"},{"name":"eee","url":"http://jenkins.xinpinget.com/view/eee/"}]
     */

    private String mode;
    private String nodeDescription;
    private String nodeName;
    private int numExecutors;
    private Object description;
    /**
     * name : All
     * url : http://jenkins.xinpinget.com/
     */

    private PrimaryViewEntity primaryView;
    private boolean quietingDown;
    private int slaveAgentPort;
    private boolean useCrumbs;
    private boolean useSecurity;
    private List<AssignedLabelsEntity> assignedLabels;
    /**
     * name : GameCub
     * url : http://jenkins.xinpinget.com/job/GameCub/
     * color : blue
     */

    private List<JobsEntity> jobs;
    /**
     * name : All
     * url : http://jenkins.xinpinget.com/
     */

    private List<ViewsEntity> views;

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setNodeDescription(String nodeDescription) {
        this.nodeDescription = nodeDescription;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public void setNumExecutors(int numExecutors) {
        this.numExecutors = numExecutors;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public void setPrimaryView(PrimaryViewEntity primaryView) {
        this.primaryView = primaryView;
    }

    public void setQuietingDown(boolean quietingDown) {
        this.quietingDown = quietingDown;
    }

    public void setSlaveAgentPort(int slaveAgentPort) {
        this.slaveAgentPort = slaveAgentPort;
    }

    public void setUseCrumbs(boolean useCrumbs) {
        this.useCrumbs = useCrumbs;
    }

    public void setUseSecurity(boolean useSecurity) {
        this.useSecurity = useSecurity;
    }

    public void setAssignedLabels(List<AssignedLabelsEntity> assignedLabels) {
        this.assignedLabels = assignedLabels;
    }

    public void setJobs(List<JobsEntity> jobs) {
        this.jobs = jobs;
    }

    public void setViews(List<ViewsEntity> views) {
        this.views = views;
    }

    public String getMode() {
        return mode;
    }

    public String getNodeDescription() {
        return nodeDescription;
    }

    public String getNodeName() {
        return nodeName;
    }

    public int getNumExecutors() {
        return numExecutors;
    }

    public Object getDescription() {
        return description;
    }

    public PrimaryViewEntity getPrimaryView() {
        return primaryView;
    }

    public boolean isQuietingDown() {
        return quietingDown;
    }

    public int getSlaveAgentPort() {
        return slaveAgentPort;
    }

    public boolean isUseCrumbs() {
        return useCrumbs;
    }

    public boolean isUseSecurity() {
        return useSecurity;
    }

    public List<AssignedLabelsEntity> getAssignedLabels() {
        return assignedLabels;
    }

    public List<JobsEntity> getJobs() {
        return jobs;
    }

    public List<ViewsEntity> getViews() {
        return views;
    }

    public static class PrimaryViewEntity {
        private String name;
        private String url;

        public void setName(String name) {
            this.name = name;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class AssignedLabelsEntity {
    }

    public static class JobsEntity {
        private String name;
        private String url;
        private String color;

        public void setName(String name) {
            this.name = name;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }

        public String getColor() {
            return color;
        }
    }

    public static class ViewsEntity {
        private String name;
        private String url;

        public void setName(String name) {
            this.name = name;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }
}
