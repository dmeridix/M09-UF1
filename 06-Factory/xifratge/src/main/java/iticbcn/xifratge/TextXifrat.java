package iticbcn.xifratge;

public class TextXifrat {
    private byte[] array;
    
    public TextXifrat(byte[] nouArray) {
        this.array = nouArray;
    }
    
    public byte[] getBytes() {
        return array;
    }
    @Override
    public String toString() {
        return new String(array.toString());
    }
    
}

