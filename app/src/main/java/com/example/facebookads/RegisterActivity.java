package com.example.facebookads;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity implements View.OnFocusChangeListener {
    EditText edtYear, edtMonth, edtDay, edtEmail, edtPhone, edtPassWord, edtConfirmPassword;
    TextView txtErrorBirthDay, txtErrorEmail, txtErrorPhoneNumber, txtErrorPassWord, txtErrorConfirmPassword,txtDone;
    RadioButton rdb1, rdb2;
    CheckBox checkBox;
    CountryCodePicker countryCodePicker1, countryCodePicker2;
    ImageView imgErrorYear, imgErrorMonth, imgErrorDay, imgErrorEmail, imgErrorPhone, imgErrorPass, imgErrorConfirmPass;
    LinearLayout lilYear, lilMonth, lilDay, lilEmail, lilPhone, lilPass, lilConfirmPass;
    Button btnCreate;
    ProgressBar progressBar;

    private int currentYear = 0;
    private boolean isYear = false,isMonth = false,isDay = false,isEmail = false,isPhone = false,isPass = false,isConfirmPass = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        setupBackground(-1);
        setupClick();
        setupData();
        eventTextChange();
    }

    private void initView() {
        // init editext
        edtYear = (EditText) findViewById(R.id.edt_year);
        edtMonth = (EditText) findViewById(R.id.edt_month);
        edtDay = (EditText) findViewById(R.id.edt_day);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPhone = (EditText) findViewById(R.id.edt_phone);
        edtPassWord = (EditText) findViewById(R.id.edt_password);
        edtConfirmPassword = (EditText) findViewById(R.id.edt_confirm_password);

        // init textview
        txtErrorBirthDay = (TextView) findViewById(R.id.txt_error_birth_day);
        txtErrorEmail = (TextView) findViewById(R.id.txt_error_email);
        txtErrorPhoneNumber = (TextView) findViewById(R.id.txt_error_phone);
        txtErrorPassWord = (TextView) findViewById(R.id.txt_error_password);
        txtErrorConfirmPassword = (TextView) findViewById(R.id.txt_error_confirm_password);
        txtDone = (TextView) findViewById(R.id.txt_done);

        // init radiobutton
        rdb1 = (RadioButton) findViewById(R.id.radio1);
        rdb2 = (RadioButton) findViewById(R.id.radio2);

        // init checkbox
        checkBox = (CheckBox) findViewById(R.id.checkbox);

        // init country
        countryCodePicker1 = (CountryCodePicker) findViewById(R.id.codepicker_first);
        countryCodePicker2 = (CountryCodePicker) findViewById(R.id.codepicker_last);

        // init imageError
        imgErrorYear = (ImageView) findViewById(R.id.img_error_year);
        imgErrorMonth = (ImageView) findViewById(R.id.img_error_month);
        imgErrorDay = (ImageView) findViewById(R.id.img_error_day);
        imgErrorEmail = (ImageView) findViewById(R.id.img_error_email);
        imgErrorPhone = (ImageView) findViewById(R.id.img_error_phone);
        imgErrorPass = (ImageView) findViewById(R.id.img_error_password);
        imgErrorConfirmPass = (ImageView) findViewById(R.id.img_error_confirmpass);

        // init linerlayout
        lilYear = (LinearLayout) findViewById(R.id.lil_year);
        lilMonth = (LinearLayout) findViewById(R.id.lil_month);
        lilDay = (LinearLayout) findViewById(R.id.lil_day);
        lilEmail = (LinearLayout) findViewById(R.id.lil_eamil);
        lilPhone = (LinearLayout) findViewById(R.id.lil_phone);
        lilPass = (LinearLayout) findViewById(R.id.lil_password);
        lilConfirmPass = (LinearLayout) findViewById(R.id.lil_confirm_password);

        // init button
        btnCreate = (Button) findViewById(R.id.btnCreate);

        // init progessbar
        progressBar = (ProgressBar) findViewById(R.id.progress_circular);
    }

    private void setupBackground(int index) {
        lilYear.setBackgroundResource(index == 0 ? R.drawable.border_select : R.drawable.border_textfield);
        lilMonth.setBackgroundResource(index == 1 ? R.drawable.border_select : R.drawable.border_textfield);
        lilDay.setBackgroundResource(index == 2 ? R.drawable.border_select : R.drawable.border_textfield);
        lilEmail.setBackgroundResource(index == 3 ? R.drawable.border_select : R.drawable.border_textfield);
        lilPhone.setBackgroundResource(index == 4 ? R.drawable.border_select : R.drawable.border_textfield);
        lilPass.setBackgroundResource(index == 5 ? R.drawable.border_select : R.drawable.border_textfield);
        lilConfirmPass.setBackgroundResource(index == 6 ? R.drawable.border_select : R.drawable.border_textfield);
    }

    private void setupClick() {
        edtYear.setOnFocusChangeListener(this);
        edtMonth.setOnFocusChangeListener(this);
        edtDay.setOnFocusChangeListener(this);
        edtEmail.setOnFocusChangeListener(this);
        edtPhone.setOnFocusChangeListener(this);
        edtPassWord.setOnFocusChangeListener(this);
        edtConfirmPassword.setOnFocusChangeListener(this);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                startActivity(new Intent(RegisterActivity.this,WebviewActivity.class));
                            }
                        },
                        3000);
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEnableButton();
            }
        });
    }

    private void setupData(){
        currentYear = Calendar.getInstance().get(Calendar.YEAR);
    }

    private void eventTextChange(){
        edtYear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkBirthDay();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtMonth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkBirthDay();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkBirthDay();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isValidEmail(charSequence)){
                    imgErrorEmail.setVisibility(View.GONE);
                    txtErrorEmail.setVisibility(View.GONE);
                    isEmail = true;
                }else{
                    isEmail = false;
                    imgErrorEmail.setVisibility(View.VISIBLE);
                    txtErrorEmail.setVisibility(View.VISIBLE);
                }
                checkEnableButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() < 5 || charSequence.toString().length() > 13){
                    imgErrorPhone.setVisibility(View.VISIBLE);
                    txtErrorPhoneNumber.setVisibility(View.VISIBLE);
                    isPhone = false;
                }else{
                    isPhone = true;
                    imgErrorPhone.setVisibility(View.GONE);
                    txtErrorPhoneNumber.setVisibility(View.GONE);
                }
                checkEnableButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() < 6){
                    imgErrorPass.setVisibility(View.VISIBLE);
                    txtErrorPassWord.setVisibility(View.VISIBLE);
                    isPass = false;
                }else{
                    isPass = true;
                    imgErrorPass.setVisibility(View.GONE);
                    txtErrorPassWord.setVisibility(View.GONE);
                }
                checkEnableButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().equals(edtPassWord.getText().toString().trim())){
                    imgErrorConfirmPass.setVisibility(View.VISIBLE);
                    txtErrorConfirmPassword.setVisibility(View.VISIBLE);
                    isConfirmPass = false;
                }else{
                    isConfirmPass = true;
                    imgErrorConfirmPass.setVisibility(View.GONE);
                    txtErrorConfirmPassword.setVisibility(View.GONE);
                }
                checkEnableButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void checkBirthDay(){
        int year = Integer.parseInt(edtYear.getText().toString().trim().isEmpty() ? "0" : edtYear.getText().toString().trim());
        int month = Integer.parseInt(edtMonth.getText().toString().trim().isEmpty() ? "0" : edtMonth.getText().toString());
        int day = Integer.parseInt(edtDay.getText().toString().trim().isEmpty() ? "0" : edtDay.getText().toString());
        if (edtYear.getText().toString().trim().length() == 4 && year > currentYear && !edtYear.getText().toString().trim().isEmpty()){
            imgErrorYear.setVisibility(View.VISIBLE);
            isYear = false;
        }else{
            imgErrorYear.setVisibility(View.GONE);
            isYear = true;
        }
        if (month > 12 && !edtMonth.getText().toString().trim().isEmpty()){
            imgErrorMonth.setVisibility(View.VISIBLE);
            isMonth = false;
        }else{
            isMonth = true;
            imgErrorMonth.setVisibility(View.GONE);
        }
        if (day > 31 && !edtDay.getText().toString().trim().isEmpty()){
            isDay = false;
            imgErrorDay.setVisibility(View.VISIBLE);
        }else{
            isDay = true;
            imgErrorDay.setVisibility(View.GONE);
        }

        if (year <= currentYear && month <= 12 && day <= 31){
            txtErrorBirthDay.setVisibility(View.GONE);
        }else{
            if (edtYear.getText().toString().trim().isEmpty() && edtMonth.getText().toString().trim().isEmpty() && edtDay.getText().toString().trim().isEmpty()){
                txtErrorBirthDay.setVisibility(View.GONE);
            }else{
                txtErrorBirthDay.setVisibility(View.VISIBLE);
            }
        }
        checkEnableButton();
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void hideKeyboard(View view){
        if(!(view instanceof EditText)){
            InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
    }

    private void checkEnableButton(){
      if (isYear && isMonth && isDay && isEmail && isPhone && isPass && isConfirmPass && checkBox.isChecked()){
          btnCreate.setEnabled(true);
          btnCreate.setBackgroundResource(R.drawable.bg_create_account_true);
          txtDone.setVisibility(View.GONE);
      }else{
          txtDone.setVisibility(View.VISIBLE);
          btnCreate.setEnabled(false);
          btnCreate.setBackgroundResource(R.drawable.bg_create_account);
      }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()) {
            case R.id.edt_year:
                setupBackground(0);
                break;
            case R.id.edt_month:
                setupBackground(1);
                break;
            case R.id.edt_day:
                setupBackground(2);
                break;
            case R.id.edt_email:
                setupBackground(3);
                break;
            case R.id.edt_phone:
                setupBackground(4);
                break;
            case R.id.edt_password:
                setupBackground(5);
                break;
            case R.id.edt_confirm_password:
                setupBackground(6);
                break;
        }
    }
}