import java.io.*;

// source: https://stackoverflow.com/questions/1194656/appending-to-an-objectoutputstream
public class AppendingObjectOutputStream extends ObjectOutputStream {

    public AppendingObjectOutputStream(OutputStream out) throws IOException {
      super(out);
    }
  
    @Override
    protected void writeStreamHeader() throws IOException {
      // do not write a header, but reset:
      // this line added after another question
      // showed a problem with the original
      reset();
    }
  
}