package com.example.technicaltest.home.view

sealed interface ElementType {
    data object First: ElementType
    data object Last: ElementType
    data object Only: ElementType
    data object Middle: ElementType
}