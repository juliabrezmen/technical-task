package com.technicaltask.app.database;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import io.realm.Realm;

import java.util.List;

public class UserDAO {
    public static UserDAO newInstance() {
        return new UserDAO();
    }

    public void saveUserList(@NonNull final List<UserData> userDataList) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(userDataList);
            }
        });
    }

    @NonNull
    public List<UserData> getUserList(@NonNull Realm realm) {
        return realm.where(UserData.class).findAll();
    }

    private void executeTransaction(@NonNull Realm.Transaction transaction) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(transaction);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            close(realm);
        }
    }

    private void close(@Nullable Realm realm) {
        if (realm != null) {
            realm.close();
        }
    }

}
