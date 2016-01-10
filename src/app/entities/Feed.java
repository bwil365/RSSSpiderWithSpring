package app.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="feeds")
public class Feed {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="feed_id")
	private long id;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="feed_fk")
	private Set<Artical> articals;
	
	private String url;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Artical> getArticals() {
		return articals;
	}

	public void setArticals(Set<Artical> articals) {
		this.articals = articals;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
}
