package com.example.jonas.kotlinmidiplayer.midi

import java.nio.ByteBuffer
import java.nio.ByteOrder

fun parseMainHeader(buffer: ByteBuffer, file: MidiFile): Int {
    return currentByte
}
fun ParseMIDI(buffer: ByteBuffer): MidiFile {
    buffer.order(ByteOrder.BIG_ENDIAN)

    var currentByte: Int = 0
    val mainChunkType = CharArray(4)
    for (i in 0..3) {
        mainChunkType[i] = buffer.get(currentByte + i).toChar();
    }
    currentByte += 4

    val mainChunkLen = buffer.getInt(currentByte)
    currentByte += 4

    val format = buffer.getShort(currentByte)
    currentByte += 2

    val numberOfTracks = buffer.getShort(currentByte)
    currentByte += 2

    val tickDiv = buffer.getShort(currentByte)
    currentByte += 2

    val mainHeader = MainHeader(mainChunkType, mainChunkLen, format, numberOfTracks, tickDiv)
    // currentByte should be pointing at the head of the first track

    val Tracks = MutableList<Track>(numberOfTracks.toInt())
    if (mainHeader.format.equals(1)) {
        // The first track in a format 1 file is only metadata
        while (buffer[currentByte] MetaEventStatus.END_OF_TRACK) {
        }
    }
    for (i in 0..numberOfTracks) {
    }

//    val mainChunkType = buffer.copyOfRange(currentByte, currentByte + 4).toString().toCharArray()
//    currentByte +=4
//    val mainChunkLen = buffer.copyOfRange(currentByte, currentByte + 4).reverse() as Int // Little Endian
//    currentByte += 4
//    val format = buffer.copyOfRange(currentByte, currentByte + 2).reverse() as Short
//    currentByte += 2
//    val numberOfTracks = buffer.copyOfRange(currentByte, currentByte + 2).reverse() as Short
//    currentByte += 2
//    val tickdiv = buffer.copyOfRange(currentByte, currentByte + 2).reverse() as Short
}