package com.nkoad.wallbler.facebook.repository;

import com.nkoad.wallbler.facebook.model.feed.FacebookFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeedRepository extends JpaRepository<FacebookFeed, String> {

    @Query(value = "SELECT * FROM facebook_feed WHERE feed_name = :feedName", nativeQuery = true)
    FacebookFeed findByFeedName(@Param("feedName") String feedName);

}
