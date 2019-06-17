package foodforpoor1.help.poor;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import foodforpoor1.help.poor.R;

public class Instruction extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null){
            navigationView.setCheckedItem(R.id.nav_instruction);

        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_list) {
            Intent intent1 = new Intent(this, MainActivity.class);
            startActivity(intent1);
            navigationView.setCheckedItem(R.id.nav_list);
            fainstruction.finish();

        } else if (id == R.id.nav_instruction) {
            navigationView.setCheckedItem(R.id.nav_instruction);

        } else if (id == R.id.nav_aims) {
            Intent intent1 = new Intent(this, Aims.class);
            startActivity(intent1);
            navigationView.setCheckedItem(R.id.nav_aims);
            fainstruction.finish();

        } else if (id == R.id.nav_team) {
            Intent intent1 = new Intent(this, Team.class);
            startActivity(intent1);
            navigationView.setCheckedItem(R.id.nav_team);
            fainstruction.finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public static Activity fainstruction;
    {
        fainstruction = this;
    }
    }

