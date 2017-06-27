package com.neusoft.springbatch.sample.cvs;

import java.util.Map;
import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hoo.framework.entity.User;
import com.neusoft.springbatch.sample.cvs.ehcache.CacheHelper;

public class JobLaunch{

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
//        ApplicationContext context = new ClassPathXmlApplicationContext("batch.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-ehcache.xml");
//        JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");
//        Job job = (Job) context.getBean("csvJob");
//
//        try {
//            /* 运行Job */
//            JobExecution result = launcher.run(job, new JobParameters());
//            /* 处理结束，控制台打印处理结果 */
//            System.out.println(result.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //String[] caches = CacheHelper.getAllCacheNames();
//        for(String cache : caches){
//        	 System.out.println(cache);
//        }
        Cache cache = CacheHelper.getCacheByName("priceLibCache");
      //  System.out.println(cache);
      //  System.out.println(cache.get("key1")); 
       // Element element = cache.get("key1");       
       // System.out.println(element.getValue());    
        cache.put(new Element("key1", "value2"));
//        User user1 = new User();
//		user1.setId(1);
//		user1.setName("zhangsan");
//		CacheHelper.setCache("priceLibCache", "user1", user1);
        //cache.put(new Element("key2", "value2"));
        Map<String, Map<Object, Element>> map = CacheHelper.getAllCache();
        Set<String> set = map.keySet();
        for(String key : set){
        	System.out.println("key:"+key+",value:"+map.get(key));
        }
        //System.out.println(cache.get("key2"));
        //Element e = CacheHelper.getCache("priceLibCache", "key2");
        //System.out.println(e.getValue());
      /*  List<String> keys = cache.getKeys();
        for(String key : keys){
       	 System.out.println(key);
       }*/
        //String name = (String) CacheHelper.getCacheValue("priceLibCache", "name");
        //System.out.println(name);
       // CacheHelper.setCache("priceLibCache", "company", "恒大");
       // System.out.println("company:"+CacheHelper.getCache("priceLibCache", "company"));
        User u1 = (User) CacheHelper.getCacheValue("priceLibCache", "user1");
        System.out.println("ID1:"+u1.getId()+",姓名1："+u1.getName());
        u1.setName("chenliu");
       // CacheHelper.setCache("priceLibCache", "user1", u1);
        User u2 = (User) CacheHelper.getCacheValue("priceLibCache", "user2");
        System.out.println("ID2:"+u2.getId()+",姓名2："+u2.getName());
        u2.setName("tianqi");
        //CacheHelper.setCache("priceLibCache", "user2", u2);
        //u.setName("李四");
        //CacheHelper.setCache("priceLibCache", "user1", u);
		//System.out.println("ID2:"+u.getId()+",姓名2："+u.getName());
        //System.out.println(CacheHelper.getCache("priceLibCache", "name"));
//        CacheManager cacheManager = CacheManager.getInstance();
		 
//        Cache cache = cacheManager.getCache("priceLibCache");
//        System.out.println("cache:"+cache);
//        System.out.println("key1:"+cache.get("key1"));
//        System.out.println("user1:"+cache.get("user1"));
//      //  cache.put(new Element("key2", "value2"));
//      //  System.out.println(cache.get("key2"));
//        while(true) {       
//            Object e = cache.get("key1");     
//            System.out.println(e);
//            if(e != null) {       
//                System.out.println(e);       
//                break;       
//            }       
//            Thread.sleep(1000);       
//        }  
    }

}
