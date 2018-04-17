package org.ethworks;

import com.spotify.docker.client.exceptions.DockerCertificateException;
import org.ethworks.docker.DockerService;

import java.io.IOException;

public class Observer {

    public static void main(String[] args) throws DockerCertificateException, IOException {
        DockerService dockerService = new DockerService();
//        ContractDeployment deployment = new ContractDeployment.Builder()
//                .with($ -> $.nodeContainer = new Ganache(dockerService))
//                .build();
//        Subscription subscription = deployment.getWeb3j().blockObservable(false).subscribe(block -> {
//            System.out.println("Sweet, block number " + block.getBlock().getNumber() + " has just been created");
//        }, Throwable::printStackTrace);
//        while(true){
////            deployment.getWeb3j()
////                    .ethGetBlockByNumber(new DefaultBlockParameterNumber(deployment.getWeb3j().ethBlockNumber().send().getBlockNumber()), true)
////                    .send()
////                    .getBlock()
////                    .getTransactions()
////                    .forEach(transactionResult -> System.out.println(transactionResult.get()));
//            deployment.getWeb3j().transactionObservable().forEach(transaction ->  System.out.println(transaction.getValue()));
//
////            deployment.
////            System.out.println( deployment.getWeb3j()
////                    .ethGetTransactionByBlockNumberAndIndex(DefaultBlockParameterName.LATEST, BigInteger.valueOf(0))
////                    .send().getTransaction().get().getValue());
//           ;
//
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }
}
