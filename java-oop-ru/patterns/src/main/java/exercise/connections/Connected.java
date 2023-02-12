package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public final class Connected implements Connection {
    private final TcpConnection connection;

    public Connected(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public void connect() {
        System.out.println("Error! Connection has established already");
    }

    @Override
    public void disconnect() {
        this.connection.setState(new Disconnected(this.connection));
    }

    @Override
    public void write(String data) {
        connection.addToBuffer(data);
    }

    public String getName() {
        return "connected";
    }
}
// END
