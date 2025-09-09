package com.wintux.inventario.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class InventarioGrpcServiceImpl  extends InventarioServiceGrpc.InventarioServiceImplBase{
    @Override
    public void getStock(StockRequest request, StreamObserver<StockResponse> responseObserver){
        int cant = 33; // Acá viene la lógica de negocio
        StockResponse response = StockResponse.newBuilder().setCantidad(cant).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
