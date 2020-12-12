package com.call.chech

enum class EPhoneState(val id: Int) {
    OUT_CALL(0),
    OUT_CALL_END(1),
    INCOMING_CALL(2),
    INCOMING_CALL_END(3),
    MISS_CALL(4),
    IDLE(5)
}