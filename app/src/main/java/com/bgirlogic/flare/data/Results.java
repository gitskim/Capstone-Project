package com.bgirlogic.flare.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kimsuh on 5/6/16.
 */
public class Results {

    @SerializedName("results")
    private List<Job> results;

    @SerializedName("page_count")
    private int page_count;

    @SerializedName("page")
    private int page;
    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Results results1 = (Results) o;

        if (page_count != results1.page_count) return false;
        if (page != results1.page) return false;
        return results != null ? results.equals(results1.results) : results1.results == null;

    }

    @Override
    public int hashCode() {
        int result = results != null ? results.hashCode() : 0;
        result = 31 * result + page_count;
        result = 31 * result + page;
        return result;
    }

    @Override
    public String toString() {
        return "Results{" +
                "results=" + results +
                ", page_count=" + page_count +
                ", page=" + page +
                '}';
    }
}
