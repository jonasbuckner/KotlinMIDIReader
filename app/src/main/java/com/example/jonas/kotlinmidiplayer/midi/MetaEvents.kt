package com.example.jonas.kotlinmidiplayer.midi

enum class MetaEventStatus(val status: Byte) {
    // Meta Events
    SEQUENCE_NUMBER     (0x00),
    TEXT_EVENT          (0x01),
    COPYRIGHT_NOTICE    (0x02),
    TRACK_NAME          (0x03),
    INSTRUMENT_NAME     (0x04),
    LYRIC               (0x05),
    MARKER              (0x06),
    CUE_POINT           (0x07),
    MIDI_CHANNEL_PREFIX (0x20),
    END_OF_TRACK        (0x2F),
    TEMPO               (0x51),
    SMPTE_OFFSET        (0x54),
    TIME_SIGNATURE      (0x58),
    KEY_SIGNATURE       (0x59),
    SEQUENCER_EVENT     (0x7f),
}

data class MetaEvent(val deltaTime: Int, val status: MetaEventStatus, val length: Int, val value: ByteArray) {
    override fun toString(): String {
        return when(status) {
            MetaEventStatus.TEXT_EVENT -> value.toString()
            MetaEventStatus.COPYRIGHT_NOTICE -> value.toString()
            MetaEventStatus.TRACK_NAME -> value.toString()
            MetaEventStatus.INSTRUMENT_NAME -> value.toString()
            MetaEventStatus.LYRIC -> value.toString()
            MetaEventStatus.MARKER -> value.toString()
            else -> super.toString()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MetaEvent

        if (deltaTime != other.deltaTime) return false
        if (status != other.status) return false
        if (length != other.length) return false
        if (!value.contentEquals(other.value)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = deltaTime
        result = 31 * result + status.hashCode()
        result = 31 * result + length
        result = 31 * result + value.contentHashCode()
        return result
    }
}
