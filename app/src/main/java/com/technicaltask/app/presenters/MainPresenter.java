package com.technicaltask.app.presenters;

import com.technicaltask.app.R;
import com.technicaltask.app.database.UserDAO;
import com.technicaltask.app.database.UserData;
import com.technicaltask.app.tasks.LoadUserAsyncTask;
import com.technicaltask.app.ui.MainActivity;
import com.technicaltask.app.utils.Internet;
import com.technicaltask.app.utils.UrlCreator;
import io.realm.Realm;

import java.util.List;

public class MainPresenter {
    private Realm realm;
    private MainActivity activity;

    public void onAttachActivity(MainActivity activity) {
        this.activity = activity;
        realm = Realm.getDefaultInstance();
        loadData();
    }

    public void onToolbarSearchClicked() {
        // handle search click
    }

    public void onToolbarCheckClicked() {
        // handle check click
    }

    public void onAddUserClicked() {
        // handle add user click
    }

    public void onDetachActivity() {
        realm.close();
        activity.finish();
    }

    private void loadData() {
        loadDataFromDatabase();
        if (Internet.isOn(activity.getApplicationContext())) {
            loadDataFromInternet();
        } else {
            activity.showMessage(activity.getString(R.string.no_internet));
        }
    }

    private void loadDataFromInternet() {
        LoadUserAsyncTask loadUserAsyncTask = new LoadUserAsyncTask(UrlCreator.getUserListUrl()) {
            @Override
            protected void onPostExecute(Void aVoid) {
                loadDataFromDatabase();
            }
        };
        loadUserAsyncTask.execute();
    }

    private void loadDataFromDatabase() {
        List<UserData> userDataList = UserDAO.newInstance().getUserList(realm);
        activity.setUserList(userDataList);
    }

}
