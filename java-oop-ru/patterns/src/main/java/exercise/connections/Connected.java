package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public final class Connected implements Connection {
    private final TcpConnection tcpConnection;

    public Connected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public void connect() {
        System.out.println("Error! Already connected");
    }

    @Override
    public void disconnect() {
        System.out.println("disconnecting...");
        this.tcpConnection.setState(new Disconnected(this.tcpConnection));
    }

    @Override
    public void write(String data) {
        System.out.println(String.format("Sending data: %s", data));
    }

    public String getCurrentState() {
        return "connected";
    }
}
// END
