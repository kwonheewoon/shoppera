package org.khw.shoppera.authorities.repository

import org.khw.shoppera.authorities.domain.entity.AuthorityEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepository : JpaRepository <AuthorityEntity, Long>{

}