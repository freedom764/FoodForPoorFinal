package foodforpoor1.help.poor;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import foodforpoor1.help.poor.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

public class Main3Activity extends AppCompatActivity{
EditText txt1,txt2,txt3,txt4,txt5,txt6;
ProgressBar progress;
Button btnUpdate,btnDelete;
String name;
int year,month,day,price,styear,stmonth,stday;
String yearAsString, monthAsString, dayAsString, priceAsString, styearAsString, stmonthAsString, stdayAsString,percentAsString;
DatabaseHelper myDB;
int yearwunder,monthwunder,daywunder;
ObjectAnimator progressAnimator;
int skidka;
double minuspercent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Calendar c = Calendar.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        yearwunder = c.get(Calendar.YEAR);
        monthwunder = c.get(Calendar.MONTH);
        daywunder = c.get(Calendar.DAY_OF_MONTH);
        txt1 = findViewById(R.id.editText);
        txt2 = findViewById(R.id.editText2);
        txt3 = findViewById(R.id.editText3);
        txt4 = findViewById(R.id.editText4);
        txt5 = findViewById(R.id.editText5);
        txt6 = findViewById(R.id.editText6);

        progress = findViewById(R.id.progressbar);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("Name");
        year = extras.getInt("Year");
        month = extras.getInt("Month");
        day = extras.getInt("Day");
        price = extras.getInt("Price");
        styear = extras.getInt("StYear");
        stmonth = extras.getInt("StMonth");
        stday = extras.getInt("StDay");

        yearAsString = Integer.toString(year);
        monthAsString = Integer.toString(month);
        dayAsString = Integer.toString(day);
        priceAsString = Integer.toString(price);
        styearAsString = Integer.toString(styear);
        stmonthAsString = Integer.toString(stmonth);
        stdayAsString = Integer.toString(stday);
        LayerDrawable progressBarDrawable = (LayerDrawable) progress.getProgressDrawable();
        Drawable progressDrawable = progressBarDrawable.getDrawable(1);

        txt1.setText(name);
        if(stmonth<10){

            if (stday<10){
                txt2.setText("0"+stdayAsString+".0"+stmonthAsString+"."+styearAsString);            }
            else{
                txt2.setText(stdayAsString+".0"+stmonthAsString+"."+styearAsString);              }
        }
        else {
            if (stday<10){
                txt2.setText("0"+stdayAsString+"."+stmonthAsString+"."+styearAsString);             }
            else {
                txt2.setText(stdayAsString + "." + stmonthAsString + "." + styearAsString);
            }}


        if(month<10){

            if (day<10){
                txt3.setText("0"+dayAsString+".0"+monthAsString+"."+yearAsString);            }
            else{
                txt3.setText(dayAsString+".0"+monthAsString+"."+yearAsString);              }
        }
        else {
            if (day<10){
                txt3.setText("0"+dayAsString+"."+monthAsString+"."+yearAsString);             }
            else{
                txt3.setText(dayAsString+"."+monthAsString+"."+yearAsString);             }}

        txt4.setText(priceAsString);
        Date datestart = getDate(styear,(stmonth-1),stday);
        Date dateend = getDate(year,(month-1),day);
        Date datetoday = getDate(yearwunder,monthwunder,daywunder);

        double millisstart = datestart.getTime();
        double millisend = dateend.getTime();
        double millistoday = datetoday.getTime();
        double percent = (((millistoday - millisstart) / (millisend - millisstart)) * 100);
        double daysleft = (millisend/8.64e+7)-(millistoday/8.64e+7);
        DecimalFormat df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.CEILING);
        String daysleftstring = df.format(daysleft);
        txt6.setText(daysleftstring);

if(percent<100 && percent>=90){

    progressDrawable.setColorFilter(ContextCompat.getColor(this, R.color.colorRed), PorterDuff.Mode.SRC_IN);
    txt6.setTextColor(getResources().getColor(R.color.colorRed));
}
if(percent<90 && percent>=75){
    progressDrawable.setColorFilter(ContextCompat.getColor(this, R.color.colorOrange), PorterDuff.Mode.SRC_IN);
    txt6.setTextColor(getResources().getColor(R.color.colorOrange));
}
        if(percent<75 && percent>=50){
            progressDrawable.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_IN);
            txt6.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        if(percent<50 && percent>=0){
            progressDrawable.setColorFilter(ContextCompat.getColor(this, R.color.colorGreen), PorterDuff.Mode.SRC_IN);
            txt6.setTextColor(getResources().getColor(R.color.colorGreen));
        }
        minuspercent = 100-percent;
        if(minuspercent<=50) {
            if (minuspercent <= 40) {
                if (minuspercent <= 30) {
                    if (minuspercent <= 20) {
                        if (minuspercent <= 15) {
                            if (minuspercent <= 10) {
                                if (minuspercent <= 7.5) {
                                    if (minuspercent <= 5) {
                                        if (minuspercent <= 2.5) {
                                            if (minuspercent <= 1) {
                                                if (minuspercent <= 0.5) {
                                                    skidka = 75;
                                                } else {
                                                    skidka = 70;
                                                }

                                            } else {
                                                skidka = 65;
                                            }

                                        } else {
                                            skidka = 60;
                                        }

                                    } else {
                                        skidka = 55;
                                    }

                                } else {
                                    skidka = 50;
                                }

                            } else {
                                skidka = 40;
                            }

                        } else {
                            skidka = 30;
                        }


                    } else {
                        skidka = 20;
                    }
                } else {
                    skidka = 10;
                }
            }
            else {skidka = 5;}
        }
        else{skidka = 0;}

        String percentintstring = df.format(percent);
        int percentint = Integer.parseInt(percentintstring);


        progressAnimator.ofInt(progress, "progress", percentint)
                .setDuration(1000)
                .start();
        double doubleprice = price;
        double doubleskidka = skidka;
        double doublepricewithskidka = (price-((doubleprice*doubleskidka)/100));

        String doublepricewithskidkastring = df.format(doublepricewithskidka);

        txt5.setText(doublepricewithskidkastring);

        myDB = new DatabaseHelper(this);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntentwunder = new Intent(Main3Activity.this, Main4Activity.class);
                Bundle extras1 = new Bundle();
                extras1.putString("Name1", name);
                extras1.putInt("Year1", year);
                extras1.putInt("Month1", month);
                extras1.putInt("Day1", day);
                extras1.putInt("Price1", price);
                extras1.putInt("StYear1", styear);
                extras1.putInt("StMonth1", stmonth);
                extras1.putInt("StDay1", stday);
                myIntentwunder.putExtras(extras1);
                startActivity(myIntentwunder);


            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder a_builder = new AlertDialog.Builder(Main3Activity.this);
                a_builder.setMessage("Вы действительно хотите удалить этот товар?")
                        .setCancelable(false)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){

                                myDB.deleteData(name);
                                Toast.makeText(Main3Activity.this, "Товар успешно удален", Toast.LENGTH_LONG).show();
                                Intent intentwunder = new Intent(Main3Activity.this, MainActivity.class);
                                startActivity(intentwunder);
                                finish();
                                MainActivity.fa.finish();

                            }
                        })
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = a_builder.create();
                alert.setTitle("Удаление товара");
                alert.show();


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
    public Date getDate(int year,int month,int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        Date date = calendar.getTime();
        return date;
    }
    public static Activity fa2;

    {
        fa2 = this;
    }

    }





