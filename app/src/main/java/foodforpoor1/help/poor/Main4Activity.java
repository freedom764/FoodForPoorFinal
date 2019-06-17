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

public class Main4Activity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText name3, year3, month3, day3, price3,styear3,stmonth3,stday3;
    Button btn_update;
    String namefrom,yearfromAsString,monthfromAsString,dayfromAsString,pricefromAsString,styearfromAsString,stmonthfromAsString,stdayfromAsString;
    int yearfrom,monthfrom,dayfrom,pricefrom,styearfrom,stmonthfrom,stdayfrom;
    int year11,month11,day11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Calendar c = Calendar.getInstance();
        year11 = c.get(Calendar.YEAR);
        month11 = c.get(Calendar.MONTH);
        day11 = c.get(Calendar.DAY_OF_MONTH);
        name3 = findViewById(R.id.name1);
        year3 = findViewById(R.id.year1);
        month3 = findViewById(R.id.month1);
        day3 = findViewById(R.id.day1);
        price3 = findViewById(R.id.price1);
        styear3 = findViewById(R.id.styear1);
        stmonth3 = findViewById(R.id.stmonth1);
        stday3 = findViewById(R.id.stday1);
        btn_update = findViewById(R.id.btn_update);
        myDB = new DatabaseHelper(this);
        Bundle extras1 = getIntent().getExtras();
        namefrom = extras1.getString("Name1");
        yearfrom = extras1.getInt("Year1");
        monthfrom = extras1.getInt("Month1");
        dayfrom = extras1.getInt("Day1");
        pricefrom = extras1.getInt("Price1");
        styearfrom = extras1.getInt("StYear1");
        stmonthfrom = extras1.getInt("StMonth1");
        stdayfrom = extras1.getInt("StDay1");

        yearfromAsString = Integer.toString(yearfrom);
        monthfromAsString = Integer.toString(monthfrom);
        dayfromAsString = Integer.toString(dayfrom);
        pricefromAsString = Integer.toString(pricefrom);
        styearfromAsString = Integer.toString(styearfrom);
        stmonthfromAsString = Integer.toString(stmonthfrom);
        stdayfromAsString = Integer.toString(stdayfrom);

        name3.setText(namefrom);
        year3.setText(yearfromAsString);
        month3.setText(monthfromAsString);
        day3.setText(dayfromAsString);
        price3.setText(pricefromAsString);
        styear3.setText(styearfromAsString);
        stmonth3.setText(stmonthfromAsString);
        stday3.setText(stdayfromAsString);


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name3.getText().toString();
                String year2 = year3.getText().toString();
                String month2 = month3.getText().toString();
                String day2 = day3.getText().toString();
                String price2 = price3.getText().toString();
                String styear2 = styear3.getText().toString();
                String stmonth2 = stmonth3.getText().toString();
                String stday2 = stday3.getText().toString();

                if (name1.length() == 0 || year2.length() == 0 || month2.length() == 0 || day2.length() == 0 || price2.length() == 0
                        || styear2.length() == 0 || stmonth2.length() == 0 || stday2.length() == 0 ) {
                    Toast.makeText(Main4Activity.this, "Вам нужно заполнить все поля", Toast.LENGTH_LONG).show();


                } else {
                    long year4 = Long.parseLong(year2);
                    long styear4 = Long.parseLong(styear2);
                    long price4 = Long.parseLong(price2);
                    long month4 = Long.parseLong(month2);
                    long stmonth4 = Long.parseLong(stmonth2);
                    long day4 = Long.parseLong(day2);
                    long stday4 = Long.parseLong(stday2);
                    if (year4 > 0 && year4 < 10000 && styear4 > 0 && styear4 < 10000 && price4 > 0 && price4 < 2000000000
                            && month4 > 0 && month4<=12 && stmonth4 > 0 && stmonth4<=12 && day4 > 0 && day4<=31 && stday4 > 0 && stday4<=31) {
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
                    if (data.getCount() == 0 || name1.equals(namefrom)) {

                        if (year1 > 0 && year1 < 10000 && month1 > 0 && month1 <= 12 && day1 > 0 && day1 <= daysInMonth1
                                && styear1 > 0 && styear1 < 10000 && stmonth1 > 0 && stmonth1 <= 12 && stday1 > 0 && stday1 <= daysInMonth2
                                && price1 > 0 && price1 < 2000000000) {
                            if ((year1 < year11) || (year1 == year11 && month1 < (month11 + 1)) || (year1 == year11 && month1 == (month11 + 1) && day1 <= day11)) {
                                AlertDialog.Builder a_builder = new AlertDialog.Builder(Main4Activity.this);
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
                                    AlertDialog.Builder a_builder = new AlertDialog.Builder(Main4Activity.this);
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
                                    myDB.updateData(namefrom, name1, year1, month1, day1, price1, styear1, stmonth1, stday1);

                                    name3.setText("");
                                    year3.setText("");
                                    month3.setText("");
                                    day3.setText("");
                                    price3.setText("");
                                    styear3.setText("");
                                    stmonth3.setText("");
                                    stday3.setText("");

                                    Intent intent = new Intent(Main4Activity.this, MainActivity.class);
                                    startActivity(intent);
                                    MainActivity.fa.finish();
                                    finish();
                                    Main3Activity.fa2.finish();
                                }
                            }


                        } else {

                            if (day1 > daysInMonth1) {
                                if (stday1 > daysInMonth2) {
                                    AlertDialog.Builder a_builder = new AlertDialog.Builder(Main4Activity.this);
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
                                    AlertDialog.Builder a_builder = new AlertDialog.Builder(Main4Activity.this);
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
                                    AlertDialog.Builder a_builder = new AlertDialog.Builder(Main4Activity.this);
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
                                    Toast.makeText(Main4Activity.this, "Вы должны правильно заполнить все даты!", Toast.LENGTH_LONG).show();
                                }
                            }

                        }

                    } else {
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(Main4Activity.this);
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
                        if (styear4 <= 0 || styear4 >= 10000) {
                            Toast.makeText(Main4Activity.this, "Год изготовления должен быть больше 0 и меньше 10000", Toast.LENGTH_LONG).show();
                        }
                        if (year4 <= 0 || year4 >= 10000) {
                            Toast.makeText(Main4Activity.this, "Год окончания срока годности должен быть больше 0 и меньше 10000", Toast.LENGTH_LONG).show();
                        }
                        if (stmonth4 <= 0 || stmonth4 > 12) {
                            Toast.makeText(Main4Activity.this, "Диапазон месяца изготовления: от 1 до 12", Toast.LENGTH_LONG).show();
                        }
                        if (month4 <= 0 || month4 > 12) {
                            Toast.makeText(Main4Activity.this, "Диапазон месяца окончания срока годности: от 1 до 12", Toast.LENGTH_LONG).show();
                        }
                        if (stday4 <= 0 || stday4 > 31) {
                            Toast.makeText(Main4Activity.this, "Диапазон дня изготовления: от 1 до 31", Toast.LENGTH_LONG).show();
                        }
                        if (day4 <= 0 || day4 > 31) {
                            Toast.makeText(Main4Activity.this, "Диапазон дня окончания срока годности: от 1 до 31", Toast.LENGTH_LONG).show();
                        }
                        if (price4 <= 0 || price4 >= 2000000000) {
                            Toast.makeText(Main4Activity.this, "Цена должна быть больше 0 и меньше 2 миллиардов", Toast.LENGTH_LONG).show();
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
    public Calendar getmycal (int iyear,int imonth){
        int iday = 1;
        Calendar mycal = new GregorianCalendar(iyear, imonth, iday);

        return mycal;

    }
}