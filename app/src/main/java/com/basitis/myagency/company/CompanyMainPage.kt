
package com.basitis.myagency.company

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.basitis.myagency.R
import com.basitis.myagency.databinding.ActivityCompanyMainPageBinding

class CompanyMainPage : AppCompatActivity() {

    var regId: Long = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       var binding: ActivityCompanyMainPageBinding = DataBindingUtil.setContentView(this,R.layout.activity_company_main_page)

        regId = intent.extras.getLong(resources.getString(R.string.con_reg_id))
        binding.btnAddJob.setOnClickListener {
            var intent = Intent(this,PostJob::class.java)
            intent.putExtra(resources.getString(R.string.con_reg_id),regId)
            startActivity(intent)
        }
        binding.btnAddPlatform.setOnClickListener {
            var intent = Intent(this,PostJob::class.java)
            intent.putExtra(resources.getString(R.string.con_reg_id),regId)
            startActivity(intent)
        }
    }
}
