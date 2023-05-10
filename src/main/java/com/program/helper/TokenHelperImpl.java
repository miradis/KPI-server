package com.program.helper;

import com.program.model.Teacher;
import com.program.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TokenHelperImpl implements TokenHelper{

    HashMap<Integer, User> tokensUserMap = new HashMap<>();
    HashMap<Integer, Teacher> tokensTeacherMap = new HashMap<>();

    @Override
    public int generateTokenForUser(User user) {
        tokensUserMap.put(user.hashCode(), user);
        return user.hashCode();
    }

    @Override
    public void deleteTokenForUser(int token) {
        tokensUserMap.remove(token);
    }

    @Override
    public User getUserByToken(int token) {
        return tokensUserMap.get(token);
    }

    @Override
    public int generateTokenForTeacher(Teacher teacher) {
        tokensTeacherMap.put(teacher.hashCode(), teacher);
        return teacher.hashCode();
    }

    @Override
    public void deleteTokenForTeacher(int token) {
        tokensTeacherMap.remove(token);
    }

    @Override
    public Teacher getTeacherByToken(int token) {
        return tokensTeacherMap.get(token);
    }
}
