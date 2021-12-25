package com.example.basagrisitakip.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basagrisitakip.R
import com.example.basagrisitakip.adapters.RecordsAdapter
import com.example.basagrisitakip.databinding.ActivityRecordsBinding
import com.example.basagrisitakip.models.RecordModel

class RecordsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecordsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.topAppBar.setNavigationOnClickListener {
            binding.recordsActivityDrawer.openDrawer(Gravity.LEFT)
        }

        binding.recordsNavigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            if(menuItem.itemId == R.id.home){
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                this.overridePendingTransition(0, 0);
                finish()
                binding.recordsActivityDrawer.closeDrawer(Gravity.LEFT)
            } else{
                binding.recordsActivityDrawer.closeDrawer(Gravity.LEFT)
            }
            //menuItem.isChecked = true

            true
        }

        val recyclerview = binding.recordsRecyclerview
        val madapter = RecordsAdapter(this,list())
        val llm = LinearLayoutManager(this)
        recyclerview.apply {
            adapter = madapter
            layoutManager = llm
        }

    }

    fun list() : ArrayList<RecordModel>{
        val listOfRecords = ArrayList<RecordModel>()

        listOfRecords.add(RecordModel("11.12.2021 13.50","05.12.2021 12.45",6))
        listOfRecords.add(RecordModel("11.08.2021 13.50","07.12.2021 17.45",3))
        listOfRecords.add(RecordModel("11.10.2021 13.50","19.12.2021 19.45",1))
        listOfRecords.add(RecordModel("11.12.2021 13.50","05.12.2021 12.45",6))

        return listOfRecords
    }

}