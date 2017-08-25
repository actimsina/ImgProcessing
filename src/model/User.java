package model;

import java.awt.Image;
import java.util.ArrayList;

public class User {

	private int id;
	private String name;
	private String pwd;

	private ArrayList<Image> images;

	public User() {
		this.images = new ArrayList<>();

	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setImages(ArrayList<Image> imgs) {
		this.images = imgs;
	}

	public ArrayList<Image> getImages() {
		return this.images;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setPassword(String pwd) {
		this.pwd = pwd;
	}

	public String getPassword() {
		return this.pwd;
	}

}
