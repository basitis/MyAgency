package com.basitis.myagency.students;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basitis.myagency.Constant;
import com.basitis.myagency.R;
import com.basitis.myagency.company.CompanyRegister;
import com.basitis.myagency.database.RepositoryManager;
import com.basitis.myagency.databinding.ActivityStudentRegisterBinding;
import com.basitis.myagency.fragments.PlatformFragment;
import com.basitis.myagency.models.Platform;
import com.basitis.myagency.models.Student;
import com.google.common.collect.Range;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StudentRegister extends AppCompatActivity implements View.OnClickListener,PlatformFragment.OnListFragmentInteractionListener {

    ActivityStudentRegisterBinding binding;
    private AwesomeValidation awesomeValidation;
    private long regId;
    private Student student;
    private Date selectedBOD = null;
    private static final String TAG = "StudentRegister";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_student_register);
        setSupportActionBar(binding.toolbar);
        binding.fab.setVisibility(View.GONE);
        awesomeValidation = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        setValidation();
        regId = getIntent().getLongExtra(getResources().getString(R.string.con_reg_id), -1);
        student = new Student();
        setData();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.department));
        binding.contentStudent.spDepartment.setAdapter(adapter);
    }

    private void setData() {
        if (Constant.PROVIDE_DATA) {
            binding.contentStudent.etFirstName.getEditText().setText("Bhavin");
            setText(binding.contentStudent.etFirstName, "Bhvin");
            setText(binding.contentStudent.etMiddleName, "S");
            setText(binding.contentStudent.etLastName, "Doshi");
            binding.contentStudent.rgGender.check(R.id.rbMale);
            setText(binding.contentStudent.etEmail, "Bhavin.doshi1989@gmail.com");
            setText(binding.contentStudent.etContactNumber, "9687667944");
            setText(binding.contentStudent.etCityAddress, "Ahmedabad");
            student.dob = new Date();

            student.age = 27;
            student.department = "Other";
//            setText(binding.contentStudent.etPlatform, "Software Industries");
        }
    }

    private void setValidation() {
        awesomeValidation.addValidation(this, R.id.etFirstName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.etMiddleName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.etLastName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.etEmail, Patterns.EMAIL_ADDRESS, R.string.error_invalid_email);
        awesomeValidation.addValidation(this, R.id.etContactNumber, "^[2-9]{2}[0-9]{8}$", R.string.error_invalid_number);
        awesomeValidation.addValidation(this, R.id.etAge, Range.closed(13, 60), R.string.ageerror);
        binding.contentStudent.btnAdd.setOnClickListener(this);
        binding.contentStudent.llDob.setOnClickListener(this);
        binding.contentStudent.tvPlatform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlatformFragment platformFragment = new PlatformFragment();
                platformFragment.show(getSupportFragmentManager(),"Platform");
            }
        });
    }

    Calendar calendar = Calendar.getInstance();

    public String getText(TextInputLayout et) {
        return et.getEditText().getText().toString();
    }

    public void setText(TextInputLayout et, String value) {
        et.getEditText().setText(value);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAdd) {
            if (awesomeValidation.validate()) {
                Toast.makeText(this, "Validation Successful", Toast.LENGTH_LONG).show();
                /*Intent intent =new Intent(StudentRegister.this,CompanyRegister.class);
                startActivity(intent);*/
                student.first_name = getText(binding.contentStudent.etFirstName);
                student.middle_name = getText(binding.contentStudent.etMiddleName);
                student.last_name = getText(binding.contentStudent.etLastName);
                student.gender = binding.contentStudent.rgGender.getCheckedRadioButtonId() == R.id.rbMale ? 0 : 1;
                student.email = getText(binding.contentStudent.etEmail);
                student.contact = getText(binding.contentStudent.etContactNumber);
                student.address = getText(binding.contentStudent.etCityAddress);
                if (selectedBOD != null) {
                    student.dob = selectedBOD;
                }
                student.age = Integer.parseInt(!TextUtils.isEmpty(getText(binding.contentStudent.etAge)) ? getText(binding.contentStudent.etAge) : "-1");
                student.department = binding.contentStudent.spDepartment.getSelectedItem().toString();
                student.platform = binding.contentStudent.tvPlatform.getText().toString();
                student.average = Float.parseFloat(!TextUtils.isEmpty(getText(binding.contentStudent.etAggrigrate)) ? getText(binding.contentStudent.etAggrigrate) : "-1");
                student.RegId = regId;
                Log.d(TAG, "onClick: " + student.toString());
                long s_id = RepositoryManager.getInstance().addStudentInTable(student);
                Log.d(TAG, "onClick: we got new student regId" + s_id);
            } else {
                Toast.makeText(this, "Validation Unsuccessful", Toast.LENGTH_LONG).show();
            }
        } else if (view.getId() == R.id.llDob) {
            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear,
                                      int dayOfMonth) {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    binding.contentStudent.tvDob.setText(new StringBuilder()
                            // Month is 0 based so add 1
                            .append(dayOfMonth).append("/").append(monthOfYear + 1).append("/").append(year).append(" "));
                    selectedBOD = new Date(calendar.getTimeInMillis());
                }
            };
            DatePickerDialog dialog = new DatePickerDialog(this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        }
    }

    @Override
    public void onListFragmentInteraction(@NotNull Platform item) {

    }
}
