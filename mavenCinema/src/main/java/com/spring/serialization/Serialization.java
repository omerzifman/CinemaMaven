package com.spring.serialization;
import java.util.ArrayList;
import java.util.List;

import com.spring.entities.Movie;
import com.spring.entities.Session;

import java.io.*;
import java.util.ArrayList;

public class Serialization<T>
{
	public void serialize(String fileName,ArrayList<T> list) throws IOException
	{
         FileOutputStream fos= new FileOutputStream(fileName);
         ObjectOutputStream oos= new ObjectOutputStream(fos);
         oos.writeObject(list);
         oos.close();
         fos.close();
	}
	public ArrayList<T> deserialize(String fileName) throws IOException,ClassNotFoundException
	{
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<T> arr = (ArrayList<T>) ois.readObject();
        ois.close();
        fis.close();
        return arr;
	         
	}
}
