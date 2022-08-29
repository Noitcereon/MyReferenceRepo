package com.experis.mytunesassignment.data_access.interfaces;

import java.util.ArrayList;

public interface ICrudRepository<T> {

    /**
     *
     * @return returns all T objects or null
     */
    ArrayList<T> getAll();

    /**
     *
     * @return The object with that id or null
     */
    T getById(int id);
    /**
     *
     * @return The created object
     */
    T create(T object);

    /**
     * @param object object of type T, must have an id associated with it.
     * Updates the T object based on its id
     * @return Number of rows updated
     */
    int update(T object);

    /**
     * Deletes single object based on the id of the object.
     * @return Number of rows deleted
     */
    int delete(int id);
}
