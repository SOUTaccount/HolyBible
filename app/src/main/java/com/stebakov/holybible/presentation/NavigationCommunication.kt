package com.stebakov.holybible.presentation

import com.stebakov.holybible.core.Communication

interface NavigationCommunication : Communication<Int> {
    class Base : Communication.Base<Int>(), NavigationCommunication
}