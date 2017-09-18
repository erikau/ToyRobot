package com.toyrobot.Exception;

public class UnableToParseCommandException extends Exception {


    public UnableToParseCommandException(String message){
        super(message);
    }

    public UnableToParseCommandException(String message, Exception e){
        super(message, e);
    }
}
