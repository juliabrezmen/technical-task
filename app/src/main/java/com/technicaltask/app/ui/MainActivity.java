package com.technicaltask.app.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.technicaltask.app.R;
import com.technicaltask.app.adapters.UserAdapter;
import com.technicaltask.app.database.UserData;
import com.technicaltask.app.presenters.MainPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private MainPresenter presenter;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        initToolBar();
        initView();

        presenter = new MainPresenter();
        presenter.onAttachActivity(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.main_menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_check:
                presenter.onToolbarCheckClicked();
                return true;
            case R.id.action_search:
                presenter.onToolbarSearchClicked();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        presenter.onDetachActivity();
        super.onDestroy();
    }

    public void setUserList(@NonNull List<UserData> userDataList) {
        userAdapter.updateData(userDataList);
        userAdapter.notifyDataSetChanged();
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initView() {
        userAdapter = new UserAdapter(getApplicationContext());
        RecyclerView.LayoutManager rvManager = new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false);
        RecyclerView rvUserList = (RecyclerView) findViewById(R.id.rv_users_list);
        rvUserList.setHasFixedSize(true);
        rvUserList.setLayoutManager(rvManager);
        rvUserList.setAdapter(userAdapter);

        FloatingActionButton btnAddUser = (FloatingActionButton) findViewById(R.id.btn_add_user);
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddUserClicked();
            }
        });
    }
}
