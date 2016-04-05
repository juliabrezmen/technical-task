package com.technicaltask.app.tasks;


import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.technicaltask.app.data.User;
import com.technicaltask.app.database.UserDAO;
import com.technicaltask.app.database.UserData;
import com.technicaltask.app.utils.UserConverter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadUserAsyncTask extends AsyncTask<Void, Void, Void> {
    private OkHttpClient client;
    private String url;

    public LoadUserAsyncTask(@NonNull String url) {
        client = new OkHttpClient();
        this.url = url;
    }

    @Override
    protected Void doInBackground(Void... params) {
        List<UserData> userDataList = UserConverter.toUserDataList(loadUserList());
        UserDAO.newInstance().saveUserList(userDataList);
        return null;
    }

    @NonNull
    private List<User> loadUserList() {
        List<User> userList = new ArrayList<User>();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String json = response.body().string();
                Gson gson = new Gson();
                userList = gson.fromJson(json, new TypeToken<List<User>>() {
                }.getType());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

}
