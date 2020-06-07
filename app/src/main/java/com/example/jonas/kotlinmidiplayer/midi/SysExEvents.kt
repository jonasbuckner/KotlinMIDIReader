package com.example.jonas.kotlinmidiplayer.midi

enum class SysExEvents(val status: Int) {
    // SysEx Events (Currently Unsupported)
    NORMAL_SYSEX_EVENT        (0xF0),
    DIVIDED_SYSEX_EVENT       (0xF7),
    AUTHORIZATION_SYSEX_EVENT (0xF0),

    SYSEX_START (0xF0),
    SYSEX_END   (0xF7),
}