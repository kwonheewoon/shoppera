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

    override fun findBrandByNamesClientStreaming(responseObserver: StreamObserver<BrandRes>): StreamObserver<BrandReq> {
        return object : StreamObserver<BrandReq> {
            val brands = mutableListOf<String>()

            override fun onNext(req: BrandReq) {
                // 각 브랜드 요청 처리
                println("Received request for brand: ${req.name}")
                brands.add(req.name)
            }

            override fun onError(t: Throwable) {
                println("Error during stream: ${t.message}")
            }

            override fun onCompleted() {
                // 모든 요청을 받은 후 최종 응답 전송
                val response = BrandRes.newBuilder()
                    .setName("Brands Processed")
                    .setExplanation("Processed ${brands.size} brands")
                    .build()
                responseObserver.onNext(response)
                responseObserver.onCompleted()
            }
        }
    }

    override fun findBrandByNameServerStreaming(request: BrandReq, responseObserver: StreamObserver<BrandRes>) {
        try {
            brandRepository.findByFoundedYearAndDeleteFlag(request.foundedYear, FlagYn.N)
                .forEach {
                    responseObserver.onNext(
                        BrandRes.newBuilder()
                            .setName(it.name)
                            .setExplanation(it.explanation)
                            .setFoundedYear(it.foundedYear)
                            .setDisplayFlag(it.displayFlag.name)
                            .build()
                    )
                }

            responseObserver.onCompleted()
        }catch (e: BrandException){
            val status = Status.NOT_FOUND.withCause(BrandException(ResCode.NOT_FOUND_BRAND)).withDescription(ResCode.NOT_FOUND_BRAND.message).asException()
            responseObserver.onError(status)
        }
    }

    override fun findBrandByNameClientServerStreaming(responseObserver: StreamObserver<BrandRes>): StreamObserver<BrandReq> {
        return object : StreamObserver<BrandReq> {
            override fun onNext(request: BrandReq) {
                // 각 요청을 받고 바로 처리
                val brand = brandRepository.findByNameAndDeleteFlag(request.name, FlagYn.N).orElse(null)
                brand?.let {
                    val response = BrandRes.newBuilder()
                        .setName(it.name)
                        .setExplanation(it.explanation)
                        .setFoundedYear(it.foundedYear)
                        .setDisplayFlag(it.displayFlag.name)
                        .build()
                    responseObserver.onNext(response) // 응답 스트리밍
                }
            }

            override fun onError(t: Throwable) {
                responseObserver.onError(Status.INTERNAL.withCause(t).asException())
            }

            override fun onCompleted() {
                responseObserver.onCompleted() // 모든 통신 완료
            }
        }
    }
}