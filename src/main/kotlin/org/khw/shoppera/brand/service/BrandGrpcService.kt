package org.khw.shoppera.brand.service

import io.grpc.Status
import io.grpc.stub.StreamObserver
import lombok.RequiredArgsConstructor
import net.devh.boot.grpc.server.service.GrpcService
import org.khw.brand.test.BrandGrpc
import org.khw.brand.test.BrandReq
import org.khw.brand.test.BrandRes
import org.khw.shoppera.brand.repository.BrandRepository
import org.khw.shoppera.common.enums.CommonEnum.FlagYn
import org.khw.shoppera.common.enums.ResCode
import org.khw.shoppera.common.exception.BrandException

@GrpcService
@RequiredArgsConstructor
class BrandGrpcService(
    val brandRepository: BrandRepository
) : BrandGrpc.BrandImplBase() {

    override fun findBrandByName(request: BrandReq, responseObserver: StreamObserver<BrandRes>) {

        try {
            val brandRes = brandRepository.findByNameAndDeleteFlag(request.name, FlagYn.N).orElseThrow { throw BrandException(ResCode.NOT_FOUND_BRAND) }
                .let {
                    BrandRes.newBuilder()
                        .setName(it.name)
                        .setExplanation(it.explanation)
                        .setFoundedYear(it.foundedYear)
                        .setDisplayFlag(it.displayFlag.name)
                        .build()
                }

            responseObserver.onNext(brandRes)
            responseObserver.onCompleted()
        }catch (e: BrandException){
            val status = Status.NOT_FOUND.withCause(BrandException(ResCode.NOT_FOUND_BRAND)).withDescription(ResCode.NOT_FOUND_BRAND.message).asException()
            responseObserver.onError(status)
        }
    }
}