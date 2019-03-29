package org.rkcpinfo.producer.model;




public class Engineering {

	public enum Genre {
		IT, Automobile, civil, electronics
	}

	private long engineerId;
	private String engineertitle;
	private Genre genre;
	
	public long getEngineerId() {
		return engineerId;
	}
	public void setEngineerId(long engineerId) {
		this.engineerId = engineerId;
	}
	public String getEngineertitle() {
		return engineertitle;
	}
	public void setEngineertitle(String engineertitle) {
		this.engineertitle = engineertitle;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	
	
}
