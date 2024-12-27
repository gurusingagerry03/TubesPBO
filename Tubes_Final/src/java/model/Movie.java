/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Movie implements Entity {
    int movieId;
    private String title;
    private int releaseYear;
    private String genre;
    private String director;
    private int duration;
    private double rating;

    public Movie(int movieId, String title, int releaseYear, String genre, String director, int duration, double rating) {
        this.movieId = movieId;
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.director = director;
        this.duration = duration;
        this.rating = rating;
    }
    
    @Override
    public int getId() {
        return movieId;
    }

    @Override
    public void setId(int id) {
        this.movieId = id;
    }
    
    public String getTitle() { 
        return title; 
    }
    public void setTitle(String title) { 
        this.title = title; 
    }

    public int getReleaseYear() { 
        return releaseYear; 
    }
    
    public void setReleaseYear(int releaseYear) { 
        this.releaseYear = releaseYear; 
    }

    public String getGenre() { 
        return genre; 
    }
    
    public void setGenre(String genre) { 
        this.genre = genre; 
    }

    public String getDirector() { 
        return director; 
    }
    
    public void setDirector(String director) { 
        this.director = director; 
    }

    public int getDuration() { 
        return duration; 
    }
    
    public void setDuration(int duration) { 
        this.duration = duration; 
    }

    public double getRating() { 
        return rating; 
    }
    
    public void setRating(double rating) { 
        this.rating = rating; 
    }
}
