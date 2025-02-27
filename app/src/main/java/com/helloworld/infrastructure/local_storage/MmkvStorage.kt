package com.helloworld.infrastructure.local_storage


import android.content.Context
import com.tencent.mmkv.MMKV

val MMKV_STORAGE = MmkvStorage.shared

class MmkvStorage {
    private var instance: MMKV? = null

    // ===== Private ===== \\
    private fun getStringWithKey(key: String): String? {
        return instance?.getString(key, null)
    }

    private fun setStringWithKey(key: String, value: String?) {
        value.let {
            instance?.putString(key, value)
        } ?: {
            instance?.remove(key)
        }
    }

    // ===== Public variable ===== \\
    var appFont: String?
        get() {
            return getStringWithKey("appFont")
        }
        set(value) {
            setStringWithKey("appFont", value)
        }

    var appLanguage: String?
        get() {
            return getStringWithKey("appLanguage")
        }
        set(value) {
            setStringWithKey("appLanguage", value)
        }
    var appToken: String?
        get() {
            return getStringWithKey("appToken")
        }
        set(value) {
            setStringWithKey("appToken", value)
        }

    var appTheme: String?
        get() {
            return getStringWithKey("appTheme")
        }
        set(value) {
            setStringWithKey("appTheme", value)
        }

    // ===== Public fun ===== \\
    fun createInstance(context: Context, cryptKey: String = "HelloWorld") {
        val root = MMKV.initialize(context)
        instance =
            MMKV.mmkvWithID("LocalStorage", MMKV.SINGLE_PROCESS_MODE, cryptKey, root + "/mmkv")
    }

    fun resetAll() {
        instance?.clearAll()
    }

    companion object {
        val shared: MmkvStorage = MmkvStorage()
    }
}