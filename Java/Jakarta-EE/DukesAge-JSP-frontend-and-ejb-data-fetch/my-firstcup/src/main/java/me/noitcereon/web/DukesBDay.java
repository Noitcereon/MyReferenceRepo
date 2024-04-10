/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * https://github.com/javaee/firstcup-examples/LICENSE.txt
 */
package me.noitcereon.web;

import me.noitcereon.ejb.DukesBirthdayBean;
import me.noitcereon.ConfigurationHelper;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Named
@SessionScoped
public class DukesBDay implements Serializable {

    @EJB
    private DukesBirthdayBean dukesBirthdayBean;
    protected int age;
    @NotNull
    protected LocalDate yourBirthday;
    protected int ageDiff;
    protected int absAgeDiff;
    protected Double averageAgeDifference;
    private static final Logger logger = Logger.getLogger("me.noitcereon.web.DukesBDay");


    /** Creates a new instance of DukesBDay */
    public DukesBDay() {
        // explicit empty construtor.
    }

    public String processBirthday() {
        System.out.println("Hit processBirthday. Bean state: " + this);
        this.setAgeDiff(dukesBirthdayBean.getAgeDifference(yourBirthday));
        logger.log(Level.INFO, "age diff from dukesbday {0}", ageDiff);
        this.setAbsAgeDiff(Math.abs(this.getAgeDiff()));
        logger.log(Level.INFO, "absAgeDiff {0}", absAgeDiff);
        this.setAverageAgeDifference(dukesBirthdayBean.getAverageAgeDifference());
        logger.log(Level.INFO, "averageAgeDifference {0}", averageAgeDifference);
        return JspPages.GREETING_PAGE;
    }
    
    /**
     * Get the value of age
     *
     * @return the value of age
     */
    public int getAge() {
        try {
            Client client = ClientBuilder.newClient();
            String url = ConfigurationHelper.getProperty(ConfigurationHelper.ConfigKeys.MY_DUKES_AGE_ENDPOINT);
            logger.info("About to make a request to " + url);
            WebTarget target = client.target(url);
            String response = target.request().get(String.class);
            age = Integer.parseInt(response);
        } catch (Exception e) {
            logger.severe("Processing of HTTP response failed due to: " + e.getClass() + ": " +  e.getMessage());
        }
        return age;
    }

    /**
     * Set the value of age
     *
     * @param age new value of age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Get the value of yourBD
     *
     * @return the value of yourBD
     */
    public LocalDate getYourBirthday() {
        return yourBirthday;
    }

    /**
     * Set the value of yourBD
     *
     * @param yourBirthday new value of yourBD
     */
    public void setYourBirthday(LocalDate yourBirthday) {
        this.yourBirthday = yourBirthday;
    }

    /**
     * Get the value of ageDiff
     *
     * @return the value of ageDiff
     */
    public int getAgeDiff() {
        return ageDiff;
    }

    /**
     * Set the value of ageDiff
     *
     * @param ageDiff new value of ageDiff
     */
    public void setAgeDiff(int ageDiff) {
        this.ageDiff = ageDiff;
    }

    /**
     * Get the value of absAgeDiff
     *
     * @return the value of absAgeDiff
     */
    public int getAbsAgeDiff() {
        return absAgeDiff;
    }

    /**
     * Set the value of absAgeDiff
     *
     * @param absAgeDiff new value of absAgeDiff
     */
    public void setAbsAgeDiff(int absAgeDiff) {
        this.absAgeDiff = absAgeDiff;
    }

    /**
     * Get the value of averageAgeDifference
     *
     * @return the value of averageAgeDifference
     */
    public Double getAverageAgeDifference() {
        return averageAgeDifference;
    }

    /**
     * Set the value of averageAgeDifference
     *
     * @param averageAgeDifference new value of averageAgeDifference
     */
    public void setAverageAgeDifference(Double averageAgeDifference) {
        this.averageAgeDifference = averageAgeDifference;
    }

    @Override
    public String toString() {
        return "DukesBDay{" +
                "dukesBirthdayBean=" + dukesBirthdayBean +
                ", age=" + age +
                ", yourBirthday=" + yourBirthday +
                ", ageDiff=" + ageDiff +
                ", absAgeDiff=" + absAgeDiff +
                ", averageAgeDifference=" + averageAgeDifference +
                '}';
    }
}
