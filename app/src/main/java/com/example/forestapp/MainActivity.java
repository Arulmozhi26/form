package com.example.forestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    TextInputLayout tname,temail,taddress,tcity,tstate,tpin,tphno,theight,tweight,tmedhis;
    RadioButton genderopt,allergiesopt,bpopt,diabetesopt,surgeryopt;
    RadioGroup rgender,rallergies,rbp,rdiabetes,rsurgery;
    Button signup;
    String gender,allergies,bp,diabetes,surgery;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    helperclass hp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Register Form");


        tname= findViewById(R.id.etname);
        temail= findViewById(R.id.etemail);
        taddress= findViewById(R.id.etaddress);
        tcity= findViewById(R.id.etcity);
        tstate= findViewById(R.id.etstate);
        tpin= findViewById(R.id.etpin);
        tphno= findViewById(R.id.etphno);
        theight= findViewById(R.id.etheight);
        tweight= findViewById(R.id.etweight);
        tmedhis= findViewById(R.id.etmedhis);
        rgender= findViewById(R.id.etgender);
        rallergies= findViewById(R.id.etallergies);
        rbp= findViewById(R.id.etbp);

        signup= findViewById(R.id.button_submit);
        rgender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                genderopt=genderopt.findViewById(i);
                rdiabetes= findViewById(R.id.etdiabetes);
                rsurgery= findViewById(R.id.etsurgery);

                switch (i){
                    case R.id.etmale:
                        gender=genderopt.getText().toString();
                        hp.setGender(gender);
                        break;
                    case R.id.etfemale:
                        gender=genderopt.getText().toString();
                       hp.setGender(gender);
                        break;
                    default:
                }
            }
        });
        rallergies.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                allergiesopt=allergiesopt.findViewById(i);

                switch (i){
                    case R.id.etayes:
                        allergies=allergiesopt.getText().toString();
                       hp.setAllergies(allergies);
                        break;
                    case R.id.etano:
                        allergies=allergiesopt.getText().toString();
                        hp.setAllergies(allergies);
                        break;
                    default:
                }
            }
        });

        rbp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                bpopt=bpopt.findViewById(i);

                switch (i){
                    case R.id.etlow:
                        bp=bpopt.getText().toString();
                       hp.setBp(bp);
                        break;
                    case R.id.etnormal:
                        bp=bpopt.getText().toString();
                        hp.setBp(bp);
                        break;
                    case R.id.ethigh:
                        bp=bpopt.getText().toString();
                       hp.setBp(bp);
                        break;
                    default:
                }
            }
        });
        rdiabetes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                diabetesopt=diabetesopt.findViewById(i);

                switch (i){
                    case R.id.etdyes:
                        diabetes=diabetesopt.getText().toString();
                       hp.setDiabetes(diabetes);
                        break;
                    case R.id.etdno:
                        diabetes=diabetesopt.getText().toString();
                        hp.setDiabetes(diabetes);
                        break;
                    default:
                }
            }
        });
        rsurgery.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                surgeryopt=surgeryopt.findViewById(i);

                switch (i){
                    case R.id.etsyes:
                        surgery=surgeryopt.getText().toString();
                        hp.setSurgery(surgery);
                        break;
                    case R.id.etsno:
                        surgery=surgeryopt.getText().toString();
                        hp.setSurgery(surgery);
                        break;
                    default:
                }
            }
        });



        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("courses");
                String name=tname.getEditText().getText().toString().trim();
                String email=temail.getEditText().getText().toString().trim();
                String address=taddress.getEditText().getText().toString().trim();
                String city=tcity.getEditText().getText().toString().trim();
                String state=tstate.getEditText().getText().toString().trim();
                String pin=tpin.getEditText().getText().toString().trim();
                String phno=tphno.getEditText().getText().toString().trim();
                String height=theight.getEditText().getText().toString().trim();
                String weight=tweight.getEditText().getText().toString().trim();
                String medhis=tmedhis.getEditText().getText().toString().trim();

                helperclass hp= new helperclass();
                reference.child(phno).setValue(hp);
                validation();



                }


        });
    }

    public void validation(){
        String mname=tname.getEditText().getText().toString().trim();
        String memail=temail.getEditText().getText().toString().trim();
        String maddress=taddress.getEditText().getText().toString().trim();
        String mcity=tcity.getEditText().getText().toString().trim();
        String mstate=tstate.getEditText().getText().toString().trim();
        String mpin=tpin.getEditText().getText().toString().trim();
        String mphno=tphno.getEditText().getText().toString().trim();
        String mheight=theight.getEditText().getText().toString().trim();
        String mweight=tweight.getEditText().getText().toString().trim();
        String mmedhis=tmedhis.getEditText().getText().toString().trim();

        if(mname.matches("")){
            Toast.makeText(MainActivity.this,"Enter Name",Toast.LENGTH_LONG).show();
            return;
        }
        if(!mname.matches("[a-zA-Z]+")){
            Toast.makeText(MainActivity.this,"Enter Valid Name",Toast.LENGTH_LONG).show();
            return;
        }
        if(memail.matches("")){
            Toast.makeText(MainActivity.this,"Enter Email",Toast.LENGTH_LONG).show();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(memail).matches()){
            Toast.makeText(MainActivity.this,"Enter Valid Email",Toast.LENGTH_LONG).show();
            return;
        }
        if(maddress.matches("")){
            Toast.makeText(MainActivity.this,"Enter Address",Toast.LENGTH_LONG).show();
            return;
        }
        if(mcity.matches("")){
            Toast.makeText(MainActivity.this,"Enter City",Toast.LENGTH_LONG).show();
            return;
        }
        if(!mcity.matches("[a-zA-Z]+")){
            Toast.makeText(MainActivity.this,"Enter Valid City",Toast.LENGTH_LONG).show();
            return;
        }
        if(mstate.matches("")){
            Toast.makeText(MainActivity.this,"Enter State",Toast.LENGTH_LONG).show();
            return;
        }
        if(!mstate.matches("[a-zA-Z]+")){
            Toast.makeText(MainActivity.this,"Enter Valid State",Toast.LENGTH_LONG).show();
            return;
        }
        if(mpin.matches("")){
            Toast.makeText(MainActivity.this,"Enter Pincode",Toast.LENGTH_LONG).show();
            return;
        }
        if(!mpin.matches("[0-9]+") || mpin.length()!=6){
            Toast.makeText(MainActivity.this,"Enter Valid Pincode",Toast.LENGTH_LONG).show();
            return;
        }
        if(mphno.matches("")){
            Toast.makeText(MainActivity.this,"Enter Phone Number",Toast.LENGTH_LONG).show();
            return;
        }
        if(!Patterns.PHONE.matcher(mphno).matches() || mphno.length()!=10){
            Toast.makeText(MainActivity.this,"Enter Valid Phone Number",Toast.LENGTH_LONG).show();
            return;
        }
        if(mheight.matches("")){
            Toast.makeText(MainActivity.this,"Enter Height",Toast.LENGTH_LONG).show();
            return;
        }
        if(!mheight.matches("[0-9]+")){
            Toast.makeText(MainActivity.this,"Enter Valid Height",Toast.LENGTH_LONG).show();
            return;
        }
        if(mweight.matches("")){
            Toast.makeText(MainActivity.this,"Enter Weight",Toast.LENGTH_LONG).show();
            return;
        }
        if(!mweight.matches("[0-9]+")){
            Toast.makeText(MainActivity.this,"Enter Valid Weight",Toast.LENGTH_LONG).show();
            return;
        }
        if(mmedhis.matches("")){
            Toast.makeText(MainActivity.this,"Enter Medical History",Toast.LENGTH_LONG).show();
            return;
        }


    }
}
