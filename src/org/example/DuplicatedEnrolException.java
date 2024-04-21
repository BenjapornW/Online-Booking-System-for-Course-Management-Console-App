package org.example;

public class DuplicatedEnrolException extends Exception {
    public DuplicatedEnrolException(String s)
    {
        // Call constructor of parent Exception
        super(s);
    }
}