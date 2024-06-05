package resources;

import pojo.LocationDetails;
import pojo.PlaceDetails;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public PlaceDetails generateAddPlacePayload(String name, String address, String language) {
        LocationDetails locationDetails = new LocationDetails();
        locationDetails.setLat(-38.383494);
        locationDetails.setLng(33.427362);

        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");

        PlaceDetails placeDetails = new PlaceDetails();
        placeDetails.setLocation(locationDetails);
        placeDetails.setAccuracy(50);
        placeDetails.setName(name);
        placeDetails.setPhone_number("(+91) 983 893 3937");
        placeDetails.setAddress(address);
        placeDetails.setTypes(types);
        placeDetails.setWebsite("http://rahulshettyacademy.com");
        placeDetails.setLanguage(language);

        return placeDetails;
    }
}
