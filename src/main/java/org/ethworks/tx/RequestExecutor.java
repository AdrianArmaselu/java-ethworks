package org.ethworks.tx;

import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.Response;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class RequestExecutor {

    public static <S extends Response> S executeRequest(Request<?, S> request) {
        return executeRequest(request::send);
    }

    private static <S extends Response> S executeRequest(AbstractRequest abstractRequest) {
        try {
            Response response = abstractRequest.execute();
            if (response.hasError())
                throw new RuntimeException("Rpc call failed because of reason: " + response.getError().getMessage());
            return (S) response;
        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to make rpc call for reason: " + e.getMessage(), e);
        }
    }

    interface AbstractRequest {
        Response execute() throws ExecutionException, InterruptedException, IOException;
    }
}
