package com.burak.myheroes.util

import java.lang.StringBuilder
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


/**
 * Created by mburak on 5.09.2021.
 */
object AuthenticationUtil {
    const val MARVEL_PUBLIC_KEY = "deec2631188bf94df88017d047a0eccd"
    private const val MARVEL_PRIVATE_KEY = "808022e6d8e42f2888641e6eba8036f10777a337"
    private const val MD5 = "MD5"

    fun getMd5Digest(timestamp: Long): String {
        val toHash = "$timestamp$MARVEL_PRIVATE_KEY$MARVEL_PUBLIC_KEY"

        return md5(toHash)
    }

    private fun md5(s: String): String {
        try {
            // Create MD5 Hash
            val digest = MessageDigest
                .getInstance(MD5)
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2) h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }
}