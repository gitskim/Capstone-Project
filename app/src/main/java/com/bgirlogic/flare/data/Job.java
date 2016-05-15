package com.bgirlogic.flare.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kimsuh on 5/6/16.
 */
public class Job {

    @SerializedName("publication_date")
    private String publication_date;

    @SerializedName("contents")
    private String contents;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private String id;

    @SerializedName("company")
    private Company company;

    @Override
    public String toString() {
        return "Job{" +
                "publication_date='" + publication_date + '\'' +
                ", contents='" + contents + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", company=" + company +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (publication_date != null ? !publication_date.equals(job.publication_date) : job.publication_date != null)
            return false;
        if (contents != null ? !contents.equals(job.contents) : job.contents != null) return false;
        if (name != null ? !name.equals(job.name) : job.name != null) return false;
        if (id != null ? !id.equals(job.id) : job.id != null) return false;
        return company != null ? company.equals(job.company) : job.company == null;

    }

    @Override
    public int hashCode() {
        int result = publication_date != null ? publication_date.hashCode() : 0;
        result = 31 * result + (contents != null ? contents.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }

    public String getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(String publication_date) {
        this.publication_date = publication_date;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
