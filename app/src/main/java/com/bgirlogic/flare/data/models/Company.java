package com.bgirlogic.flare.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kimsuh on 5/6/16.
 */
public class Company {
    @SerializedName("name")
    private String name;

    @SerializedName("short_name")
    private String short_name;

    @SerializedName("id")
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
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
        if (short_name != null ? !short_name.equals(company.short_name) : company.short_name != null)
            return false;
        return id != null ? id.equals(company.id) : company.id == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (short_name != null ? short_name.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", short_name='" + short_name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
