package com.ameltaleb.users.domain.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long idUser){
        super("User not found with id : "+ idUser);
        System.out.println("**********Exception thrown*********");
    }

}
