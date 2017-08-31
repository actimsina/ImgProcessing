package model;

import java.awt.Image;
import java.util.ArrayList;

public class User {

	private int id;
	private String name;
	private String pwd;
	// Full size images of 800x600
	private ArrayList<Image> images;
	// Thumb-nail size images of 32x32
	private ArrayList<Image> thumbs;

	public User() {
		images = new ArrayList<>();
		thumbs = new ArrayList<>();
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setThumbs(ArrayList<Image> thumbs) {
		this.thumbs = thumbs;
	}

	public ArrayList<Image> getThumbs() {
		return this.thumbs;
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
