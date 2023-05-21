package com.example.wwatesttask.domain

import com.example.wwatesttask.R
import com.example.wwatesttask.domain.model.Images
import javax.inject.Inject

class ListOfImagesImpl @Inject constructor (): ListOfImages {

    private val listOfImages = listOf(
        Images(0, R.drawable.cherry),
        Images(1, R.drawable.red_p),
        Images(2, R.drawable.old_red_p),
        Images(3, R.drawable.blue_gray),
        Images(4, R.drawable.blue_p),
        Images(5, R.drawable.green_p),
        Images(6, R.drawable.pacman),
        Images(7, R.drawable.pink_p),
        Images(8, R.drawable.old_packman),
        Images(9, R.drawable.yellow_p),
        Images(0, R.drawable.cherry),
        Images(1, R.drawable.red_p),
        Images(2, R.drawable.old_red_p),
        Images(3, R.drawable.blue_gray),
        Images(4, R.drawable.blue_p),
        Images(5, R.drawable.green_p),
        Images(6, R.drawable.pacman),
        Images(7, R.drawable.pink_p),
        Images(8, R.drawable.old_packman),
        Images(9, R.drawable.yellow_p)
    )

    override fun getList(): List<Images> = listOfImages



}