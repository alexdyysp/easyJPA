package com.JPA;

public class JPAClient {

    public static void main(String[] args){
        UserDAO userDao = new UserDAO();
        User user = new User("dyy", 20);

        userDao.add(user);
    }
}
