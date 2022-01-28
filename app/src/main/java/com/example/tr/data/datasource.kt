package com.example.tr.data

import com.example.tr.R
import com.example.tr.model.itemModel

class datasource {
    fun loadItem_1(): List<itemModel> {
        return listOf(
            itemModel(R.drawable.ic_baseline_people_24),
            itemModel(R.drawable.ic_baseline_people_24)
        )
    }
    fun loadItem_2(): List<itemModel> {
        return listOf(
            itemModel(R.drawable.ic_baseline_people_24),
            itemModel(R.drawable.ic_baseline_people_24),
            itemModel(R.drawable.ic_baseline_people_24)
        )
    }
}