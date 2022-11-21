package ru.madmax.testcaseeffectivemobile.featureDetails.domain.model

data class Details(
    val CPU: String = "",
    val camera: String = "",
    val capacity: List<String> = emptyList(),
    val color: List<String> = emptyList(),
    val id: String = "",
    val images: List<String> = emptyList(),
    val isFavorites: Boolean = false,
    val price: Int = 0,
    val rating: Double = 0.0,
    val sd: String = "",
    val ssd: String = "",
    val title: String = ""
)