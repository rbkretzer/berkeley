package berkeley.utils;

/**
 * 
 * @author Gabriel A. Rodrigues, Leonardo S. Nunes e Rafael B. Kretzer
 *
 */
public class Client implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String host = "127.0.0.1";
    private int port = 4500;
    private Time time = new Time();

    public Client(int id, int port) {
        this.id = id;
        this.port = port;
        System.out.println("Create client with time: " + getTime());
    }

    public String getHost() {
        return host;
    }

    public void setHost(String ip) {
        this.host = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Time getTime() {
        return this.time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
