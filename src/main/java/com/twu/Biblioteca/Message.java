package com.twu.Biblioteca;

public class Message {
    private final String messageWhenSelectInvalid;
    private final String messageWhenReturnSuccess;
    private final String messageWhenReturnFail;
    private final String messageWhenCheckOutSuccess;
    private final String messageWhenCheckOutFail;
    private final String messageWhenCheckOutMovieSuccess;
    private final String messageWhenCheckOutMovieFail;
    private final String welcomeMessage;

    public Message() {
        this.messageWhenSelectInvalid = "Please select a valid option!";
        this.messageWhenCheckOutSuccess = "Thank you! Enjoy the book";
        this.messageWhenCheckOutFail = "Sorry, that book is not available";
        this.messageWhenCheckOutMovieSuccess = "Thank you! Enjoy the movie";
        this.messageWhenCheckOutMovieFail = "Sorry, that movie is not available";
        this.messageWhenReturnSuccess = "Thank you for returning the book";
        this.messageWhenReturnFail = "That is not a valid book to return";
        this.welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }

    public String getMessageWhenSelectInvalid() {
        return messageWhenSelectInvalid;
    }

    public String getMessageWhenCheckOutSuccess() {
        return messageWhenCheckOutSuccess;
    }

    public String getMessageWhenCheckOutFail() {
        return messageWhenCheckOutFail;
    }

    public String getMessageWhenCheckOutMovieSuccess() {
        return messageWhenCheckOutMovieSuccess;
    }

    public String getMessageWhenCheckOutMovieFail() {
        return messageWhenCheckOutMovieFail;
    }

    public String getMessageWhenReturnSuccess() {
        return messageWhenReturnSuccess;
    }

    public String getMessageWhenReturnFail() {
        return messageWhenReturnFail;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }
}
