/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class AnimatedMovie extends Movie {
    private int animatedMovieId;  
    private String animationStudio;
    private String ageRecommendation;

    public AnimatedMovie(int movieId, String title, int releaseYear, String genre, String director, int duration, double rating,int animatedMovieId, String animationStudio, String ageRecommendation) {
        super(movieId, title, releaseYear, genre, director, duration, rating);
        this.animatedMovieId = animatedMovieId;
        this.animationStudio = animationStudio;
        this.ageRecommendation = ageRecommendation;
    }

    public int getAnimatedMovieId() {
        return animatedMovieId;
    }

    public void setAnimatedMovieId(int animatedMovieId) {
        this.animatedMovieId = animatedMovieId;
    }

    public String getAnimationStudio() {
        return animationStudio;
    }

    public void setAnimationStudio(String animationStudio) {
        this.animationStudio = animationStudio;
    }

    public String getAgeRecommendation() {
        return ageRecommendation;
    }

    public void setAgeRecommendation(String ageRecommendation) {
        this.ageRecommendation = ageRecommendation;
    }
}
