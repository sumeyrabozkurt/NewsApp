package com.example.haberaksuygulamas

import java.io.Serializable

class NewsInfo(var title: String,
               var author: String,
               var description: String,
               var url: String,
               var imageUrl: String,
               var publishedAt: String,
               var content: String) : Serializable { }