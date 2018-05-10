package model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "MuseumsObject")
public class MuseumsObject {

	@Attribute(name = "ID")
	private int id;

	@Attribute(name = "Name")
	private String name;

	@Attribute(name = "Language")
	private String language;

	@Attribute(name = "Path")
	private String path;

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}