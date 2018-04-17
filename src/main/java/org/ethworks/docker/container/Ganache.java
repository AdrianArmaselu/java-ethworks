package org.ethworks.docker.container;

import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.HostConfig;
import com.spotify.docker.client.messages.PortBinding;
import org.ethworks.docker.DockerService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ganache implements  NodeContainer{

    private DockerService dockerService;

    public Ganache(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    public void start() throws DockerException, InterruptedException {
        final Map<String, List<PortBinding>> portBindings = new HashMap<>();
        portBindings.put("8545", Collections.singletonList(PortBinding.of("0.0.0.0", "8545")));
        HostConfig hostConfig = HostConfig.builder().portBindings(portBindings).build();
        ContainerConfig containerConfig = ContainerConfig.builder()
                .hostConfig(hostConfig)
                .image("trufflesuite/ganache-cli:latest")
                .exposedPorts("8545")
                .cmd("-a 10 -d")
                .build();
        dockerService.runContainer(containerConfig, "ganache");
    }

    public void stop(){
        dockerService.stopContainer();
    }
}
