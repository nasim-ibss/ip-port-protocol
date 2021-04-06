package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Array;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText ipAddress;
    private TextInputEditText portNumber;
    private TextView ipAddressText;
    private TextView portNumberText;
    private Spinner protocolSpinnerText;
    private TextView protocolFieldText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipAddress = findViewById(R.id.ip_address);
        ipAddressText = findViewById(R.id.ip_address_text);

        portNumber = findViewById(R.id.port);
        portNumberText = findViewById(R.id.port_text);

        protocolFieldText = findViewById(R.id.protocol_field_text);
        protocolSpinnerText = findViewById(R.id.protocol_text);


        // Set Protocol Data on Dropdown
        String[] protocolCategory = getResources().getStringArray(R.array.protocol_type);
        ArrayAdapter<String> protocolAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, protocolCategory);
        protocolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        protocolSpinnerText.setAdapter(protocolAdapter);

        // On Item Selected Listener for Protocol
        protocolSpinnerText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setUpData("protocol", parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                protocolFieldText.setText("");
            }
        });
        protocolSpinnerText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });


        // IP Address on Edit
        ipAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && !ipAddress.getText().toString().equals(""))
                    setUpData("ip_address", ipAddress.getText().toString());
            }
        });
        ipAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setUpData("ip_address", s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ipAddress.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    portNumber.requestFocus();
                    return true;
                }

                return false;
            }
        });


        // Port Number On Edit
        portNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !portNumber.getText().toString().equals(""))
                    setUpData("port_number", portNumber.getText().toString());
            }
        });
        portNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setUpData("port_number", s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        portNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    protocolSpinnerText.requestFocus();
                    return true;
                }

                return false;
            }
        });
    }

    // Setup Data for TextView
    public void setUpData(String fieldType, String data){
        switch (fieldType) {
            case "ip_address":
                ipAddressText.setText(data);
                break;
            case "port_number":
                portNumberText.setText(data);
                break;
            case "protocol":
                protocolFieldText.setText(data);
                break;
        }
    }

}