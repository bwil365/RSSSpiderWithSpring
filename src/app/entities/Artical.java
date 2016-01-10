package app.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="articals")
public class Artical {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="artical_id")
	private long id;
	
	@ManyToOne
	private Feed feed;
	
	private String title;
	
	private String link;
	
	@Lob 
	private String description;
	
	private String pubDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Feed getFeed() {
		return feed;
	}
	public void setFeed(Feed feed) {
		this.feed = feed;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	
	
	
}
