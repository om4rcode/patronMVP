package com.omarcode.patronmvp.model.pojos

import com.google.gson.annotations.SerializedName

data class Topartists(

	@field:SerializedName("@attr")
	val attr: Attr? = null,

	@field:SerializedName("artist")
	val artist: List<ArtistItem?>? = null
)