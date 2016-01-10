package app.services;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entities.Artical;
import app.entities.Feed;
import app.repositories.ArticalRepository;
import app.repositories.FeedRepository;

public class FeedService {

	@Resource
	private ArticalRepository articalRepository;
	
	@Resource
	private FeedRepository feedRepository;
	
	
	@Transactional
	public Feed createFeed(Feed feed) throws Exception {
		Feed newFeed = feed;
		if(newFeed == null) {
			throw new Exception("feed can't be null");
		}
		return feedRepository.save(newFeed);
	}
	
	@Transactional
	public Feed getFeedByUrl(String url){
		Feed feed = feedRepository.getFeedByUrl(url);
		return feed;
	}
	
	@Transactional
	public Feed addArticalToFeed(long id, Artical artical) throws Exception {
		Feed feed = feedRepository.findOne(id);	
		if(feed == null) {
			throw new Exception("There is no feed at this id");
		}
		else {
			//Fix this query
			Artical tempArtical = articalRepository.getArticalByTitleAndPubDateAndFeed(artical.getTitle(), artical.getPubDate());		
			if(tempArtical == null) {
				feed.getArticals().add(artical);
				return feedRepository.save(feed);
			}
			else {
				return null;
				//no fail here
				//throw new Exception("The artical can't already exist in the feed");
			}
		}
	}
}
