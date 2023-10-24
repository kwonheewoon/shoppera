package org.khw.shoppera.common.enums

import java.lang.RuntimeException

class CommonEnum {
    enum class FlagYn{
        Y, N
    }

    enum class ItemType{

    }

    enum class OrderState{
        ORDER_CONFIRM, PAYMENT_CONFIRM, SHIPMENT_REQUEST, SHIPMENT_PROCESS, SHIPMENT_COMPLETED;

        companion object {
            fun fromString(value: String): OrderState {
                return values().find { it.name == value } ?: throw RuntimeException("존재하지 않는 상태 정보입니다.")
            }
        }
    }
}