package com.bgirlogic.flare.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kimsuh on 5/6/16.
 */
public class Company {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (name != null ? !name.equals(company.name) : company.name != null) return false;
        if (shortName != null ? !shortName.equals(company.shortName) : company.shortName != null)
            return false;
        return id != null ? id.equals(company.id) : company.id == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @SerializedName("name")
    private String name;

    @SerializedName("short_name")
    private String shortName;

    @SerializedName("id")
    private String id;

}
