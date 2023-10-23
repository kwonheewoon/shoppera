package org.khw.shoppera.common.enums

class CommonEnum {
    enum class FlagYn{
        Y, N
    }

    enum class ItemType{

    }

    enum class OrderState{
        ORDER_CONFIRM, PAYMENT_CONFIRM, SHIPMENT_REQUEST, SHIPMENT_PROCESS, SHIPMENT_COMPLETED
    }
}