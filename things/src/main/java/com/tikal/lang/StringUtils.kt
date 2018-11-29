package com.tikal.lang

operator fun StringBuilder.plus(s: CharSequence): StringBuilder {
    return StringBuilder(this).append(s)
}

operator fun StringBuilder.plusAssign(obj: Any?) {
    append(obj)
}

operator fun String.compareTo(s: CharSequence): Int {
    return this.compareTo(s.toString())
}
