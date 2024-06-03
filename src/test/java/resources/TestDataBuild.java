package resources;

import pojo.LocationDetails;
import pojo.PlaceDetails;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public PlaceDetails generateAddPlacePayload() {
        LocationDetails locationDetails = new LocationDetails();
        locationDetails.setLat(-38.383494);
        locationDetails.setLng(33.427362);

        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");

        PlaceDetails placeDetails = new PlaceDetails();
        placeDetails.setLocation(locationDetails);
        placeDetails.setAccuracy(50);
        placeDetails.setName("Rahul Shetty Academy");
        placeDetails.setPhone_number("(+91) 983 893 3937");
        placeDetails.setAddress("29, side layout, cohen 09");
        placeDetails.setTypes(types);
        placeDetails.setWebsite("http://rahulshettyacademy.com");
        placeDetails.setLanguage("French-IN");

        return placeDetails;
    }
}
