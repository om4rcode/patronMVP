package com.omarcode.patronmvp.model.pojos

import com.google.gson.annotations.SerializedName

data class Attr(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("total")
	val total: String? = null,

	@field:SerializedName("perPage")
	val perPage: String? = null,

	@field:SerializedName("totalPages")
	val totalPages: String? = null,

	@field:SerializedName("page")
	val page: String? = null
)