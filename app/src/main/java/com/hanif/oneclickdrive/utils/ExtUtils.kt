package com.hanif.oneclickdrive.utils

fun String.stringToSet(): Set<Int> {
    return this.split(",").map { it.trim().toInt() }.toSet()
}
