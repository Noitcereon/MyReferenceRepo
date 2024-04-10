/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * https://github.com/javaee/firstcup-examples/LICENSE.txt
 */
package me.noitcereon.ejb;

import me.noitcereon.entity.FirstcupUser;

import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DukesBirthdayBean is a stateless session bean that calculates the age
 * difference between a user and Duke, who was born on May 23, 1995.
 */
@Stateless
public class DukesBirthdayBean {

    private static final Logger logger =
            Logger.getLogger("me.noitcereon.ejb.DukesBirthdayBean");

    @PersistenceContext
    private EntityManager em;

    public Double getAverageAgeDifference() {
        Double avgAgeDiff = (Double)
                em.createNamedQuery("findAverageAgeDifferenceOfAllFirstcupUsers")
                        .getSingleResult();
        logger.log(Level.INFO, "Average age difference is: {0}", avgAgeDiff);
        return avgAgeDiff;
    }

    public int getAgeDifference(LocalDate theirBirthday) {
		int ageDifference;

        LocalDate dukesBirthday = LocalDate.of(1995, Month.MAY, 23);
        ageDifference = theirBirthday.getYear() - dukesBirthday.getYear();
        logger.log(Level.INFO, "Raw ageDifference is: {0}",  ageDifference);
        LocalDate theirBirthdayMonthAndDayOnly = theirBirthday.withYear(dukesBirthday.getYear());

        if(dukesBirthday.isBefore(theirBirthdayMonthAndDayOnly) && ageDifference > 0){
            ageDifference--;
        }
        // Create and store the user's birthday in the database
        FirstcupUser user = new FirstcupUser(theirBirthday, ageDifference);
        em.persist(user);

        logger.log(Level.INFO, "Final ageDifference is: {0}",  ageDifference);

        return ageDifference;
    }
}
