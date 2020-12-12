package com.call.chech

interface IPhoneState {
    fun onChangePhoneState(state: EPhoneState, date: Long, phone: String?)
}