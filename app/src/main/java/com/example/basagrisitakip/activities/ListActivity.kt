package com.example.basagrisitakip.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.util.rangeTo
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basagrisitakip.R
import com.example.basagrisitakip.adapters.PillsAdapter
import com.example.basagrisitakip.databinding.ActivityListBinding
import com.example.basagrisitakip.helpers.CheckboxListListener
import com.example.basagrisitakip.models.PillsModel

class ListActivity : AppCompatActivity(), CheckboxListListener {

    lateinit var mBinding: ActivityListBinding
    var listof = listOfPills()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityListBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)

        val recyclerView = view.findViewById<RecyclerView>(R.id.list_activity_recyclerview)
        val mAdapter = PillsAdapter(this, listof, this)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL


        recyclerView.apply {
            adapter = mAdapter
            layoutManager = llm
        }

        mBinding.pillsSaveButton.setOnClickListener {
            val data = Intent()
            data.putExtra("list", resultList(listof))
            setResult(RESULT_OK, data)
            finish()
        }

    }

    override fun pillsCheckboxListener(position: Int, isChecked: Boolean) {
        listof[position].isChecked = isChecked
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

        listOfPills.add(PillsModel("Paracetomal", false));
        listOfPills.add(PillsModel("Acetylsalicylic acid", false));
        listOfPills.add(PillsModel("Ibuprofen", false));
        listOfPills.add(PillsModel("Asperin", false));
        listOfPills.add(PillsModel("Formigran", false));
        listOfPills.add(PillsModel("Majezik", false));
        listOfPills.add(PillsModel("Naproksen", false));
        listOfPills.add(PillsModel("Ketoprofen", false));

        return listOfPills
    }


}