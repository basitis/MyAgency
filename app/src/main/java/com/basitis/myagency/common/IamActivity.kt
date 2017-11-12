package com.basitis.myagency.common

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.basitis.myagency.LoginActivity
import com.basitis.myagency.R
import com.basitis.myagency.databinding.ActivityIamBinding

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class IamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityIamBinding = DataBindingUtil.setContentView(this,R.layout.activity_iam)
        binding.btnStudent.setOnClickListener { _ ->
            val intent = Intent(this,LoginActivity::class.java)
            intent.putExtra("AS","S")
            startActivity(intent)
         }
        binding.btnRecruit.setOnClickListener { _ ->
            val intent = Intent(this,LoginActivity::class.java)
            intent.putExtra("AS","R")
            startActivity(intent)
         }
    }
}
