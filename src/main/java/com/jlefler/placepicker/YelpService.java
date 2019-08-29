package com.jlefler.placepicker;

import com.montealegreluis.yelpv3.Yelp;
import com.montealegreluis.yelpv3.businesses.SearchResult;
import com.montealegreluis.yelpv3.client.Credentials;
import com.montealegreluis.yelpv3.client.YelpClient;
import com.montealegreluis.yelpv3.search.SearchCriteria;
import com.montealegreluis.yelpv3.search.SortingMode;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;

public class YelpService {

    @Value("${yelp.id}")
    private Credentials id;

    @Value("${yelp.secret}")
    private YelpClient secret;

    public SearchResult search(HashMap<String, String> search){

        Yelp yelp = new Yelp(id, secret);

        SearchCriteria criteria = SearchCriteria.byLocation();
        criteria.withTerm("restaurants");
        criteria.withinARadiusOf();
        criteria.withPricing();
        criteria.openNow();
        criteria.limit();
        criteria.sortBy(SortingMode.REVIEW_COUNT);

        return yelp.search(criteria).searchResult();
    }



}
