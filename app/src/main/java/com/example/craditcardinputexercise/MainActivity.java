package com.example.craditcardinputexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText card_number,cvv,exp_date,first_name,last_name;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        card_number=findViewById(R.id.cardumber);
        cvv=findViewById(R.id.cvv);
        exp_date=findViewById(R.id.expdate);
        first_name=findViewById(R.id.firstname);
        last_name=findViewById(R.id.lastname);
        submit=findViewById(R.id.button2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number=card_number.getText().toString();
                String Cvv=cvv.getText().toString();
                String date=exp_date.getText().toString();
                String first=first_name.getText().toString();
                String last=last_name.getText().toString();

                if(valid_card_number(number,card_number)&&valid_cvv(Cvv,cvv)&&valid_first_name(first,first_name)&&valid_last_name(last,last_name)){
                    Toast.makeText(MainActivity.this,"PAYMENT WAS SUCCESSFULL",Toast.LENGTH_SHORT).show();
                } }
        });
    }

    private boolean valid_last_name(String last, EditText last_name) {
        if(last.isEmpty())
            last_name.setError("THIS FIELD SHOULD NOT BE EMPTY");
        else if(valid_name(last))
            last_name.setError("Invalid Last Name");
        else{
            last_name.setError(null);
            return true;
        }
        return false;
    }

    private boolean valid_first_name(String first, EditText first_name) {
        if(first.isEmpty())
            last_name.setError("THIS FIELD SHOULD NOT BE EMPTY");
        else if(valid_name(first))
            last_name.setError("Invalid Last Name");
        else{
            last_name.setError(null);
            return true;
        }
        return false;
    }

    private boolean valid_card_number(String number, EditText card_number) {
        if(number.isEmpty())
            card_number.setError("THIS FIELD SHOULD NOT BE EMPTY");
        else if(!number_format(number))
            card_number.setError("Invalid Card number");
        else{
            card_number.setError(null);
        return true ;}
        return false;
    }

    private boolean valid_cvv(String cvv, EditText cvv1) {
        if(cvv.isEmpty())
            cvv1.setError("This field should not be empty");
        else if(cvv.length()!=3)
            cvv1.setError("Invalid Security number");
        else{
            cvv1.setError(null);
        return true;}
        return false;
    }

    boolean number_format(String number){
        if(number.isEmpty()){
            return  false;
        }
        //Visa Card format begins with 4 and have 13 or 16 digis
        else if( (number.length()==13||number.length()==16)&&number.charAt(0)=='4'){
            return true;
        }
        //Master card format begins with 5 and have 16 digits.
        else if(number.length()==16&&number.charAt(0)=='5'){
            return  true;
        }
        //American Express Card format begins with 3 followed by a 4 or 7 has 15 digits
        else if(number.length()==15&&(number.charAt(0)=='3'&&(number.charAt(1)=='4')||number.charAt(1)=='7')){
            return true;
        }
        else if(number.charAt(0)=='6'&&number.length()==16){
            return true;
        }
        return false;
    }
    boolean luhm_validation(String number){
        int sum=0;
        int length=number.length();boolean second=false;
        for(int i=length-2;i<=0;i--){
            int a=number.charAt(i);
            if(second==true){
                a=a*2;
            }
            sum+=a/10;
            sum+=a%10;
            second=!second;
        }
        return (sum%10==0);
    }
    boolean valid_name(String name){
        return ((name!=null)&&(name.matches("^[a-zA-Z]*$")));
    }

}