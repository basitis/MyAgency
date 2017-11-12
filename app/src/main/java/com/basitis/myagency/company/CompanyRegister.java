package com.basitis.myagency.company;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basitis.myagency.Constant;
import com.basitis.myagency.R;
import com.basitis.myagency.database.RepositoryManager;
import com.basitis.myagency.databinding.ActivityCompanyRegisterBinding;
import com.basitis.myagency.models.Company;
import com.google.common.collect.Range;

import org.jetbrains.anko.internals.AnkoInternals;

import kotlin.Pair;

public class CompanyRegister extends AppCompatActivity implements View.OnClickListener {

    ActivityCompanyRegisterBinding binding;
    private AwesomeValidation awesomeValidation;
    private long regId;
    private static final String TAG = "CompanyRegister";
    Company company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_company_register);
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setVisibility(View.GONE);
        awesomeValidation = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        setValidation();
        setData();
        company = new Company();
        regId = getIntent().getLongExtra(getResources().getString(R.string.con_reg_id),-1);
    }

    private void setData() {
        if (Constant.PROVIDE_DATA) {
            setText(binding.contentCompany.etCompanyName, "ABC PVT. LTD.");
            setText(binding.contentCompany.etCompanyHrName, "Ms. Malini");
            setText(binding.contentCompany.etCompanyEmail, "malini@abc.com");
            setText(binding.contentCompany.etCompanyContactNumber, "9696969696");
        }
    }

    public void setText(TextInputLayout et, String value) {
        et.getEditText().setText(value);
    }

    public String getText(TextInputLayout et) {
        return et.getEditText().getText().toString();
    }

    private void setValidation() {
        awesomeValidation.addValidation(this, R.id.etCompanyName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.etCompanyEmail, Patterns.EMAIL_ADDRESS, R.string.error_invalid_email);
        awesomeValidation.addValidation(this, R.id.etCompanyHrName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.etContactNumber, "^[2-9]{2}[0-9]{8}$", R.string.error_invalid_number);
        binding.contentCompany.btnAddCompany.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAddCompany) {
            if (awesomeValidation.validate()) {
                Toast.makeText(this, "Validation Successful", Toast.LENGTH_LONG).show();
//                Intent intent =new Intent(CompanyRegister.this,PostJob.class);
//                startActivity(intent);
                company.name = getText(binding.contentCompany.etCompanyName);
                company.email = getText(binding.contentCompany.etCompanyEmail);
                company.contact = getText(binding.contentCompany.etCompanyContactNumber);
                company.hrName = getText(binding.contentCompany.etCompanyHrName);
                company.RegId = regId;
                long id = RepositoryManager.getInstance().addCompanyName(company);
                Intent intent = new Intent(CompanyRegister.this, CompanyMainPage.class);
                intent.putExtra(getResources().getString(R.string.con_reg_id), id);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Validation Unsuccessful", Toast.LENGTH_LONG).show();

            }
        }
    }
}
