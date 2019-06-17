package foodforpoor1.help.poor;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import foodforpoor1.help.poor.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, android.support.v7.widget.SearchView.OnQueryTextListener {
    DatabaseHelper myDB;
    MyAdapter mAdapter;
    RecyclerView mRecyclerView;
    int year,month,day;
    int count;
    ArrayList<MyObject> myObjects;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.nav_view);


        myDB = new DatabaseHelper(this);
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        Cursor cursor = myDB.forCount(year,month,day);
        count = cursor.getCount();
        if (count != 0) {
            alertWhenDelete(count);}
        myDB.deleteIstekshiySrokGodnosy(year,month,day);


        mRecyclerView = findViewById(R.id.recyclerView);

        myObjects = new ArrayList<>();


        Cursor data = myDB.getListContents();
        if(data.getCount() == 0){
            AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
            a_builder.setMessage("Ваш список товаров пуст! Нажмите на желтую кнопку со знаком \"+\" и добавьте новый товар." +
                                      " Затем, вы можете нажать на созданный товар, чтобы просмотреть его!")
                    .setCancelable(false)
                    .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });

            AlertDialog alert = a_builder.create();
            alert.setTitle("Список товаров");
            alert.show();
        }else{
            while(data.moveToNext()){
                int year228 = data.getInt(2);
                int month228 = data.getInt(3);
                int day228 = data.getInt(4);
                int styear228 = data.getInt(6);
                int stmonth228 = data.getInt(7);
                int stday228 = data.getInt(8);
                String datestart,dateend;
                if(stmonth228<10){

                    if (stday228<10){
                        datestart = "0"+stday228+".0"+stmonth228+"."+styear228;            }
                    else{
                        datestart = stday228+".0"+stmonth228+"."+styear228;                 }
                }
                else {
                    if (stday228<10){
                        datestart = "0"+stday228+"."+stmonth228+"."+styear228;             }
                    else {
                        datestart = stday228+"."+stmonth228+"."+styear228;
                    }}


                if(month228<10){

                    if (day228<10){
                        dateend = "0"+day228+".0"+month228+"."+year228;             }
                    else{
                        dateend = day228+".0"+month228+"."+year228;             }
                }
                else {
                    if (day228<10){
                        dateend = "0"+day228+"."+month228+"."+year228;                }
                    else{
                        dateend = day228+"."+month228+"."+year228;              }}



                        myObjects.add(new MyObject(data.getString(1), datestart+"—"+dateend));



                mAdapter = new MyAdapter(myObjects);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.updateData(myObjects);




            }
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
                                   @Override
                                   public void onClick(View v) {

                                       AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                                       a_builder.setMessage("Вы хотите добавить новый товар?")
                                               .setCancelable(true)
                                               .setPositiveButton("Да", new DialogInterface.OnClickListener(){
                                                   @Override
                                                   public void onClick(DialogInterface dialog, int which){

                                                       Intent intent = new Intent(".Main2Activity");
                                                       startActivity(intent);


                                                   }
                                               })
                                               .setNegativeButton("Нет", new DialogInterface.OnClickListener(){
                                                   @Override
                                                   public void onClick(DialogInterface dialog, int which){
                                                       dialog.cancel();
                                                   }
                                               });
                                       AlertDialog alert = a_builder.create();
                                       alert.setTitle("Добавление товара");
                                       alert.show();
                                   }
                               }
        );
        navigationView.setNavigationItemSelectedListener(this);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
if (savedInstanceState == null){
    navigationView.setCheckedItem(R.id.nav_list);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        android.support.v7.widget.SearchView searchView = (SearchView) menuItem.getActionView();
        Cursor data1 = myDB.getListContents();
        if(data1.getCount() != 0){
        searchView.setOnQueryTextListener(this);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_list) {
navigationView.setCheckedItem(R.id.nav_list);

        } else if (id == R.id.nav_instruction) {
            Intent intent1 = new Intent(this, Instruction.class);
            startActivity(intent1);
            navigationView.setCheckedItem(R.id.nav_instruction);
            fa.finish();

        } else if (id == R.id.nav_aims) {
            Intent intent1 = new Intent(this, Aims.class);
            startActivity(intent1);
            navigationView.setCheckedItem(R.id.nav_aims);
            fa.finish();

        } else if (id == R.id.nav_team) {
            Intent intent1 = new Intent(this, Team.class);
            startActivity(intent1);
            navigationView.setCheckedItem(R.id.nav_team);
            fa.finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public AlertDialog alertWhenDelete(int count){
        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
        a_builder.setMessage("Срок годности товаров истек, поэтому мы удалили их из вашего списка товаров! (Число удаленных товаров: "+count+")")
                .setCancelable(false)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){



                    }
                });
        AlertDialog alert = a_builder.create();
        alert.setTitle("Истек срок годности товаров");
        alert.show();
        return alert;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        mAdapter.getFilter().filter(newText);
        return true;
    }
    public static Activity fa;
    {
        fa = this;
    }
}
