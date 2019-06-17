package foodforpoor1.help.poor;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import foodforpoor1.help.poor.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main2Activity extends AppCompatActivity {

    DatabaseHelper myDB;
    Button btnAdd;
    EditText editText1,editText2,editText3,editText4,editText5,editText6, editText7,editText8;
int year11,month11,day11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Calendar c = Calendar.getInstance();
        year11 = c.get(Calendar.YEAR);
        month11 = c.get(Calendar.MONTH);
        day11 = c.get(Calendar.DAY_OF_MONTH);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        editText1 = (EditText) findViewById(R.id.name);
        editText2 = (EditText) findViewById(R.id.year);
        editText3 = (EditText) findViewById(R.id.month);
        editText4 = (EditText) findViewById(R.id.day);
        editText5 = (EditText) findViewById(R.id.price);
        editText6 = (EditText) findViewById(R.id.styear);
        editText7 = (EditText) findViewById(R.id.stmonth);
        editText8 = (EditText) findViewById(R.id.stday);



        btnAdd = (Button) findViewById(R.id.btn_add);
        myDB = new DatabaseHelper(this);





        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = editText1.getText().toString();
                String year2 = editText2.getText().toString();
                String month2 = editText3.getText().toString();
                String day2 = editText4.getText().toString();
                String price2 = editText5.getText().toString();
                String styear2 = editText6.getText().toString();
                String stmonth2 = editText7.getText().toString();
                String stday2 = editText8.getText().toString();

                if (name1.length() == 0 || year2.length() == 0 || month2.length() == 0 || day2.length() == 0 || price2.length() == 0
                        || styear2.length() == 0 || stmonth2.length() == 0 || stday2.length() == 0 ) {
                    Toast.makeText(Main2Activity.this, "Вам нужно заполнить все поля", Toast.LENGTH_LONG).show();


                } else {
                    long year3 = Long.parseLong(year2);
                    long styear3 = Long.parseLong(styear2);
                    long price3 = Long.parseLong(price2);
                    long month3 = Long.parseLong(month2);
                    long stmonth3 = Long.parseLong(stmonth2);
                    long day3 = Long.parseLong(day2);
                    long stday3 = Long.parseLong(stday2);
                    if (year3 > 0 && year3 < 10000 && styear3 > 0 && styear3 < 10000 && price3 > 0 && price3 < 2000000000
                    && month3 > 0 && month3<=12 && stmonth3 > 0 && stmonth3<=12 && day3 > 0 && day3<=31 && stday3 > 0 && stday3<=31 ){
                        int year1 = Integer.parseInt(year2);
                    int month1 = Integer.parseInt(month2);
                    int day1 = Integer.parseInt(day2);
                    int price1 = Integer.parseInt(price2);
                    int styear1 = Integer.parseInt(styear2);
                    int stmonth1 = Integer.parseInt(stmonth2);
                    int stday1 = Integer.parseInt(stday2);
                    Calendar mycal1 = getmycal(year1, (month1 - 1));
                    Calendar mycal2 = getmycal(styear1, (stmonth1 - 1));
                    int daysInMonth1 = mycal1.getActualMaximum(Calendar.DAY_OF_MONTH);
                    int daysInMonth2 = mycal2.getActualMaximum(Calendar.DAY_OF_MONTH);
                    Cursor data = myDB.searchForData(name1);
                    if (data.getCount() == 0) {

                        if (year1 > 0 && year1 < 10000 && month1 > 0 && month1 <= 12 && day1 > 0 && day1 <= daysInMonth1
                                && styear1 > 0 && styear1 < 10000 && stmonth1 > 0 && stmonth1 <= 12 && stday1 > 0 && stday1 <= daysInMonth2
                                && price1 > 0 && price1 < 2000000000) {
                            if ((year1 < year11) || (year1 == year11 && month1 < (month11 + 1)) || (year1 == year11 && month1 == (month11 + 1) && day1 <= day11)) {
                                AlertDialog.Builder a_builder = new AlertDialog.Builder(Main2Activity.this);
                                a_builder.setMessage("Вы ввели истекший срок годности!")
                                        .setCancelable(true)
                                        .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {


                                            }
                                        });

                                AlertDialog alert = a_builder.create();
                                alert.setTitle("Внимание!");
                                alert.show();
                            } else {
                                if ((styear1 > year11) || (styear1 == year11 && stmonth1 > (month11 + 1)) || (styear1 == year11 && stmonth1 == (month11 + 1) && stday1 > day11)) {
                                    AlertDialog.Builder a_builder = new AlertDialog.Builder(Main2Activity.this);
                                    a_builder.setMessage("Данный товар еще не был изготовлен!")
                                            .setCancelable(true)
                                            .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {


                                                }
                                            });

                                    AlertDialog alert = a_builder.create();
                                    alert.setTitle("Внимание!");
                                    alert.show();
                                } else {
                                    AddData(name1, year1, month1, day1, price1, styear1, stmonth1, stday1);

                                    editText1.setText("");
                                    editText2.setText("");
                                    editText3.setText("");
                                    editText4.setText("");
                                    editText5.setText("");
                                    editText6.setText("");
                                    editText7.setText("");
                                    editText8.setText("");


                                    Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    MainActivity.fa.finish();
                                }
                            }


                        } else {

                            if (day1 > daysInMonth1) {
                                if (stday1 > daysInMonth2) {
                                    AlertDialog.Builder a_builder = new AlertDialog.Builder(Main2Activity.this);
                                    a_builder.setMessage("В выбранном для даты изготовления месяце - " + daysInMonth2 + " дней, " +
                                            "а в выбранном для даты окончания срока годности месяце - " + daysInMonth1 + " дней")
                                            .setCancelable(true)
                                            .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            });
                                    AlertDialog alert = a_builder.create();
                                    alert.setTitle("Внимание!");
                                    alert.show();
                                } else {
                                    AlertDialog.Builder a_builder = new AlertDialog.Builder(Main2Activity.this);
                                    a_builder.setMessage("В выбранном для даты окончания срока годности месяце - " + daysInMonth1 + " дней")
                                            .setCancelable(true)
                                            .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            });
                                    AlertDialog alert = a_builder.create();
                                    alert.setTitle("Внимание!");
                                    alert.show();
                                }
                            } else {
                                if (stday1 > daysInMonth2) {
                                    AlertDialog.Builder a_builder = new AlertDialog.Builder(Main2Activity.this);
                                    a_builder.setMessage("В выбранном для даты изготовления месяце - " + daysInMonth2 + " дней")
                                            .setCancelable(true)
                                            .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            });
                                    AlertDialog alert = a_builder.create();
                                    alert.setTitle("Внимание!");
                                    alert.show();
                                } else {
                                    Toast.makeText(Main2Activity.this, "Вы должны правильно заполнить все даты!", Toast.LENGTH_LONG).show();
                                }
                            }

                        }

                    } else {
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(Main2Activity.this);
                        a_builder.setMessage("У вас уже есть товар с таким названием, переименуйте его! " +
                                "(Вы можете дополнительно указать ему уникальный код)")
                                .setCancelable(true)
                                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {


                                    }
                                });

                        AlertDialog alert = a_builder.create();
                        alert.setTitle("Внимание!");
                        alert.show();
                    }
                }
                    else{
                        if (styear3 <= 0 || styear3 >= 10000) {
                            Toast.makeText(Main2Activity.this, "Год изготовления должен быть больше 0 и меньше 10000", Toast.LENGTH_LONG).show();
                        }
                        if (year3 <= 0 || year3 >= 10000) {
                            Toast.makeText(Main2Activity.this, "Год окончания срока годности должен быть больше 0 и меньше 10000", Toast.LENGTH_LONG).show();
                        }
                        if (stmonth3 <= 0 || stmonth3 > 12) {
                            Toast.makeText(Main2Activity.this, "Диапазон месяца изготовления: от 1 до 12", Toast.LENGTH_LONG).show();
                        }
                        if (month3 <= 0 || month3 > 12) {
                            Toast.makeText(Main2Activity.this, "Диапазон месяца окончания срока годности: от 1 до 12", Toast.LENGTH_LONG).show();
                        }
                        if (stday3 <= 0 || stday3 > 31) {
                            Toast.makeText(Main2Activity.this, "Диапазон дня изготовления: от 1 до 31", Toast.LENGTH_LONG).show();
                        }
                        if (day3 <= 0 || day3 > 31) {
                            Toast.makeText(Main2Activity.this, "Диапазон дня окончания срока годности: от 1 до 31", Toast.LENGTH_LONG).show();
                        }
                        if (price3 <= 0 || price3 >= 2000000000) {
                            Toast.makeText(Main2Activity.this, "Цена должна быть больше 0 и меньше 2 миллиардов", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== android.R.id.home) {
            this.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public void AddData(String name1, int year1, int month1, int day1, int price1, int styear1, int stmonth1, int stday1) {

        boolean insertData = myDB.addData(name1,year1,month1,day1,price1,styear1,stmonth1,stday1);

        if(insertData==true){
            Toast.makeText(this, "Товар успешно добавлен!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Что-то пошло не так :(", Toast.LENGTH_LONG).show();
        }
    }
    public Calendar getmycal (int iyear,int imonth){
int iday = 1;
        Calendar mycal = new GregorianCalendar(iyear, imonth, iday);

return mycal;

    }
}
