package database;

import helper.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.io.IOException;

import database.ConnPool;
import twitter4j.*;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import com.google.gson.Gson;



public class Followers {

	private static Gson gson = new Gson();
	private static final Logger logger = Logger.getLogger(Followers .class);

	private static final String INSERT_TWITTER_FOLLOWERS = "INSERT INTO TWITTER_USER_FOLLOWERS (user_id,followers_id,screen_name,INSERT_TIME) VALUES(?,?,?,?)";

	public static void insertfollowers(Twitter user_twitter) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		
		try {
			  
				logger.info("AAAAAAAAA");
				ConnPool.getInstance().init();
				con = ConnPool.getInstance().getConnection();
				logger.info("AAAAAAAAA");
				stmt = con.prepareStatement(INSERT_TWITTER_FOLLOWERS);
				    long followerCursor = -1;
					IDs followerIds;
					int i=0;
					User user5=null;
					followerIds = user_twitter.getFollowersIDs(user_twitter.getId(), followerCursor);	
		do{
 

	       
 
 
	       for (long id : followerIds.getIDs()) {
        	   user5=user_twitter.showUser(id);
	    	  // System.out.println(id+user5.getScreenName()+i);
	    	   long stmt1=user5.getId();
			   String stmt2= user5.getName();
			   String stmt3= user5.getScreenName();
           	logger.info("AAAAAAAAA");
   			stmt.setLong(1, stmt1);
   			stmt.setString(2, stmt2);
   			stmt.setString(3, stmt3);

  			Long d = System.currentTimeMillis();
  			stmt.setTimestamp(4, new Timestamp(d / 1000));

   			stmt.executeUpdate();
   			con.commit();
        	   i++;
	       	}
			}
    
			while ((followerCursor = followerIds.getNextCursor()) != 0);
			} catch (TwitterException te) {
				te.printStackTrace();
				System.out.println("Failed to get followers' ids: " + te.getMessage());
				System.exit(-1);
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block
		logger.error("Couldn't insert user info to database : " + e);
			e.printStackTrace();
	            
	  
	
	           
	
            


        			//con.close();
        			
	} 
		}
	}


