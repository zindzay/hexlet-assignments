package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public final class Disconnected implements Connection {
    private final TcpConnection tcpConnection;

    public Disconnected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public void connect() {
        System.out.println("connecting...");
        this.tcpConnection.setState(new Connected(this.tcpConnection));
    }

    @Override
    public void disconnect() {
        System.out.println("Error! Already disconnected");
    }

    @Override
    public void write(String data) {
        System.out.println("Error. Can't send data.");
    }

    public String getCurrentState() {
        return "disconnected";
    }
}
// END
