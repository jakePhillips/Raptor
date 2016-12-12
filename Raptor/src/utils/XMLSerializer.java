package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class XMLSerializer implements Serializer{

	private Stack stack = new Stack();
	private File file;
	
	public XMLSerializer(File file)
	{
		this.file = file;
	}
	
	@Override
	public void push(Object o) {
		stack.push(o);
		
	}

	@Override
	public Object pop() {
		return stack.pop(); 
	}

	@Override
	public void write() throws Exception{
		ObjectOutputStream os = null;

	    try
	    {
	      os =  new ObjectOutputStream(new GZIPOutputStream(new BufferedOutputStream ( new FileOutputStream(file) ) ) );
	      os.writeObject(stack);
	    }
	    finally
	    {
	      if (os != null)
	      {
	        os.close();
	      }
	    }
	  }

	@Override
	public void read() throws Exception {
		ObjectInputStream is = null;

	    try
	    {
	      is =  new ObjectInputStream(new GZIPInputStream(new BufferedInputStream ( new FileInputStream(file) ) ) );
	      stack = (Stack) is.readObject();
	    }
	    finally
	    {
	      if (is != null)
	      {
	        is.close();
	      }
	    }
		
	}

}
