package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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

    private String[] protocolCategory;
    private String selectedProtocol;

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
        protocolCategory = getResources().getStringArray(R.array.protocol_type);
        ArrayAdapter<String> protocolAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, protocolCategory);
        protocolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        protocolSpinnerText.setAdapter(protocolAdapter);

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


        // IP Address on Edit
        ipAddress.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || event != null && event.getAction() == KeyEvent.ACTION_DOWN || event.getAction() == KeyEvent.KEYCODE_ENTER){
                    if(event == null || !event.isShiftPressed()){
                        setUpData("ipaddress", ipAddress.getText().toString());
                        return true;
                    }
                }

                return false;
            }
        });


        // Port Number On Edit
        portNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || event != null && event.getAction() == KeyEvent.ACTION_DOWN || event.getAction() == KeyEvent.KEYCODE_ENTER){
                    if(event == null || !event.isShiftPressed()){
                        setUpData("portnumber", portNumber.getText().toString());
                        return true;
                    }
                }

                return false;
            }
        });

    }

    public void setUpData(String fieldType, String data){
        if(fieldType == "ipaddress"){
            ipAddressText.setText(data);
            portNumber.setFocusable(true);
        }else if(fieldType == "portnumber"){
            portNumberText.setText(data);
            protocolSpinnerText.setFocusable(true);
        }else if(fieldType == "protocol"){
            protocolFieldText.setText(data);
        }
    }

}