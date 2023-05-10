import java.io.*;

// source: https://stackoverflow.com/questions/1194656/appending-to-an-objectoutputstream
public class AppendingObjectOutputStream extends ObjectOutputStream {

    public AppendingObjectOutputStream(OutputStream out) throws IOException {
      super(out);
    }
  
    @Override
    protected void writeStreamHeader() throws IOException {
      reset();
    }
  
}