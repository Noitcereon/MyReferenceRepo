/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 * <br/>
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * https://github.com/javaee/firstcup-examples/LICENSE.txt
 */
package me.noitcereon.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findAverageAgeDifferenceOfAllFirstcupUsers",
        query = "SELECT AVG(user.ageDifference) FROM FirstcupUser user")
public class FirstcupUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    protected LocalDate birthday;
    protected int ageDifference;

    public FirstcupUser() {
    }

    public FirstcupUser(LocalDate date, int difference) {
        this.birthday = date;
        this.ageDifference = difference;
    }

    /**
     * Get the value of ageDifference
     *
     * @return the value of ageDifference
     */
    public int getAgeDifference() {
        return ageDifference;
    }

    /**
     * Set the value of ageDifference
     *
     * @param ageDifference new value of ageDifference
     */
    public void setAgeDifference(int ageDifference) {
        this.ageDifference = ageDifference;
    }

    /**
     * Get the value of birthday
     *
     * @return the value of birthday
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Set the value of birthday
     *
     * @param birthday new value of birthday
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FirstcupUser)) {
            return false;
        }
        FirstcupUser other = (FirstcupUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "me.noitcereon.entity.FirstcupUser[id=" + id + "]";
    }
}
