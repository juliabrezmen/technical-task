package com.technicaltask.app.utils;

import android.support.annotation.NonNull;
import com.technicaltask.app.data.User;
import com.technicaltask.app.database.UserData;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {
    @NonNull
    public static List<UserData> toUserDataList(@NonNull List<User> userList) {
        List<UserData> userDataList = new ArrayList<UserData>();
        for (User user : userList) {
            UserData userData = new UserData();
            userData.setId(user.getId());
            userData.setName(user.getName());
            userData.setTitle(user.getTitle());
            userData.setText(user.getText());
            userData.setPicture(user.getPicture());

            userDataList.add(userData);
        }
        return userDataList;
    }
}
