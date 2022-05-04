package com.stebakov.holybible.core


interface Matcher<T> {

    fun matches(arg: T): Boolean
}