package com.bgirlogic.flare.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kimsuh on 5/6/16.
 */
public class Job {

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (companies != null ? !companies.equals(job.companies) : job.companies != null)
            return false;
        if (publicationDate != null ? !publicationDate.equals(job.publicationDate) : job.publicationDate != null)
            return false;
        if (id != null ? !id.equals(job.id) : job.id != null) return false;
        if (contents != null ? !contents.equals(job.contents) : job.contents != null) return false;
        if (name != null ? !name.equals(job.name) : job.name != null) return false;
        return type != null ? type.equals(job.type) : job.type == null;

    }

    @Override
    public int hashCode() {
        int result = companies != null ? companies.hashCode() : 0;
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (contents != null ? contents.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Job{" +
                "companies=" + companies +
                ", publicationDate='" + publicationDate + '\'' +
                ", id='" + id + '\'' +
                ", contents='" + contents + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @SerializedName("company")
    private List<Company> companies;

    @SerializedName("publication_date")
    private String publicationDate;

    @SerializedName("id")
    private String id;

    @SerializedName("contents")
    private String contents;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

}
