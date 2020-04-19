package com.example.homeworktopass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.homeworktopass.Contacts.ContactsContent;

import java.util.Calendar;
import java.util.Random;

public class AddContact extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener mDateListener;
    private TextView displayDate;
    private String date = "Birthday: no idea";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);


        displayDate = (TextView) findViewById(R.id.birthdayAdd);

        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddContact.this, R.style.ThemeOverlay_AppCompat_Light,
                        mDateListener, year, month,day);
                dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
                dialog.show();
            }
        });
        mDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d("TAG", "onDATESET: DD/MM/YYYY" + dayOfMonth +"/" +month +"/" + year);
                month = month+1;
                date = dayOfMonth + "/" + month+"/"+year;
                displayDate.setText(date);
            }
        };
    }

    public void addContact(View view) {
        EditText name = findViewById(R.id.nameAdd);
        EditText surname = findViewById(R.id.surnameAdd);
        EditText phoneNumber = findViewById(R.id.phonebumberAdd);

        String sName = name.getText().toString();
        String sSurname = surname.getText().toString();
        String sBirthday = date;
        String sPhoneNumber = phoneNumber.getText().toString();

        int number_control = 0;
        int lenght = sPhoneNumber.length();
        if(lenght ==9) {
            for(int i = 0; i <lenght ; i++) {
                if(sPhoneNumber.charAt(i) >= 48 && sPhoneNumber.charAt(i) <=57) {
                    number_control++;
                }
            }
        }



        Random rand = new Random();
        int rand_int = rand.nextInt(7); //number of pics i have
        String rand_string = Integer.toString(rand_int);
        //Log.d("OBRAZ", "obraz = " + rand_string );

        Intent data = new Intent();
        if(sName.isEmpty() || sPhoneNumber.isEmpty() || number_control != 9){
            setResult(RESULT_CANCELED, data);
        } else {
            ContactsContent.Contact contact = new ContactsContent.Contact(sName, sSurname,
                    sBirthday,rand_string,sPhoneNumber,ContactsContent.ITEMS.size()+101);
            data.putExtra("output_ack", contact);
            setResult(RESULT_OK, data);
        }
        finish();
    }
}
