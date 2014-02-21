package targetcode;

import org.etsi.ttcn.tri.TriMessage;

// provided by TriMessage() of Factory // xxx

public class Message implements TriMessage {
    
    private byte[] mess;

    @Override
    public byte[] getEncodedMessage() {
        return mess;
    }

    @Override
    public void setEncodedMessage(byte[] message) {
        mess = message;

    }

    @Override
    public int getNumberOfBits() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setNumberOfBits(int amount) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean equals(TriMessage message) {
        // TODO Auto-generated method stub
        return false;
    }

}
