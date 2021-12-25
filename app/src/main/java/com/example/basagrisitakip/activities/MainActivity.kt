package com.example.basagrisitakip.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import com.example.basagrisitakip.R
import com.example.basagrisitakip.databinding.ActivityMainBinding
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.widget.TextView
import android.widget.Toast

import com.example.basagrisitakip.helpers.SQLiteHelperMethods
import java.lang.String
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {


    val PILL_REQUEST_CODE = 101
    val TRIGGERS_REQUEST_CODE = 102
    val SYMPTOMS_REQUEST_CODE = 103
    lateinit var binding: ActivityMainBinding
    var pillsResult: ArrayList<kotlin.String>? = null
    var triggersResult: ArrayList<kotlin.String>? = null
    var symptomsResult: ArrayList<kotlin.String>? = null
    var hour = 0
    var minute: Int = 0
    lateinit var sqLiteHelperMethods: SQLiteHelperMethods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sqLiteHelperMethods = SQLiteHelperMethods(this,"record.db",null,1,null)

        pillsResult = ArrayList<kotlin.String>()
        triggersResult = ArrayList<kotlin.String>()
        symptomsResult = ArrayList<kotlin.String>()

        binding.topAppBar.setNavigationOnClickListener {
            binding.mainDrawerLayout.openDrawer(Gravity.LEFT)
        }

        binding.startTime.setOnClickListener {
            popTimePicker(binding.startTime)
        }
        binding.endTime.setOnClickListener {
            popTimePicker(binding.endTime)
        }

        binding.pillsButton.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivityForResult(intent, PILL_REQUEST_CODE)
        }
        binding.triggersButton.setOnClickListener {
            val intent = Intent(this, TriggersActivity::class.java)
            startActivityForResult(intent, TRIGGERS_REQUEST_CODE)
        }
        binding.symptomsButton.setOnClickListener {
            val intent = Intent(this, SymptomsActivity::class.java)
            startActivityForResult(intent, SYMPTOMS_REQUEST_CODE)
        }

        binding.mainNavigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            if (menuItem.itemId == R.id.profile) {
                val intent = Intent(this, RecordsActivity::class.java)
                startActivity(intent)
                this.overridePendingTransition(0, 0);
                finish()
                binding.mainDrawerLayout.closeDrawer(Gravity.LEFT)
            } else {
                binding.mainDrawerLayout.closeDrawer(Gravity.LEFT)
            }
            //menuItem.isChecked = true

            true
        }

        binding.saveButton.setOnClickListener {
            sqLiteHelperMethods.addRecord(
                pillsResult!!,
                triggersResult!!,
                symptomsResult!!,
                binding.startTime.text as kotlin.String,
                binding.endTime.text as kotlin.String,binding.painSeekBar.progress)
            Toast.makeText(this,"başarılı",Toast.LENGTH_LONG).show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PILL_REQUEST_CODE && data != null) {
            if (resultCode == RESULT_OK) {
                pillsResult = data?.getStringArrayListExtra("list")
                if (pillsResult != null) {
                    binding.pillsButton.setDescriptionTv(resources.getString(R.string.selected))
                } else {
                    binding.pillsButton.setDescriptionTv("Lütfen kullandığınız ilaçları seçiniz.")
                }
            }
        } else if (requestCode == TRIGGERS_REQUEST_CODE && data != null) {
            if (resultCode == RESULT_OK) {
                triggersResult = data?.getStringArrayListExtra("list")
                if (triggersResult != null) {
                    binding.triggersButton.setDescriptionTv(resources.getString(R.string.selected))
                } else {
                    binding.triggersButton.setDescriptionTv("Lütfen tetikleyicileri seçiniz.")
                }
            }
        } else if (requestCode == SYMPTOMS_REQUEST_CODE && data != null) {
            if (resultCode == RESULT_OK) {
                symptomsResult = data?.getStringArrayListExtra("list")
                if (symptomsResult != null) {
                    binding.symptomsButton.setDescriptionTv(resources.getString(R.string.selected))
                } else {
                    binding.symptomsButton.setDescriptionTv("Lütfen semptomları seçiniz.")
                }
            }
        }
    }

    fun popTimePicker(view: TextView?) {
        val onTimeSetListener =
            OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                hour = selectedHour
                minute = selectedMinute
                view?.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute))
            }

        // int style = AlertDialog.THEME_HOLO_DARK;
        val timePickerDialog =
            TimePickerDialog(this,  /*style,*/onTimeSetListener, hour, minute, true)
        timePickerDialog.setTitle("Select Time")
        timePickerDialog.show()
    }

}