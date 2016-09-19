package com.erincinci.models;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Base Model Abstract Class
 * Created by erincinci on 19.09.2016.
 */
public abstract class BaseModel implements Serializable {
    // Attributes
    private static final long serialVersionUID = 1L;
    private long id;

    /**
     * Constructors
     */
    protected BaseModel() {
    }

    protected BaseModel(long id) {
        this.id = id;
    }


    /**
     * Getters & Setters
     * @return
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Hash Code Overriden for set uniqueness
     * @return
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        if (getId() != 0)
            return new HashCodeBuilder((int) (getId()%2 == 0 ? getId()+1 : getId()), prime).toHashCode();
        else
            return super.hashCode();
    }

    /**
     * Equals Overriden for comparing two models based on solely IDs
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (o.getClass() != getClass()) return false;
        if (!BaseModel.class.isAssignableFrom(o.getClass())) return false;

        if (getId() != 0)
            return new EqualsBuilder().append(getId(), ((BaseModel)o).getId()).isEquals();
        else
            return super.equals(o);
    }

    /**
     * toString Overriden for formatted debug outputs for models
     * @return
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
