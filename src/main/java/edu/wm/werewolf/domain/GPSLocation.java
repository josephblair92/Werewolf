package edu.wm.werewolf.domain;

public class GPSLocation {
	
	private double lat;
	private double lng;
	
	public GPSLocation() {
		
	}
	
	public GPSLocation(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
	

}
