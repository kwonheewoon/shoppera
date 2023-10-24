package org.khw.shoppera.common.enums

import org.springframework.http.HttpStatus

enum class ResCode(val code: String, val message: String, val httpStatus: HttpStatus) {

    // 200 Ok

    USERS_SAVE("200_100", "회원 정보가 등록 되었습니다.", HttpStatus.OK),
    USERS_UPDATE("200_101", "회원 정보가 수정 되었습니다.", HttpStatus.OK),
    USERS_DELETE("200_102", "회원 정보가 삭제 되었습니다.", HttpStatus.OK),
    USERS_FIND("200_103", "회원 정보가 정상 적으로 조회 되었습니다.", HttpStatus.OK),
    USERS_PASSWORD_UPDATE("200_104", "패스워드 변경이 완료 되었습니다.", HttpStatus.OK),

    AUTHORITIES_SAVE("200_200", "권한 정보가 등록 되었습니다.", HttpStatus.OK),
    AUTHORITIES_DELETE("200_201", "권한 정보 삭제가 되었습니다.", HttpStatus.OK),


    ITEM_SAVE("200_400", "아이템 정보가 등록 되었습니다.", HttpStatus.OK),
    ITEM_UPDATE("200_401", "아이템 정보가 수정 되었습니다.", HttpStatus.OK),
    ITEM_DELETE("200_402", "아이템 정보가 삭제 되었습니다.", HttpStatus.OK),
    ITEM_FIND("200_403", "아이템 정보가 정상 적으로 조회 되었습니다.", HttpStatus.OK),

    ITEM_TYPE_SAVE("200_404", "아이템 타입 정보가 등록 되었습니다.", HttpStatus.OK),
    ITEM_TYPE_UPDATE("200_405", "아이템 타입 정보가 수정 되었습니다.", HttpStatus.OK),
    ITEM_TYPE_DELETE("200_406", "아이템 타입 정보가 삭제 되었습니다.", HttpStatus.OK),
    ITEM_TYPE_FIND("200_407", "아이템 타입 정보가 정상 적으로 조회 되었습니다.", HttpStatus.OK),

    COUPON_SAVE("200_408", "쿠폰 정보가 등록 되었습니다.", HttpStatus.OK),
    COUPON_UPDATE("200_409", "쿠폰 정보가 수정 되었습니다.", HttpStatus.OK),
    COUPON_DELETE("200_410", "쿠폰 정보가 삭제 되었습니다.", HttpStatus.OK),

    BRAND_SAVE("200_412", "브랜드가 등록 되었습니다.", HttpStatus.OK),
    BRAND_UPDATE("200_413", "브랜드가 수정 되었습니다.", HttpStatus.OK),
    BRAND_DELETE("200_414", "브랜드가 삭제 되었습니다.", HttpStatus.OK),

    USER_COUPON_SAVE("200_415", "쿠폰 발급이 완료 되었습니다.", HttpStatus.OK),
    USER_COUPON_USEAGE("200_416", "쿠폰 사용이 완료 되었습니다.", HttpStatus.OK),

    ORDER_SAVE_FAIL("200_420", "주문에 실패하였습니다. 주문정보를 확인해 주세요.", HttpStatus.OK),


    // 400 Bad Request

    // 409 Conflict

    DUPLICATE_AUTHORITIES("409_001", "중복된 권한 정보 입니다.", HttpStatus.CONFLICT),
    DUPLICATE_USER("409_002", "중복된 계정입니다.", HttpStatus.CONFLICT),
    DUPLICATE_ITEM_TYPE("409_003", "중복된 아이템 타입 코드 입니다.", HttpStatus.CONFLICT),
    DUPLICATE_COUPON_NAME("409_004", "중복된 쿠폰 이름 입니다.", HttpStatus.CONFLICT),
    DUPLICATE_BRAND_NAME("409_005", "중복된 브랜드 이름 입니다.", HttpStatus.CONFLICT),
    DUPLICATE_USER_COUPON("409_006", "이미 발급받은 쿠폰 입니다.", HttpStatus.CONFLICT),



    // 404 Not Found
    NOT_FOUND_CATEGORY("404_001", "존재 하지 않는 카테고리 정보 입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_ITEM("404_002", "존재 하지 않는 아이템 정보 입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_USER("404_003", "잘못된 회원 정보 입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_AUTHORITY("404_004", "존재 하지 않는 권한 입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_AUTHORITIES("404_005", "존재 하지 않는 권한 정보 입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_ITEM_TYPE("404_006", "존재 하지 않는 아이템 타입 정보입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_ITEM_OPTION("404_007", "존재 하지 않는 아이템 옵션 입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_COUPON("404_008", "존재 하지 않는 쿠폰 입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_BRAND("404_009", "존재 하지 않는 브랜드 입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_USER_COUPON("404_010", "존재 하지 않는 쿠폰 정보 입니다.", HttpStatus.NOT_FOUND)

}