package com.example.fininfocomproject

import android.R.attr.name
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {

    lateinit var loginButton: Button
    lateinit var registerButton:Button
    lateinit var loginIdText:EditText
    lateinit var loginPassText:EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val b = findViewById<Button>(R.id.button)
//        b.setOnClickListener{
//            val i = Intent(this, AppView::class.java)
//            startActivity(i)
//        }
        loginButton = findViewById<Button>(R.id.login)
        registerButton = findViewById(R.id.register)
        loginIdText = findViewById(R.id.userId)
        loginPassText = findViewById(R.id.pass)

        var loginID = loginIdText.text
        var loginPass = loginPassText.text
        Log.i("Neeraj ID" , loginID.toString())
        Log.i("Neeraj pass" , loginPass.toString())

        loginButton.setOnClickListener{
            Log.i("Neeraj ID" , loginIdText.getText().toString())
            Log.i("Neeraj pass" , loginPassText.getText().toString())
            if(verifyValidInput(loginIdText.getText().toString().trim() , loginPassText.getText().toString().trim()))
                loginAuthorization( loginIdText.getText().toString().trim() ,  loginPassText.getText().toString().trim())


        }

        registerButton.setOnClickListener{
            if(verifyValidInput(loginIdText.getText().toString().trim() , loginPassText.getText().toString().trim()))
                registerNewUser( loginID.toString().trim() , loginPass.toString().trim())

        }
    }

    override fun onStop() {

        loginIdText.setText("")
        loginPassText.setText("")
        super.onStop()
    }

    fun loginAuthorization(id:String ,pass:String):Boolean{
        if(id=="Fininfocom" && pass == "Fin@123"){
            startApp()
            loginIdText.setText("")
            loginPassText.setText("")
            return true
        }

        val sh = getSharedPreferences("Credentials", MODE_PRIVATE)
        val s1 = sh.getString(id, "")
        if(s1 == "" || s1 != pass ){
            Toast.makeText(this,"No such ID exists!!", Toast.LENGTH_LONG).show()
            loginIdText.setText("")
            loginPassText.setText("")
            return false
        }else  {
            startApp()
        }
        loginIdText.setText("")
        loginPassText.setText("")
        return true
    }

    fun  registerNewUser(id:String , pass:String):Boolean{
        if(id=="Fininfocom" && pass == "Fin@123"){
            Toast.makeText(this,"Already Registered. Please Login!!",Toast.LENGTH_LONG).show()
            loginIdText.setText("")
            loginPassText.setText("")
            return false
        }
        val sharedPreferences = getSharedPreferences("Credentials", MODE_PRIVATE)

        val s1 = sharedPreferences.getString(id, "")
        if(s1 != ""){
            Toast.makeText(this,"Already Registered. Please Login!!",Toast.LENGTH_LONG).show()
            loginIdText.setText("")
            loginPassText.setText("")
            return false
        }
        val myEdit = sharedPreferences.edit()

        myEdit.putString(id, pass)
        myEdit.commit()
        Toast.makeText(this,"Registered Successfully!!",Toast.LENGTH_LONG).show()

        startApp()
        loginIdText.setText("")
        loginPassText.setText("")
        return true;

    }
    fun verifyValidInput  (id:String ,pass:String): Boolean  {
            if(id.length<10 ){
                loginIdText.setText("")
                loginPassText.setText("")
                Toast.makeText(this, "Please Enter Minimum 10 character!!",Toast.LENGTH_LONG).show()
                return false
            }

        val passwordREGEX = Pattern.compile("^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                //"(?=\\S+$)" +           //no white spaces
                ".{6,}" +               //at least 7 characters
                "$")
        if(!passwordREGEX.matcher(pass).matches() || pass.length!=7) {


           Toast.makeText(this,"Password must be 7 Characters with" +
                   " 1UpperCase Alphabet and 1SpecialCharacter and Numeric!!",Toast.LENGTH_LONG).show()

            loginIdText.setText("")
            loginPassText.setText("")
            return false

        }


        return true
    }

    fun startApp(){
        clearCredentialInputCache()
//        loginPassText.text=""
//        loginPassText.text = ""
        val intent = Intent(this , AppView::class.java)
        startActivity(intent)
    }
    fun clearCredentialInputCache(){

    }
}