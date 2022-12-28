package com.example.propaintcontainerscreen.data


/*data class PaintContainer(val name: String, val age: Int, val messages: List<String>) {
}*/

data class PaintContainer(
    val colors: List<Color>,
    val name: String
) {
    data class Color(
        val amount: Int,
        val sheen: List<Sheen>,
        val size: String
    ) {
        data class Sheen(
            val name: String
        )
    }
}
