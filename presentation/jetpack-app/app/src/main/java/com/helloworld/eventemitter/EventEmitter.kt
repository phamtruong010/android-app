package com.helloworld.eventemitter


import android.util.Log
import java.util.*


val dicFunc = mutableMapOf<String, MutableList<Listener>>()
val dicMidd = mutableMapOf<String, (Any) -> Any>()

data class Listener(val id: String, val func: (Any) -> Unit)

fun getId(): String {
    return UUID.randomUUID().toString()
}

fun addListener(event: String, id: String? = null, cb: ((Any) -> Unit)?): String? {
    if (cb == null) return null
    if (!dicFunc.containsKey(event)) dicFunc[event] = mutableListOf()
    val idEvent = id ?: getId()
//    if (dicFunc[event]?.any { it.id == idEvent } == true) return null
    dicFunc[event]?.add(Listener(idEvent, cb))
    return idEvent
}

fun emit(event: String, data: Any) {
    if (!dicFunc.containsKey(event)) return
    var newData = data
    dicMidd[event]?.let { middleware ->
        newData = middleware(newData)
    }
    dicFunc[event]?.forEach { it.func(newData) }
}

fun deleteEvent(event: String) {
    dicFunc.remove(event)
    dicMidd.remove(event)
}

fun deleteEventById(event: String, id: String) {
    dicFunc[event]?.removeIf { it.id == id }
}

fun deleteListener(event: String, idEvent: String) {
    dicFunc[event]?.removeIf { it.id == idEvent }
}

fun getDicMid() {
    dicFunc

}