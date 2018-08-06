package com.omarcode.patronmvp.model.pojos

import com.google.gson.annotations.SerializedName

data class ImageItem(

	@field:SerializedName("#text")
	val text: String? = null,

	@field:SerializedName("size")
	val size: String? = null
)