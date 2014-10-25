package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

import com.google.gson.Gson;

public class Friends {
	private static Gson gson = new Gson();
	private static final Logger logger = Logger.getLogger(Followers .class);

	private static final String INSERT_TWITTER_FOLLOWERS = "INSERT INTO TWITTER_USER_FRIENDS (user_id,friends_id,screen_name,INSERT_TIME) VALUES(?,?,?,?)";

	public static void insertfriends(Twitter user_twitter) {
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
					IDs friendsIds;
					int i=0;
					User user5=null;
					
		do{
			friendsIds = user_twitter.getFriendsIDs(user_twitter.getId(), followerCursor);	
	       for (long id : friendsIds.getIDs()) {
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
    
			while ((followerCursor = friendsIds.getNextCursor()) != 0);
			} catch (TwitterException te) {
				te.printStackTrace();
				System.out.println("Failed to get followers' ids: " + te.getMessage());
				//System.exit(-1);
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block
		logger.error("Couldn't insert user info to database : " + e);
			e.printStackTrace();
	         } 
		}
	}




