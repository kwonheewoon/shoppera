package org.khw.shoppera.order.domain.entity

import jakarta.persistence.*
import lombok.Builder
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.Where
import org.khw.shoppera.brand.domain.entity.Brand
import org.khw.shoppera.category.domain.entity.Category
import org.khw.shoppera.common.entity.BaseEntity
import org.khw.shoppera.common.enums.CommonEnum
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.CommonEnum.OrderState
import org.khw.shoppera.item.domain.dto.ItemUpdateDto
import org.khw.shoppera.item.domain.entity.Item
import org.khw.shoppera.itemoption.domain.entity.ItemOption
import org.khw.shoppera.user.domain.entity.User
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
@Builder
@DynamicInsert
@DynamicUpdate
class Order(
    id: Long? = null,
    orderNumber: String,
    user: User,
    orderDate: LocalDate,
    deleteFlag: FlagYn = FlagYn.N,
    orderDetailList: List<OrderDetail>
) {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("주문 아이디 아이디")
    val id: Long? = id

    @Column(name = "order_number", nullable = false)
    @Comment("주문 번호")
    var orderNumber = orderNumber
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Comment("주문자 회원 정보")
    var user: User = user
        private set

    @Column(name = "order_date", nullable = false)
    @Comment("주문 일자")
    var orderDate = orderDate
        private set

    @Column(name = "delete_flag", nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("삭제여부")
    var deleteFlag = deleteFlag
        private set

    @Embedded
    val baseEntity = BaseEntity()

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "order")
    @Where(clause = "delete_flag = 'N'")
    var orderDetailList = orderDetailList
        private set

}