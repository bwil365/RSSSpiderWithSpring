package app.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entities.Artical;
import app.entities.Feed;

public interface ArticalRepository extends JpaRepository<Artical,Long> {
	
	@Query("select a from Artical a where a.title = :title and a.pubDate = :pubDate")
	public Artical getArticalByTitleAndPubDateAndFeed(@Param("title") String title,@Param("pubDate") String pubDate);
	
}
