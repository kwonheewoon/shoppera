package org.khw.kotlinspring.authorities.repository

import org.khw.kotlinspring.authorities.domain.entity.AuthorityEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepository : JpaRepository <AuthorityEntity, Long>{

}