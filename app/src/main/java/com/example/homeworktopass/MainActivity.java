package com.example.homeworktopass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.homeworktopass.Contacts.ContactsContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        contactsFragment.OnListFragmentInteractionListener,
        DeleteDialog.OnDeleteDialogInteractionListener,
        CallDialog.OnCallDialogInteractionListener{

    private int currentPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityAdd = new Intent(getApplicationContext(), AddContact.class);
                startActivityForResult(activityAdd,1);
            }
        });
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            View v = findViewById(R.id.contactsFragment);
            ContactsContent.Contact c = data.getExtras().getParcelable("output_ack");
            ContactsContent.addItem(c);
            ((contactsFragment) getSupportFragmentManager().findFragmentById(R.id.contactsFragment)).notifyDataChange();

            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow( v.getWindowToken(),0);

            Toast.makeText(getApplicationContext(),getText(R.string.back_message), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(ContactsContent.Contact contact, int position) {
        Toast.makeText(this,getString(R.string.details_msg), Toast.LENGTH_SHORT).show();

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            displayTaskInFragment(contact);
        } else{
            startContactDetailsActivity(contact, position);
        }
    }

    @Override
    public void onListFragmentLongClickInteraction(int position) {
        showCallDialog();
        currentPosition = position;
    }

    @Override
    public void onListDeleteContact(int position) {
        deleteContact(position);
    }

    public void showDeleteDialog(){
        DeleteDialog.newInstance().show(getSupportFragmentManager(),"ack");

    }
    public void showCallDialog(){
        CallDialog.newInstance().show(getSupportFragmentManager(),"ack");

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        if(currentPosition != -1 && currentPosition < ContactsContent.ITEMS.size()){
            ContactsContent.removeItem(currentPosition);
            ((contactsFragment) getSupportFragmentManager().findFragmentById(R.id.contactsFragment)).notifyDataChange();
        }

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(this,getString(R.string.negativeDeleteClick),Toast.LENGTH_SHORT).show();

    }

    public void deleteContact(int position){
        showDeleteDialog();
        currentPosition = position;
    }


    /*@Override
    public void onCallPositiveClick(DialogFragment dialog) {
        Toast.makeText(this,getString(R.string.positiveCallClick),Toast.LENGTH_SHORT).show();

    }
    String name = ContactsContent.calling(currentPosition);

    @Override
    public void onCallNegativeClick(DialogFragment dialog) {
        Toast.makeText(this,getString(R.string.negativeCallClick),Toast.LENGTH_SHORT).show();

    }*/

    @Override
    public void onCallPositiveClick(DialogFragment dialog) {

        Toast.makeText(this,getString(R.string.positiveCallClick),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCallNegativeClick(DialogFragment dialog) {
        Toast.makeText(this,getString(R.string.negativeCallClick),Toast.LENGTH_SHORT).show();

    }


    // DETAILS VIEW
    public static final String contactDetailActivity = "contactDetailActivity";

    private void startContactDetailsActivity(ContactsContent.Contact contact, int position){
        Intent intent = new Intent(this, ContactDetailsActivity.class);
        intent.putExtra(contactDetailActivity, contact);
        startActivity(intent);
    }

    //LANDSCaPE

    private void displayTaskInFragment(ContactsContent.Contact contact) {
        ContactDetails contactDetails = ((ContactDetails) getSupportFragmentManager().findFragmentById(R.id.displayFragment));
        if (contactDetails != null) {
            contactDetails.displayContact(contact);
        }
    }


}
