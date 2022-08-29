package com.experis.mytunesassignment.data_access.services;

import com.experis.mytunesassignment.data_access.ConnectionManager;
import com.experis.mytunesassignment.data_access.interfaces.IMusicService;
import com.experis.mytunesassignment.models.MusicInfo;
import com.experis.mytunesassignment.models.MusicSearchInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MusicService implements IMusicService {

    Connection conn;

    @Override
    public ArrayList<MusicInfo> getRandomMusicInfo() {
        try {
            conn = ConnectionManager.getInstance().getConnection();
            ArrayList<MusicInfo> randomMusicInfo = new ArrayList<>();

            String sql = """
                    SELECT Track.Name, Artist.Name, Genre.Name FROM Track
                    INNER JOIN Album ON Album.AlbumId = Track.AlbumId
                    INNER JOIN Artist ON Album.ArtistId = Artist.ArtistId
                    INNER JOIN Genre ON Track.GenreId = Genre.GenreId
                    ORDER BY random()
                    LIMIT 5
                    """;

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MusicInfo nextMusicInfo = new MusicInfo(
                        resultSet.getString(2),
                        resultSet.getString(1),
                        resultSet.getString(3)
                );
                randomMusicInfo.add(nextMusicInfo);
            }

            return randomMusicInfo;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //Close connection
                conn.close();
            } catch (SQLException sqe) {
                System.out.println("Something went wrong while closing the connection.");
                System.out.println(sqe.getMessage());
            }
        }

        return null;
    }

    @Override
    public ArrayList<MusicSearchInfo> getMusicSearchInfo(String searchQuery) {
        try {
            conn = ConnectionManager.getInstance().getConnection();
            ArrayList<MusicSearchInfo> randomMusicInfo = new ArrayList<>();

            String sql = """
                    SELECT Artist.Name,Track.Name,Genre.Name, Album.Title FROM Track
                    INNER JOIN Album ON Album.AlbumId = Track.AlbumId
                    INNER JOIN Artist ON Album.ArtistId = Artist.ArtistId
                    INNER JOIN Genre ON Track.GenreId = Genre.GenreId
                    WHERE Track.Name LIKE ?
                    """;

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,"%" + searchQuery + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MusicSearchInfo nextMusicInfo = new MusicSearchInfo(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
                randomMusicInfo.add(nextMusicInfo);
            }

            return randomMusicInfo;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //Close connection
                conn.close();
            } catch (SQLException sqe) {
                System.out.println("Something went wrong while closing the connection.");
                System.out.println(sqe.getMessage());
            }
        }

        return null;
    }
}
