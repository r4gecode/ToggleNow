package com.school.fa.view.response;

import java.util.List;

public class DashboardResponseWrapper {

    private List<DashboardResultResponse> dashboardResults;
    private String status;
    private String message;

    public List<DashboardResultResponse> getDashboardResults() {
        return dashboardResults;
    }

    public void setDashboardResults(List<DashboardResultResponse> dashboardResults) {
        this.dashboardResults = dashboardResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DashboardResponse{" +
                "dashboardResultsList=" + dashboardResults +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
