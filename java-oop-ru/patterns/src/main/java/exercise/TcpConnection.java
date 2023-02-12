package exercise;

import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.ArrayList;
import java.util.List;

// BEGIN
public final class TcpConnection {
    private String ip;
    private int port;
    private Connection state;
    private List<String> buffer = new ArrayList<>();

    public TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.state = new Disconnected(this);
    }

    public String getCurrentState() {
        return this.state.getName();
    }

    public void connect() {
        state.connect();
    }

    public void disconnect() {
        state.disconnect();
    }

    public void write(String data) {
        state.write(data);
    }

    public void addToBuffer(String data) {
        buffer.add(data);
    }

    public void setState(Connection state) {
        this.state = state;
    }
}
// END
