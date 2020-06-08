package com.example.jonas.kotlinmidiplayer.midi

data class MainHeader(val chunkType: CharArray,
                      val chunkLen:  Int,
                      val format:    Short,
                      val numTracks: Short,
                      val tickdiv:   Short
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MainHeader

        if (!chunkType.contentEquals(other.chunkType)) return false
        if (chunkLen != other.chunkLen) return false
        if (format != other.format) return false
        if (numTracks != other.numTracks) return false
        if (tickdiv != other.tickdiv) return false

        return true
    }

    override fun hashCode(): Int {
        var result = chunkType.contentHashCode()
        result = 31 * result + chunkLen
        result = 31 * result + format
        result = 31 * result + numTracks
        result = 31 * result + tickdiv
        return result
    }
}

data class TrackHeader(val chunkType: CharArray,
                       val chunkLen: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TrackHeader

        if (!chunkType.contentEquals(other.chunkType)) return false
        if (chunkLen != other.chunkLen) return false

        return true
    }

    override fun hashCode(): Int {
        var result = chunkType.contentHashCode()
        result = 31 * result + chunkLen
        return result
    }
}

data class Track(
    val header: TrackHeader,
    val metaEvents: Array<MetaEvent>,
    val channelEvents: Array<ChannelEvent>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Track

        if (header != other.header) return false
        if (!metaEvents.contentEquals(other.metaEvents)) return false
        if (!channelEvents.contentEquals(other.channelEvents)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = header.hashCode()
        result = 31 * result + metaEvents.contentHashCode()
        result = 31 * result + channelEvents.contentHashCode()
        return result
    }
}

data class MidiFile(
    var mainHeader: MainHeader,
    val Tracks: MutableList<Track>,
    var trackName: String = ""
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MidiFile

        if (mainHeader != other.mainHeader) return false
        if (!Tracks.contentEquals(other.Tracks)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mainHeader.hashCode()
        result = 31 * result + Tracks.contentHashCode()
        return result
    }
}