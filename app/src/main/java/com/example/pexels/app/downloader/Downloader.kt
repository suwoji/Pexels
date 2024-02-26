package com.example.pexels.app.downloader

interface Downloader {
    fun downloadFile(url: String, fileName: String): Long
}