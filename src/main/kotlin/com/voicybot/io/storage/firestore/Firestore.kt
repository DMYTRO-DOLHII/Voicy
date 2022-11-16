package com.voicybot.io.storage.firestore

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseOptions
import java.io.FileInputStream

class Firestore {
    private val refreshToken = FileInputStream("voicy-3e640-firebase-adminsdk-ulr28-fd0af7d6ef.json")

    private val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(refreshToken))
        .build();


}