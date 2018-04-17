package org.ethworks.docker.container;

import com.spotify.docker.client.exceptions.DockerException;

public interface NodeContainer {
    boolean isRunning();
    void start() throws DockerException, InterruptedException;
    void stop();
}
