package app.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entities.Artical;
import app.entities.Feed;

public interface FeedRepository extends JpaRepository<Feed,Long>{
	@Query("select f from Feed f where f.url = :url")
	public Feed getFeedByUrl(@Param("url") String url);
}
