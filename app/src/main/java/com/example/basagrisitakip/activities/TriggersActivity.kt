package com.example.basagrisitakip.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basagrisitakip.R
import com.example.basagrisitakip.adapters.PillsAdapter
import com.example.basagrisitakip.databinding.ActivityTriggersBinding
import com.example.basagrisitakip.helpers.CheckboxListListener
import com.example.basagrisitakip.models.PillsModel

class TriggersActivity : AppCompatActivity(), CheckboxListListener {

    lateinit var mBinding: ActivityTriggersBinding
    var listof = listOfPills()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTriggersBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)


        val recView = mBinding.triggersRecyclerview
        val mAdapter = PillsAdapter(this, listof, this)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL

        recView.apply {
            adapter = mAdapter
            layoutManager = llm
        }

        mBinding.triggersSaveButton.setOnClickListener {
            val data = Intent()
            data.putExtra("list", resultList(listof))
            setResult(RESULT_OK, data)
            finish()
        }

    }

    fun resultList(arrayList: ArrayList<PillsModel>): ArrayList<String> {
        var arraySize = arrayList.size
        val resultArray = ArrayList<String>()
        val i = 0
        for (i in 0 until arraySize - 1) {
            if (arrayList[i].isChecked) {
                resultArray.add(arrayList[i].name)
            }
        }
        return resultArray
    }

    fun listOfPills(): ArrayList<PillsModel> {
        var listOfPills = ArrayList<PillsModel>()

        listOfPills.add(PillsModel("Kahve", false));
        listOfPills.add(PillsModel("Yoğunluk", false));
        listOfPills.add(PillsModel("Stres", false));
        listOfPills.add(PillsModel("Alkol", false));
        listOfPills.add(PillsModel("Sigara", false));
        listOfPills.add(PillsModel("Sıcaklık", false));
        listOfPills.add(PillsModel("Atlanan Öğün", false));
        listOfPills.add(PillsModel("Çikolata", false));

        return listOfPills
    }

    override fun pillsCheckboxListener(position: Int, isChecked: Boolean) {
        listof[position].isChecked = isChecked
    }

}