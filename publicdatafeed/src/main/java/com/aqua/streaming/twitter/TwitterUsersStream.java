package com.aqua.streaming.twitter;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterUsersStream
{
    public static void main( String[] args ) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled( true );
        cb.setOAuthConsumerKey( "YyX492qW889OK1SxKpA" );
        cb.setOAuthConsumerSecret( "PVYaJLPPn030g8hFDEdtSooobTkONg6oaDR9TnUGKGg" );
        cb.setOAuthAccessToken( "1448420899-Sq8Fg3Evapkf4jZoWqKzaRKOgRg0l8YfQk3o3zF" );
        cb.setOAuthAccessTokenSecret( "XUE62P9Ub2ydrXxegbWSyM51Q3i7WoNnLImrrXJt6Gtk3" );

        TwitterStream twitterStream = new TwitterStreamFactory( cb.build() ).getInstance();
        StatusListener statusListener = new MyListener();
        
        twitterStream.addListener( statusListener );
        twitterStream.sample();
        twitterStream.addRateLimitStatusListener( new RateLimitStatusListener() {
            @Override
            public void onRateLimitStatus( RateLimitStatusEvent event ) {
                System.out.println("Limit["+event.getRateLimitStatus().getLimit() + "], Remaining[" +event.getRateLimitStatus().getRemaining()+"]");
            }
            
            @Override
            public void onRateLimitReached( RateLimitStatusEvent event ) {
                System.out.println("Limit["+event.getRateLimitStatus().getLimit() + "], Remaining[" +event.getRateLimitStatus().getRemaining()+"]");
            }
        } );
    }

    static class MyListener implements StatusListener
    {
        int count=0;
        
        @Override
        public void onDeletionNotice( StatusDeletionNotice statusDeletionNotice ) {}

        @Override
        public void onException( Exception ex ) {}

        @Override
        public void onScrubGeo( long userId, long upToStatusId ) {}

        @Override
        public void onStallWarning( StallWarning warning ) {

        }

        @Override
        public void onStatus( Status status ) {
            if(count++==0){
                System.out.println( MyListener.format("S.No.", "Screen name: ", "User's real name: ", "Users_id: " , "Users_place: ", "Status: " ) );        
            }
            User user = status.getUser();
            System.out.println( format(count, user.getScreenName(), user.getName(), user.getId(), status.getPlace().getCountry(), status.getText() ) );
        }

        @Override
        public void onTrackLimitationNotice( int numberOfLimitedStatuses ) {}
        
        private static String format( Object... data ) {
            StringBuffer result = new StringBuffer();
            for(Object each: data){
                result.append( String.format( "%-30s", each ) );
            }
            return result.toString();
        }
    }
}