package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.file.FileAccess;
import app.file.FileData;
import app.services.FeedService;
import app.spiders.RSSSpider;


public class MainRSSApp {

	public static void main(String[] args) {
	
		if(args.length <= 0) {
			System.out.println("You need more args!!!!!");
			System.out.println("arg list: operation config_file resulting_file");
			System.exit(0);
		}
		else {
			app(args[0]);
		}
	}
	
	private static void app(String urlFile) {
		
		int numberOfThreads = 5;
		FileData fileData = FileAccess.readData(urlFile);
		List<String> urls = fileData.getData();
		List<RSSSpider> spiders = new ArrayList<>();
		
		// Adding spiders in code for the moment, this will change in the future
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:springBeans.xml");
		
		for(int i = 0; i < numberOfThreads; ++i) {
			RSSSpider spider = (RSSSpider) ctx.getBean("rssSpider");
			spiders.add(spider);
		}
		
		
		for(int i = 0; i < urls.size(); ++i) {
			String url = urls.get(i);
			int index = i % numberOfThreads;
			spiders.get(index).addUrl(url);
		}
		
		for(int i = 0; i < numberOfThreads; ++i) {
			spiders.get(i).start();
		}
		
		
		//String t = "http://finance.yahoo.com/webservice/v1/symbols/AAPL/quote?format=json&view=%E2%80%8C%E2%80%8Bdetail";
	}

}
