package com.bgirlogic.flare.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kimsuh on 5/6/16.
 */
public class Results {

    public List<Job> getResults() {
        return results;
    }

    public void setResults(List<Job> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Results results1 = (Results) o;

        if (page != results1.page) return false;
        if (pageCount != results1.pageCount) return false;
        return results != null ? results.equals(results1.results) : results1.results == null;

    }

    @Override
    public int hashCode() {
        int result = results != null ? results.hashCode() : 0;
        result = 31 * result + page;
        result = 31 * result + pageCount;
        return result;
    }

    @SerializedName("Results")
    private List<Job> results;

    @SerializedName("page")
    private int page;

    @SerializedName("page_count")
    private int pageCount;
}
