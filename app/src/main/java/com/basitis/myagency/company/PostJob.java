package com.basitis.myagency.company;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basitis.myagency.R;
import com.basitis.myagency.database.RepositoryManager;
import com.basitis.myagency.databinding.ActivityPostJobBinding;
import com.basitis.myagency.models.Job;
import com.google.common.collect.Range;

public class PostJob extends AppCompatActivity implements View.OnClickListener {

    ActivityPostJobBinding binding;
    private AwesomeValidation awesomeValidation;
    long regid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_job);
        setSupportActionBar(binding.toolbar);
        binding.fab.setVisibility(View.GONE);
        awesomeValidation = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        setValidation();
        regid = getIntent().getLongExtra(getResources().getString(R.string.con_reg_id),-1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.bond));
        binding.contentPostJob.spBond.setAdapter(adapter);
    }

    private void setValidation() {
        awesomeValidation.addValidation(this, R.id.etJobProfile, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.etJobEmail, Patterns.EMAIL_ADDRESS, R.string.error_invalid_email);
        awesomeValidation.addValidation(this, R.id.etJobCriteria, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
//        awesomeValidation.addValidation(this, R.id.etJobBond, "^[0-9]{1}$", R.string.invalid_bond_period);
//        awesomeValidation.addValidation(this, R.id.etD, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.nameerror);
        binding.contentPostJob.btnAddJob.setOnClickListener(this);
    }

    public String getText(TextInputLayout et) {
        return et.getEditText().getText().toString();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAddJob) {
            if (awesomeValidation.validate()) {
                Toast.makeText(this, "Validation Successful", Toast.LENGTH_LONG).show();
                Job job = new Job();
                job.email = getText(binding.contentPostJob.etJobEmail);
                job.criteria = getText(binding.contentPostJob.etJobCriteria);
                job.salary = Float.parseFloat(!TextUtils.isEmpty(getText(binding.contentPostJob.etJobSalary))?getText(binding.contentPostJob.etJobSalary):"0");
                job.bond = Float.parseFloat(binding.contentPostJob.spBond.getSelectedItem().toString());
                job.RegId = regid;
                long jobId = RepositoryManager.getInstance().insertNewJob(job);
                if(jobId > 0){
                    Toast.makeText(this, "New job added successfully", Toast.LENGTH_SHORT).show();
                }
                finish();
            }else{
                Toast.makeText(this, "Please fill required field", Toast.LENGTH_SHORT).show();            
            }
        }
    }
}
