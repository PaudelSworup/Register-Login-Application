package com.example.kctleavemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
   Button Register, Login;

   FirebaseDatabase db;
   DatabaseReference ref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Register = findViewById(R.id.btn_register);
        Login = findViewById(R.id.btn_login);

        Register.setOnClickListener(view -> replaceFragment(new Register_fragmanet()));
        Login.setOnClickListener(view -> replaceFragment(new Login_fragment()));
    }

    public void signUp(View v){
        Registration();
    }
    public void signIn(View v){Login();}





    public void Registration(){
        EditText txt_name = findViewById(R.id.et_name);
        EditText txt_uname = findViewById(R.id.et_uname);
        EditText txt_email = findViewById(R.id.et_email);
        EditText txt_phone = findViewById(R.id.et_mobile);
        EditText txt_faculty = findViewById(R.id.et_faculty);
        EditText txt_semester = findViewById(R.id.et_period);
        EditText txt_password  = findViewById(R.id.et_password);
        EditText txt_c_password = findViewById(R.id.et_confirm_password);

        String name = txt_name.getText().toString();
        String uname = txt_uname.getText().toString();
        String email = txt_email.getText().toString();
        String phone = txt_phone.getText().toString();
        String faculty = txt_faculty.getText().toString();
        String semester = txt_semester.getText().toString();
        String password = txt_password.getText().toString();
        String c_password = txt_c_password.getText().toString();
        String email_pattern = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        boolean check_email = email.matches(email_pattern);
        boolean check_password = password.equals(c_password);
        if(name.isEmpty() || name.length() < 3 ){
            txt_name.setError("Name is Required and must have atleast 3 character");
            return;
        }else txt_name.setError(null);

        if(uname.isEmpty()){
            txt_uname.setError("username must be of 3 charcters");
            return;
        }else txt_uname.setError(null);
        if(uname.length() != 3){
            txt_uname.setError("username must be of 3 charcters");
            return;
        }else txt_uname.setError(null);

        if(phone.isEmpty()){
            txt_phone.setError("Number is Required");
            return;
        }else txt_phone.setError(null);

        if(phone.length() != 10){
            txt_phone.setError("Phone Number must be of 10 digits");
            return;
        }else txt_phone.setError(null);

        if(faculty.isEmpty()){
            txt_faculty.setError("Please Enter Your Faculty");
            return;
        }else txt_faculty.setError(null);

        if(semester.isEmpty()){
            txt_semester.setError("Please enter your Running semester or year");
            return;
        }else txt_semester.setError(null);

        if(semester.length() < 3){
            txt_semester.setError("Please enter Semester or Year (eg 6th sem or 3rd year) ");
            return;
        }else txt_semester.setError(null);

        if(email.isEmpty()){
            txt_email.setError("Email is Required");
            return;
        }else txt_email.setError(null);

        if(!check_email){
            txt_email.setError("Please enter Valid Email Address");
            return;
        }else txt_email.setError(null);

        if(password.isEmpty() ){
            txt_password.setError("Please Enter your Password");
            return;
        }else txt_password.setError(null);

        if(c_password.isEmpty()){
            txt_c_password.setError("Please Enter your Confirmation Password");
            return;
        }else txt_name.setError(null);
        if(!check_password){
            txt_password.setError("Password dosen't match");
            txt_c_password.setError("Password dosen't match");
        }
        else{
            txt_password.setError(null);
            txt_c_password.setError(null);
            db = FirebaseDatabase.getInstance();
            ref = db.getReference("Students");


            String dbname = txt_name.getText().toString();
            String dbuname = txt_uname.getText().toString();
            String dbemail = txt_email.getText().toString();
            String dbphone = txt_phone.getText().toString();
            String dbfaculty = txt_faculty.getText().toString();
            String dbsemester = txt_semester.getText().toString();
            String dbpassword = txt_password.getText().toString();
            String dbc_password = txt_c_password.getText().toString();

            Registration dbr1 = new Registration(dbname,dbuname,dbemail,dbphone,dbfaculty,dbsemester,dbpassword,dbc_password);

            ref.child(dbuname).setValue(dbr1);


            Context context = getApplicationContext();
            CharSequence text = "Data submitted ";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }



    public void Login(){
        EditText txt_password  = findViewById(R.id.et_password);
        EditText txt_uname = findViewById(R.id.et_uname);

        String password = txt_password.getText().toString();
        String uname = txt_uname.getText().toString();


        if(uname.isEmpty() && uname.length() < 3){
            txt_uname.setError("user name is Required and must be equal to or greater than 3");
            return;
        }else txt_uname.setError(null);

        if(password.isEmpty() ){
            txt_password.setError("Please Enter your Password");
        }else txt_password.setError(null);

        if(!password.isEmpty()){
            final String dbpass = txt_password.getText().toString();
            final String dbuname = txt_uname.getText().toString();
            db = FirebaseDatabase.getInstance();
            ref = db.getReference("Students");

            Query Check_user = ref.orderByChild("uname").equalTo(dbuname);

            Check_user.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        txt_uname.setError(null);
                        String Pass_Check = snapshot.child(dbuname).child("password").getValue(String.class);
                        if(Pass_Check.equals(dbpass)){
                            txt_password.setError(null);
                            Intent in = new Intent(getApplicationContext(),Dashboard.class);
                            startActivity(in);
                            finish();
                        }else txt_password.setError("Password dosent match");
                    }else txt_uname.setError("User dosen't exist");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }

    }








    private void replaceFragment(Fragment fg) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.linear, fg);
        ft.commit();
    }
}