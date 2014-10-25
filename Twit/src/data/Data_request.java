package data;

import database.Followers;
import twitter4j.*;
import twitter4j.examples.user.ShowUser;

public class Data_request {
	
	public static void data_get(Twitter user_twitter) {
	
try {
            
	               long followerCursor = -1;
                   IDs followerIds;
                   int i=0;
            do{
     	       
        	   
        		       followerIds = user_twitter.getFollowersIDs(user_twitter.getId(), followerCursor);
        	    
        	    
        		       for (long id : followerIds.getIDs()) {
                       	   User user5=user_twitter.showUser(id);
        		    	   System.out.println(id+user5.getScreenName()+i);
        		    	   long stmt1=user5.getId();
               			   String stmt2= user5.getName();
               			   String stmt3= user5.getScreenName();
        		    	  // Followers.insertfollowers(stmt1,stmt2,stmt3);
                       	   i++;
                       }
        	       }
        	       
        	       while ((followerCursor = followerIds.getNextCursor()) != 0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get followers' ids: " + te.getMessage());
            System.exit(-1);
        }











}
}