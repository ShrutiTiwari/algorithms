import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class SimpleStream
{
    public static void main( String[] args ) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled( true );
        cb.setOAuthConsumerKey( "******" );
        cb.setOAuthConsumerSecret( "*****" );
        cb.setOAuthAccessToken( "********" );
        cb.setOAuthAccessTokenSecret( "******************" );

        TwitterStream twitterStream = new TwitterStreamFactory( cb.build() ).getInstance();

        StatusListener listener = new StatusListener() {

            @Override
            public void onStatus( Status status ) {
                User user = status.getUser();

                String username = status.getUser().getScreenName();
                System.out.println( "Screen name: " + username );

                String name = status.getUser().getName();
                System.out.println( "User's real name: " + name );

                long user_id = status.getUser().getId();
                System.out.println( "Users_id: " + user_id );

            }

        };

        twitterStream.addListener( listener );
        twitterStream.sample();

    }
}