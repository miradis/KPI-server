package com.program.service;

import com.program.exception.UserException;
import com.program.model.User;
import java.util.List;

public interface UserService {

    User createUser(User user) throws UserException;

    User changeRoleToAdmin(User user) throws UserException;

    List<User> findAll() throws UserException;

    List<User> getUsersByRoleTeacher() throws UserException;

    User getUserByEmail(String email) throws UserException;

    boolean isUserEmailPresent(String email) throws UserException;

    User getUserById(Long userId) throws UserException;

    User updateUser(Long id, User user) throws UserException;

    void deleteUser(Long id) throws UserException;

}
