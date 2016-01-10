package app.spiders;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import app.entities.Artical;
import app.entities.Feed;
import app.services.FeedService;


@Component
@Scope("prototype")
public class RSSSpider extends Thread implements Spider {

	@Autowired
	@Qualifier("feedService")
	protected FeedService feedService;	
	
	protected ArrayList<String> urls = null;
	protected ArrayList<Feed> feeds;
	protected ArrayList<HashMap<String,String>> data;
	protected boolean loopIt = true;
	protected int timeSleeping = 5000;
	
	public void setRSSSpider(ArrayList<String> urls) {
	//	this.feedService = feedService;
		if(urls != null) {
			this.urls = urls;
		}
	}
	
	public void addUrl(String url) {
		//this.feedService = feedService;
		if(urls == null) {
			urls = new ArrayList<String>();
		}
		if(url != null) {
			urls.add(url);
		}
	}
	
	public void setRSSSpider(ArrayList<String> urls, int timeSleeping) {
		if(urls != null) {
			this.urls = urls;
			this.timeSleeping = timeSleeping;
		}
	}
	
	public void addUrlAndSleepTime(String url, int timeSleeping) {
		if(urls == null) {
			urls = new ArrayList<String>();
		}
		if(url != null) {
			urls.add(url);
			this.timeSleeping = timeSleeping;
		}
	}
	
	protected void executeSpider(Map<String,Feed> urlsAndFeeds) {
		while(loopIt) {
			try {
				Thread.sleep(timeSleeping);
				for(String u : urls) {
					System.out.println("Looping: " + u);
					getDataFromSource(u,urlsAndFeeds.get(u));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void getDataFromSource(String url, Feed feed) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			URL u = new URL(url);
			Document doc = builder.parse(u.openStream());
			NodeList nodes = doc.getElementsByTagName("item");
			
			for(int i = 0; i < nodes.getLength(); i++) {

				Element element = (Element)nodes.item(i);
				HashMap<String,String> m2 = new HashMap<String,String>();
				
				String title = getElementValue(element,"title");
				String link = getElementValue(element,"link");
				String description = getElementValue(element,"description");
				String pubDate = getElementValue(element,"pubDate");
				
				//Adding Artical to Database
				Artical artical = new Artical();
				artical.setTitle(title);
				artical.setLink(link);
				artical.setDescription(description);
				artical.setPubDate(pubDate);
				
				feedService.addArticalToFeed(feed.getId(), artical);
				
				//Adding it to the map for future use
				m2.put("title",title);
				m2.put("link",link);
				m2.put("description",description);
				m2.put("pubDate",pubDate);

				
				data.add(m2);
			}
		}
		catch(Exception e) {
			System.out.println("An Error Occured: " + e.getMessage());
		}
	}
	
	protected String getElementValue(Element parent,String label) {
		return getCharacterDataFromElement((Element)parent.getElementsByTagName(label).item(0));
	}
	
	protected String getCharacterDataFromElement(Element el) {
		try {
			Node child = el.getFirstChild();
			return child.getNodeValue();
		}
		catch(Exception e) {
			System.out.println("An Error Occured: " + e.getMessage());
		}
		
		return "";
	} 
	
	@Override
	public void run() {
		try {
			if(urls != null) {
				Map<String,Feed> urlsAndFeeds = new HashMap<String,Feed>();			
				for(String u : urls) {			
					Feed f = null;
					f = feedService.getFeedByUrl(u);
					if(f != null) {	
						urlsAndFeeds.put(u, f);
					}
					else {
						Feed f2 = new Feed();
						f2.setUrl(u);
						urlsAndFeeds.put(u, feedService.createFeed(f2));
					}
				}
				executeSpider(urlsAndFeeds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
