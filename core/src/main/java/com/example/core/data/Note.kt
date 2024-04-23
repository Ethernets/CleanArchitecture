package com.example.core.data

data class Note (
    var id: Long = 0,
    var title: String,
    var creationTime: Long,
    var updateTime: Long,
    var content: String
)