package com.bgirlogic.flare.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kimsuh on 5/6/16.
 */
public class Response1 {

    @SerializedName("results")
    private List<Job> results;

    @SerializedName("page_count")
    private int page_count;

    @SerializedName("page")
    private int page;

    @Override
    public String toString() {
        return "Response1{" +
                "results=" + results +
                ", page_count=" + page_count +
                ", page=" + page +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response1 response1 = (Response1) o;

        if (page_count != response1.page_count) return false;
        if (page != response1.page) return false;
        return results != null ? results.equals(response1.results) : response1.results == null;

    }

    @Override
    public int hashCode() {
        int result = results != null ? results.hashCode() : 0;
        result = 31 * result + page_count;
        result = 31 * result + page;
        return result;
    }

    public List<Job> getResults() {
        return results;
    }

    public void setResults(List<Job> results) {
        this.results = results;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
