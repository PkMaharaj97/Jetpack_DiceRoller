package com.example.diceroller.model

data class ImageContent(
    var ImageUrl:String,
    var Content:String
)

fun loadImagesContents():List<ImageContent>{
    return listOf<ImageContent>(
        ImageContent("https://cdn.pixabay.com/photo/2015/01/20/12/51/mobile-605422_1280.jpg","Mobile, Phone, Samsung image. Free for use"),
        ImageContent("https://cdn.pixabay.com/photo/2021/11/02/15/30/tealights-6763542_1280.jpg","free tealights photos"),
        ImageContent("https://cdn.pixabay.com/photo/2019/11/19/11/47/tealight-4637226_1280.jpg","Tealight, Glass, Candle image"),
        ImageContent("https://cdn.pixabay.com/photo/2015/05/31/11/20/candleholders-791154_1280.jpg","Candleholders, Tealight, Glass, Candle image"),
        ImageContent("https://cdn.pixabay.com/photo/2020/08/24/08/58/buddha-5513214_1280.jpg","Buddha, Tealight, Glass, Candle image")
    )
}