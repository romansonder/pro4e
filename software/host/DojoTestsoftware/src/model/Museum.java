package model;

import java.util.ArrayList;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "Museum")
public class Museum {

	@ElementList(name = "MuseumsObject", inline = true, required = false)
	public ArrayList<MuseumsObject> list = new ArrayList<MuseumsObject>();
}