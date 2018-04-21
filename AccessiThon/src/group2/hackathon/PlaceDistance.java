package group2.hackathon;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import group2.hachathon.po.Places;
import group2.hackathon.service.wsClient;
import group2.hackathon.stimulate.DataStimulation;

public class PlaceDistance {

	private wsClient client;
	private Places places;

	public Places getPlaces() {
		return places;
	}

	public void setPlaces(Places places) {
		this.places = places;
	}

	public PlaceDistance() {
		client = new wsClient(
				"https://maps.googleapis.com/maps/api/distancematrix/json?origins=");
		places = DataStimulation.generatePlacesData();
	}

	public double locationDistance() throws ClientProtocolException,
			JSONException, IOException, URISyntaxException {
		double dis = client.getDistance(places.getOriginal(),
				places.getDestination());
		return dis;
	}

}
