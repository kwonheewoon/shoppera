package org.khw.kotlinspring.user.repository

import org.khw.kotlinspring.category.domain.entity.CategoryEntity
import org.khw.kotlinspring.user.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
}