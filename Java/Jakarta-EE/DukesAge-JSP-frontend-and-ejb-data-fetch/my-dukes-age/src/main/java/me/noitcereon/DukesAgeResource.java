/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 * <p>
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * https://github.com/javaee/firstcup-examples/LICENSE.txt
 */
package me.noitcereon;

import java.time.LocalDate;
import java.time.Month;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
// NOSONAR Original import from archetype: import javax.em.rs.Produces;

/**
 * REST Web Service
 * Accessible at, e.g. http://localhost:4444/my-dukes-age-1.0-SNAPSHOT/webapi/dukesAge
 * Broken down this would be: <br/>
 * 1. host:port <br/>
 * 2. "my-dukes-age" is from artifact name (or context path, if specified elsewhere, e.g. in application.xml) <br/>
 * 3. "webapi" comes from servlet in web.xml <br/>
 * 4. "dukesAge" comes from the @Path annotation.
 */
@Path("dukesAge")
public class DukesAgeResource {

    /**
     * Creates a new instance of DukesAgeResource
     */
    public DukesAgeResource() {
        // To have no-arg constructor explicitly declared.
    }

    /**
     * Retrieves representation of an instance of DukesAgeResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getText() {
        LocalDate dukeBirthday = LocalDate.of(1995, Month.MAY, 23);
        LocalDate today = LocalDate.now();
        LocalDate currentMonthAndDay = today.minusYears(today.getYear() - (long) dukeBirthday.getYear());
        int dukeAge = LocalDate.now().getYear() - dukeBirthday.getYear();
        if (dukeBirthday.isBefore(currentMonthAndDay)) {
            dukeAge--;
        }
        return String.valueOf(dukeAge);
    }
}
