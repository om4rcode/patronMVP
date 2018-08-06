package com.omarcode.patronmvp.model.pojos

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("topartists")
	val topartists: Topartists? = null
)