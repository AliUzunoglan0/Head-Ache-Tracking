package com.example.basagrisitakip.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basagrisitakip.R
import com.example.basagrisitakip.adapters.PillsAdapter
import com.example.basagrisitakip.databinding.ActivitySymptomsBinding
import com.example.basagrisitakip.helpers.CheckboxListListener
import com.example.basagrisitakip.models.PillsModel

class SymptomsActivity : AppCompatActivity(),CheckboxListListener {

    lateinit var binding: ActivitySymptomsBinding
    var listof = listOfPills()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySymptomsBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)

        val recyclerView = view.findViewById<RecyclerView>(R.id.symptoms_activity_recyclerview)
        val mAdapter = PillsAdapter(this, listof, this)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL

        recyclerView.apply {
            adapter = mAdapter
            layoutManager = llm
        }

        binding.symptomsSaveButton.setOnClickListener {
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

        listOfPills.add(PillsModel("Mide bulantısı var mı?", false));
        listOfPills.add(PillsModel("Işığa duyarlılık var mı?", false));
        listOfPills.add(PillsModel("Zonklayan ağrı var mı?", false));
        listOfPills.add(PillsModel("Tek taraflı ağrı mı?", false));
        listOfPills.add(PillsModel("Gürültüye duyarlılık var mı?", false));

        return listOfPills
    }

    override fun pillsCheckboxListener(position: Int, isChecked: Boolean) {
        listof[position].isChecked = isChecked
    }
}