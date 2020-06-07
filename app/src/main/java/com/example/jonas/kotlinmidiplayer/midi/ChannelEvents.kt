package com.example.jonas.kotlinmidiplayer.midi

import kotlin.experimental.and

/***********************/
/*** Channel Events ***/
/***********************/

enum class ChannelEventStatus(val mask: Byte) {
    // [8n, 9n, An] where n is a channel number
    // Next byte is a value
    NOTE_OFF     (0x08),
    NOTE_ON      (0x09),
    KEY_PRESSURE (0x0A),

    // Bn where n is a channel number
    // Next byte is a flag
    CONTROL_CHANGE (0xB),

    // Cn, Dn, En
    PROGRAM_CHANGE     (0x0C),
    CHANNEL_AFTERTOUCH (0x0D),
    PITCH_BEND         (0x0E),
}

open class ChannelEvent(val deltaTime: Int, val status: Byte) {
    val channel: Byte // Low Nibble of Status
        get() = status and 0x0F

    val statusMask: ChannelEventStatus? // High Nibble of Status
        get() = ChannelEventStatus.values().associateBy(
            ChannelEventStatus::mask)[(status.toInt() shr 4).toByte() and 0x0F]
}

class NoteEvent (
    deltaTime:    Int,
    status:       Byte,
    val note:     Byte,
    val velocity: Byte
) : ChannelEvent(deltaTime, status)

class KeyPressureEvent (
    deltaTime: Int,
    status: Byte,
    val key: Byte,
    val pressure: Byte
) : ChannelEvent(deltaTime, status)

class ControlChangeEvent (
    deltaTime: Int,
    status: Byte,
    val flag: ControlChangeFlag,
    val controllerType: Byte
) : ChannelEvent(deltaTime, status)

class ProgramChange (
    deltaTime: Int,
    status: Byte,
    val ProgramNumber: Byte
) : ChannelEvent(deltaTime, status)

class ChannelAftertouch (
    deltaTime: Int,
    status: Byte,
    val Amount: Byte
) : ChannelEvent(deltaTime, status)

class PitchBend (
    deltaTime: Int,
    status: Byte,
    LeastSignificantByte: Byte,
    MostSignificantByte: Byte
) : ChannelEvent(deltaTime, status)

enum class ControlChangeFlag(val event: Byte) {
    BANK_SELECT           (0x00),
    MODULATION_WHEEL      (0x01),
    BREATH_CONTROLLER     (0x02),
    FOOT_CONTROLLER       (0x04),
    PORTAMENTO_TIME       (0x05),
    DATA_ENTRY_SLIDER     (0x06),
    MAIN_VOLUME           (0x07),
    BALANCE               (0x08),
    PAN                   (0x0A),
    EXPRESSION_CONTROLLER (0x0B),
    EFFECT_CONTROL_1      (0x0B),
    EFFECT_CONTROL_2      (0x0C),
    GENERAL_PURPOSE_1     (0x10), // IS_GENERAL_PURPOSE(byte)
    GENERAL_PURPOSE_2     (0x11),
    GENERAL_PURPOSE_3     (0x12),
    GENERAL_PURPOSE_4     (0x13),
    LSB_CONTROLLER_0      (0x20), // IS_LSB_CONTROLLER(byte)
    LSB_CONTROLLER_1      (0x21),
    LSB_CONTROLLER_2      (0x22),
    LSB_CONTROLLER_3      (0x23),
    LSB_CONTROLLER_4      (0x24),
    LSB_CONTROLLER_5      (0x25),
    LSB_CONTROLLER_6      (0x26),
    LSB_CONTROLLER_7      (0x27),
    LSB_CONTROLLER_8      (0x28),
    LSB_CONTROLLER_9      (0x29),
    LSB_CONTROLLER_10     (0x2A),
    LSB_CONTROLLER_11     (0x2B),
    LSB_CONTROLLER_12     (0x2C),
    LSB_CONTROLLER_13     (0x2D),
    LSB_CONTROLLER_14     (0x2E),
    LSB_CONTROLLER_15     (0x2F),
    LSB_CONTROLLER_16     (0x30),
    LSB_CONTROLLER_17     (0x31),
    LSB_CONTROLLER_18     (0x32),
    LSB_CONTROLLER_19     (0x33),
    LSB_CONTROLLER_20     (0x34),
    LSB_CONTROLLER_21     (0x35),
    LSB_CONTROLLER_22     (0x36),
    LSB_CONTROLLER_23     (0x37),
    LSB_CONTROLLER_24     (0x38),
    LSB_CONTROLLER_25     (0x39),
    LSB_CONTROLLER_26     (0x3A),
    LSB_CONTROLLER_27     (0x3B),
    LSB_CONTROLLER_28     (0x3C),
    LSB_CONTROLLER_29     (0x3D),
    LSB_CONTROLLER_30     (0x3E),
    LSB_CONTROLLER_31     (0x3F),
    DAMPER_PEDAL          (0x40),
    SOSTENUTO_PEDAL       (0x42),
    SOFT_PEDAL            (0x43),
    LEGATO_FOOTSWITCH     (0x44),
    HOLD_2                (0x45),
    SOUND_CONTROLLER_1    (0x46), // IS_SOUND_CONTROLLER(byte)
    SOUND_CONTROLLER_2    (0x47),
    SOUND_CONTROLLER_3    (0x48),
    SOUND_CONTROLLER_4    (0x49),
    SOUND_CONTROLLER_5    (0x4A),
    SOUND_CONTROLLER_6    (0x4B),
    SOUND_CONTROLLER_7    (0x4C),
    SOUND_CONTROLLER_8    (0x4D),
    SOUND_CONTROLLER_9    (0x4E),
    SOUND_CONTROLLER_10   (0x4F),
    GENERAL_PURPOSE_5     (0x50), // IS_GENERAL_PURPOSE(byte)
    GENERAL_PURPOSE_6     (0x51),
    GENERAL_PURPOSE_7     (0x52),
    GENERAL_PURPOSE_8     (0x53),
    PORTAMENTO_CONTROL    (0x54),
    EFFECTS_1_DEPTH       (0x5B),
    EFFECTS_2_DEPTH       (0x5C),
    EFFECTS_3_DEPTH       (0x5D),
    EFFECTS_4_DEPTH       (0x5E),
    EFFECTS_5_DEPTH       (0x5F),
    DATA_INCREMENT_1      (0x60), // IS_DATA_INCREMENT(byte)
    DATA_INCREMENT_2      (0x61),
    NON_REGISTERED_PARAM_LSB (0x62),
    NON_REGISTERED_PARAM_MSB (0x63),
    REGISTERED_PARAM_LSB  (0x64),
    REGISTERED_PARAM_MSB  (0x65),
    MODE_MESSAGE          (0x79), // IS_MODE_MESSAGE(byte)
    MODE_MESSAGE_1        (0x79),
    MODE_MESSAGE_2        (0x7A),
    MODE_MESSAGE_3        (0x7B),
    MODE_MESSAGE_4        (0x7C),
    MODE_MESSAGE_5        (0x7D),
    MODE_MESSAGE_6        (0x7E),
    MODE_MESSAGE_7        (0x7F),
}
